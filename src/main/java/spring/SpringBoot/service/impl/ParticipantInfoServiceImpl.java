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
    public List<TokenRaffleVo> getParticipantInfos(String participantAddress,int pageNumber, int pageSize,String chainId) {
      int offset = pageNumber * pageSize;
      int limit = pageSize;
      List<ParticipantInfo> participantInfos = participantInfoMapper.getParticipantInfosGroupByRaffleAddress(participantAddress,
              offset,limit,chainId);
      List<TokenRaffleVo> list = new ArrayList<>();
      for(ParticipantInfo participantInfo:participantInfos){
        TokenRaffleVo  tokenRaffleVo = new TokenRaffleVo();
        RaffleInfo raffleInfo =  raffleInfoMapper.getDetailByRaffleAddress(participantInfo.getRaffleaddress());
        Integer participantCount = participantInfoMapper.getParticipantCount(participantInfo.getRaffleaddress());
        raffleInfo.setParticipants(null == participantCount?0:participantCount);
        Integer myTickets = participantInfoMapper.getMytickets(participantInfo.getRaffleaddress(),participantAddress);
        tokenRaffleVo.setMyTickets(null == myTickets?0:myTickets);
        //纠正DB状态偏差
        raffleInfo = raffleInfoService.correctStatus(raffleInfo);
        tokenRaffleVo.setRaffleInfo(raffleInfo);
        TokenInfo tokenInfo = tokenInfoMapper.selectByTokenId(raffleInfo.getContractAddress(), raffleInfo.getTokenId());
        tokenRaffleVo.setTokenInfo(tokenInfo);
        list.add(tokenRaffleVo);
      }
        return list;
    }

    @Override
    public int createParticipantInfo(ParticipantInfo participantInfo) {
      RaffleInfo raffleInfo = new RaffleInfo();
      int raffleAssets = raffleInfoMapper.getDetailByRaffleAddress(participantInfo.getRaffleaddress()).getRaffleAssets();
      if(2==raffleAssets || 1==raffleAssets){
        raffleInfo.setRaffleAssets(999);
      }
      if(0==raffleAssets){
        raffleInfo.setRaffleAssets(1);
      }
      raffleInfoMapper.updateRaffleInfo(raffleInfo);
      int participants = participantInfoMapper.getParticipantInfosByParticipantInfo(participantInfo).size();
      if(0==participants){
        return participantInfoMapper.insertSelective(participantInfo);
      }else{
        return  -1;
      }
    }
}
