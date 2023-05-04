package spring.SpringBoot.mapper;

import org.springframework.stereotype.Repository;
import spring.SpringBoot.entry.ParticipantInfo;

import java.util.List;

@Repository
public interface ParticipantInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParticipantInfo record);

    int insertSelective(ParticipantInfo record);

    ParticipantInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParticipantInfo record);

    int updateByPrimaryKey(ParticipantInfo record);

    List<ParticipantInfo> getParticipantInfos(String participantAddress);
}