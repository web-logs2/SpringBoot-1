package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.ParticipantInfo;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.entry.TokenInfo;
import spring.SpringBoot.mapper.ParticipantInfoMapper;
import spring.SpringBoot.mapper.RaffleInfoMapper;
import spring.SpringBoot.mapper.TokenInfoMapper;
import spring.SpringBoot.service.ParticipantInfoService;
import spring.SpringBoot.service.RaffleInfoService;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantInfoServiceImpl implements ParticipantInfoService {

    @Autowired
    ParticipantInfoMapper participantInfoMapper;

    @Autowired
  RaffleInfoService raffleInfoService;

  @Autowired
  RaffleInfoMapper raffleInfoMapper;

  @Autowired
  TokenInfoMapper tokenInfoMapper;


    @Override
    public List<TokenRaffleVo> getParticipantInfos(String participantAddress) {
      List<ParticipantInfo> participantInfos = participantInfoMapper.getParticipantInfos(participantAddress);
      List<TokenRaffleVo> list = new ArrayList<>();
      for(ParticipantInfo participantInfo:participantInfos){
        TokenRaffleVo  tokenRaffleVo = new TokenRaffleVo();
        RaffleInfo raffleInfo =  raffleInfoMapper.getDetailByRaffleAddress(participantInfo.getRaffleaddress());
        //纠正DB状态偏差
        if(null !=raffleInfo & !(new BigInteger("4").equals(raffleInfo.getRafflestatus())||new BigInteger("5").equals(raffleInfo.getRafflestatus()))){
          raffleInfo = raffleInfoService.correctStatus(raffleInfo);
        }
        tokenRaffleVo.setRaffleInfo(raffleInfo);
        TokenInfo tokenInfo = tokenInfoMapper.selectByTokenId(raffleInfo.getContractAddress(), raffleInfo.getTokenId());
        tokenRaffleVo.setTokenInfo(tokenInfo);
        list.add(tokenRaffleVo);
      }
        return list;
    }

    @Override
    public int createParticipantInfo(ParticipantInfo participantInfo) {
        return participantInfoMapper.insertSelective(participantInfo);
    }
}
