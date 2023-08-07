package spring.SpringBoot.service;

import org.apache.ibatis.annotations.Param;
import spring.SpringBoot.entry.ParticipantInfo;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.util.List;

public interface ParticipantInfoService {
  List<TokenRaffleVo> getParticipantInfos(String userAddress,int pageNumber, int pageSize,String chainId);

    int createParticipantInfo(ParticipantInfo participantInfo);
}
