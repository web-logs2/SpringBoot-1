package spring.SpringBoot.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.ParticipantInfo;
import spring.SpringBoot.mapper.ParticipantInfoMapper;
import spring.SpringBoot.service.ParticipantInfoService;

import java.util.List;

@Service
public class ParticipantInfoServiceImpl implements ParticipantInfoService {

    @Autowired
    ParticipantInfoMapper participantInfoMapper;
    @Override
    public List<ParticipantInfo> getParticipantInfos(String userAddress) {
        return participantInfoMapper.getParticipantInfos(userAddress);
    }
}
