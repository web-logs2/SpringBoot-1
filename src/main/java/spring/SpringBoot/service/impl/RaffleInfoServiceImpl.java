package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
//            List<ParticipantInfo> participantInfos = new  ArrayList<>();
//            participantInfos = participantInfoMapper.ParticipantInfos(raffleInfo.getRaffleaddress());
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
    if(4!=raffleStatusByDb && 5!=raffleStatusByDb){
      BigInteger raffleStatusByChain = raffleContractService.getState(raffleAddress,chainId);
      if(raffleStatusByChain != null && !raffleStatusByChain.equals( BigInteger.valueOf(raffleStatusByDb))){
        raffleInfo.setRafflestatus(Integer.valueOf(String.valueOf(raffleStatusByChain)));
      }
    }
    if(4==raffleStatusByDb && 0==swapStatusStatusByDb){
        // 4:已经完成   && 0：说明还未进行选择，需要矫正状态。  1：eth  2：nft
        BigInteger swapStautsByChain = raffleContractService.getSwapStauts(raffleAddress,chainId);
        if(0 !=swapStautsByChain.intValue() ){
            raffleInfo.setSwapStatus(swapStautsByChain.intValue());
        }

        if(null == raffleInfo.getKing()){
            String king = raffleContractService.getKing(raffleInfo.getRaffleaddress(),chainId);
            if(null!=king&&""!=king){
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
    if(new BigInteger("5").equals(raffleInfo.getRafflestatus()) &&
            !(new BigInteger("0").equals(raffleInfo.getRaffleAssets()))) {
        //取消状态，getSwapStauts  = 1，即为已退回
        BigInteger nftBackStatus = raffleContractService.getSwapStauts(raffleInfo.getRaffleaddress(),chainId);
        BigInteger soldTickets = raffleContractService.getSoldTickets(raffleInfo.getRaffleaddress(),chainId);
        BigInteger refundTickets = raffleContractService.getRefundTickets(raffleInfo.getRaffleaddress(),chainId);
        int raffleAssetsByDb = raffleInfo.getRaffleAssets();
        if(new BigInteger("1").equals(nftBackStatus)){
            if(raffleAssetsByDb == 999){
                if(soldTickets.equals(refundTickets)){
                    raffleInfo.setRaffleAssets(0);
                }else {
                    raffleInfo.setRaffleAssets(1);
                }
            }
            if(raffleAssetsByDb == 1){
                raffleInfo.setRaffleAssets(0);
            }
        }
        if(soldTickets.equals(refundTickets)){
            if(raffleAssetsByDb == 999){
                if(new BigInteger("1").equals(nftBackStatus)){
                    raffleInfo.setRaffleAssets(0);
                }else {
                    raffleInfo.setRaffleAssets(2);
                }
            }
        }else {
            if(!new BigInteger("1").equals(nftBackStatus)){
                raffleInfo.setRaffleAssets(999);
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
        if (null!=raffleAddress && ""!=raffleAddress){
            return raffleInfoMapper.getDetailByRaffleAddress(raffleAddress);
        }
        return null;
    }
}
