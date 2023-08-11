package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.constant.RaffleAssetsStatus;
import spring.SpringBoot.constant.RaffleStatus;
import spring.SpringBoot.constant.SwapStatus;
import spring.SpringBoot.entry.ParticipantInfo;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.entry.TokenInfo;
import spring.SpringBoot.mapper.ParticipantInfoMapper;
import spring.SpringBoot.mapper.RaffleInfoMapper;
import spring.SpringBoot.mapper.TokenInfoMapper;
import spring.SpringBoot.service.RaffleContractService;
import spring.SpringBoot.service.RaffleInfoService;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RaffleInfoServiceImpl implements RaffleInfoService {

    @Autowired
    RaffleInfoMapper raffleInfoMapper;

    @Autowired
    TokenInfoMapper tokenInfoMapper;

    @Autowired
    RaffleContractService raffleContractService;

    @Autowired
    ParticipantInfoMapper participantInfoMapper;

    @Override
    public List<TokenRaffleVo> getRaffleInfoListByOwner(String owner) {
        List<TokenRaffleVo> list = new ArrayList<>();
        List<RaffleInfo> raffleInfos =  raffleInfoMapper.getRaffleInfoListByOwner(owner);
        for (RaffleInfo raffleInfo:raffleInfos){
          TokenRaffleVo  tokenRaffleVo = new TokenRaffleVo();
          TokenInfo tokenInfo = tokenInfoMapper.selectByTokenId(raffleInfo.getContractAddress(), raffleInfo.getTokenId());
          Integer participants = participantInfoMapper.getParticipantCount(raffleInfo.getRaffleaddress());
          raffleInfo.setParticipants(null == participants?0:participants);
            raffleInfo = correctStatus(raffleInfo);
            tokenRaffleVo.setRaffleInfo(raffleInfo);
            tokenRaffleVo.setTokenInfo(tokenInfo);
            list.add(tokenRaffleVo);
        }
        return list;
    }

    @Override
    public List<TokenRaffleVo> findAll(String owner,int pageNumber, int pageSize,String chainId) {
        List<TokenRaffleVo> list = new ArrayList<>();
        int offset = pageNumber * pageSize;
        List<RaffleInfo>  raffleInfos =  raffleInfoMapper.findAll(owner,offset,pageSize,chainId);
        for (RaffleInfo raffleInfo:raffleInfos){
            TokenRaffleVo  tokenRaffleVo = new TokenRaffleVo();
            TokenInfo tokenInfo = tokenInfoMapper.selectByTokenId(raffleInfo.getContractAddress(), raffleInfo.getTokenId());
            Integer participants = participantInfoMapper.getParticipantCount(raffleInfo.getRaffleaddress());

            raffleInfo.setParticipants(null == participants?0:participants);
            raffleInfo = correctStatus(raffleInfo);
            tokenRaffleVo.setRaffleInfo(raffleInfo);
            tokenRaffleVo.setTokenInfo(tokenInfo);
            list.add(tokenRaffleVo);
        }
        return list;
    }

  @Override
    public RaffleInfo correctStatus(RaffleInfo raffleInfo){
      int raffleStatusByDb = raffleInfo.getRafflestatus();
      int swapStatusStatusByDb = raffleInfo.getSwapStatus();
      String raffleAddress = raffleInfo.getRaffleaddress();
      Long chainId = raffleContractService.getTokenChainIdByRaffleAddress(raffleAddress);
        //!4  !5的时候，raffleStatus会变化
    if(RaffleStatus.Completed.getCode()!=raffleStatusByDb && RaffleStatus.Cancelled.getCode()!=raffleStatusByDb && !Long.valueOf(-1).equals(chainId)){

            BigInteger raffleStatusByChain = raffleContractService.getState(raffleAddress,chainId);
            if(raffleStatusByChain != null && !raffleStatusByChain.equals( BigInteger.valueOf(raffleStatusByDb))){
                raffleInfo.setRafflestatus(Integer.valueOf(String.valueOf(raffleStatusByChain)));
            }

    }
    if(RaffleStatus.Completed.getCode()==raffleStatusByDb && SwapStatus.NoSwap.getCode()==swapStatusStatusByDb && !Long.valueOf(-1).equals(chainId)){
        // 4:已经完成   && 0：说明还未进行选择，需要矫正状态。  1：eth  2：nft
        BigInteger swapStautsByChain = raffleContractService.getSwapStauts(raffleAddress,chainId);
        if(SwapStatus.NoSwap.getCode() !=swapStautsByChain.intValue() ){
            raffleInfo.setSwapStatus(swapStautsByChain.intValue());
        }

        if(null == raffleInfo.getKing()){
            String king = raffleContractService.getKing(raffleInfo.getRaffleaddress(),chainId);
            if(null!=king&&!"".equals(king)){
                raffleInfo.setKing(king);
            }
        }
        if(null == raffleInfo.getWinnerDrawTimestamp()){
            BigInteger winnerDrawTimestamp = raffleContractService.getWinnerDrawTimestamp(raffleInfo.getRaffleaddress(),chainId);
            if(null!=winnerDrawTimestamp){
                raffleInfo.setWinnerDrawTimestamp(winnerDrawTimestamp.longValue());
            }
        }
        if(null == raffleInfo.getWinnerTicketNumber()){
            BigInteger winnerTicketNumber = raffleContractService.getWinnerTicketNumber(raffleInfo.getRaffleaddress(),chainId);
            if(null != winnerTicketNumber){
                raffleInfo.setWinnerTicketNumber(winnerTicketNumber);
            }
        }
     }
//     5:是终态，和兑换状态有关
    if(RaffleStatus.Cancelled.getCode() == raffleInfo.getRafflestatus() &&
            RaffleAssetsStatus.NO.getCode() != raffleInfo.getRaffleAssets() && !Long.valueOf(-1).equals(chainId)) {
        //取消状态，getSwapStauts  = 1，即为已退回
        Integer nftBackStatusbyChain = raffleContractService.getSwapStauts(raffleInfo.getRaffleaddress(),chainId).intValue();
        BigInteger soldTicketsbyChain = raffleContractService.getSoldTickets(raffleInfo.getRaffleaddress(),chainId);
        BigInteger refundTicketsbyChain = raffleContractService.getRefundTickets(raffleInfo.getRaffleaddress(),chainId);
        int raffleAssetsByDb = raffleInfo.getRaffleAssets();
        if(SwapStatus.ETH.getCode() == nftBackStatusbyChain){
            if(RaffleAssetsStatus.ALL.getCode() == raffleAssetsByDb){
                if(soldTicketsbyChain.equals(refundTicketsbyChain)){
                    raffleInfo.setRaffleAssets(RaffleAssetsStatus.NO.getCode());
                }else {
                    raffleInfo.setRaffleAssets(RaffleAssetsStatus.ETH.getCode());
                }
            }
            if(RaffleAssetsStatus.ETH.getCode() == raffleAssetsByDb ){
                raffleInfo.setRaffleAssets(RaffleAssetsStatus.NO.getCode());
            }
        }
        if(soldTicketsbyChain.equals(refundTicketsbyChain)){
            if(RaffleAssetsStatus.ALL.getCode() == raffleAssetsByDb){
                if(SwapStatus.ETH.getCode() ==nftBackStatusbyChain){
                    raffleInfo.setRaffleAssets(RaffleAssetsStatus.NO.getCode());
                }else {
                    raffleInfo.setRaffleAssets(RaffleAssetsStatus.NFT.getCode());
                }
            }
        }else {
            if(SwapStatus.ETH.getCode() !=nftBackStatusbyChain){
                raffleInfo.setRaffleAssets(RaffleAssetsStatus.ALL.getCode());
            }
        }

       }

//    }
      updateRaffleInfo(raffleInfo);
      return raffleInfo;
    }




    @Override
    public int createRaffleInfo(RaffleInfo raffleInfo) {
        return raffleInfoMapper.insertSelective(raffleInfo);
    }

    @Override
    public int updateRaffleInfo(RaffleInfo raffleInfo) {
        return raffleInfoMapper.updateRaffleInfo(raffleInfo);
    }

    @Override
    public RaffleInfo getRaffleInfoByCondition(Map map) {
        return raffleInfoMapper.getRaffleInfoByCondition(map);
    }

    @Override
    public TokenRaffleVo getDetailByRaffleAddress(String raffleAddress) {
        TokenRaffleVo  tokenRaffleVo = new TokenRaffleVo();
        RaffleInfo raffleInfo =  raffleInfoMapper.getDetailByRaffleAddress(raffleAddress);
//        票的数量
        Integer participantCount = participantInfoMapper.getParticipantCount(raffleAddress);
        raffleInfo.setParticipants(null == participantCount?0:participantCount);
        List<ParticipantInfo> participantInfoList = participantInfoMapper.getParticipantInfosByRaffleAddress(raffleAddress);
        tokenRaffleVo.setParticipantInfos(participantInfoList);
        TokenInfo tokenInfo = tokenInfoMapper.selectByTokenId(raffleInfo.getContractAddress(), raffleInfo.getTokenId());
        raffleInfo = correctStatus(raffleInfo);
        tokenRaffleVo.setRaffleInfo(raffleInfo);
        tokenRaffleVo.setTokenInfo(tokenInfo);
        return tokenRaffleVo;
    }

    @Override
    public RaffleInfo getRaffleDetailByRaffleAddress(String raffleAddress) {
        if (null!=raffleAddress && !"".equals(raffleAddress)){
            return raffleInfoMapper.getDetailByRaffleAddress(raffleAddress);
        }
        return null;
    }
}
