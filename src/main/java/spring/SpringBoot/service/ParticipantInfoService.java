package spring.SpringBoot.service;

import spring.SpringBoot.entry.ParticipantInfo;

import java.util.List;

public interface ParticipantInfoService {
    List<ParticipantInfo> getParticipantInfos(String userAddress);

    int createParticipantInfo(ParticipantInfo participantInfo);
}
