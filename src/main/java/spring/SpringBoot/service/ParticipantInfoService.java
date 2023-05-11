package spring.SpringBoot.service;

import spring.SpringBoot.entry.ParticipantInfo;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.util.List;

public interface ParticipantInfoService {
  List<TokenRaffleVo> getParticipantInfos(String userAddress);

    int createParticipantInfo(ParticipantInfo participantInfo);
}
