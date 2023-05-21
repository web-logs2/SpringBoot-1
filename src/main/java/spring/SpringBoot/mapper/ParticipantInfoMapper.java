package spring.SpringBoot.mapper;

import org.apache.ibatis.annotations.Param;
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

    List<ParticipantInfo> getParticipantInfosByRaffleAddress(String raffleaddress);

    List<ParticipantInfo>  getParticipantInfosGroupByRaffleAddress(String participantAddress);

    List<ParticipantInfo> ParticipantInfos(String raffleaddress);


    Integer getParticipantCount(@Param("raffleaddress") String raffleaddress);

}