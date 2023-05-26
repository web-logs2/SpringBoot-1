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
    if(!(new BigInteger("4").equals(raffleInfo.getRafflestatus())||new BigInteger("5").equals(raffleInfo.getRafflestatus()))){
      BigInteger status = raffleContractService.getState(raffleInfo.getRaffleaddress());
      if(!status.equals(raffleInfo.getRafflestatus())){
        raffleInfo.setRafflestatus(Integer.valueOf(String.valueOf(status)));
      }
    }
    if(new BigInteger("5").equals(raffleInfo.getRafflestatus())
            &&new BigInteger("0").equals(raffleInfo.getSwapStatus())){
        BigInteger status = raffleContractService.getSwapStauts(raffleInfo.getRaffleaddress());
        if(!status.equals(raffleInfo.getSwapStatus())){
            raffleInfo.setSwapStatus(Integer.valueOf(String.valueOf(status)));
            updateRaffleInfo(raffleInfo);
        }
        if(null == raffleInfo.getKing()){
            String king = raffleContractService.getKing(raffleInfo.getRaffleaddress());
            if(null!=king&&""!=king){
                raffleInfo.setKing(king);
            }
        }
        if(null == raffleInfo.getWinnerDrawTimestamp()){
            BigInteger winnerDrawTimestamp = raffleContractService.getWinnerDrawTimestamp(raffleInfo.getRaffleaddress());
            if(null!=winnerDrawTimestamp){
                raffleInfo.setWinnerDrawTimestamp(winnerDrawTimestamp.longValue());
            }
        }

    }
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
