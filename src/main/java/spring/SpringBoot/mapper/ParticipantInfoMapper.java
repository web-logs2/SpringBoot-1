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

//    List<ParticipantInfo> getParticipantInfosByParticipantInfo(@Param("raffleaddress") String raffleaddress,
//                                                               @Param("participantAddress") String participantAddress,
//                                                               @Param("from") Integer from,
//                                                               @Param("to") Integer to);
List<ParticipantInfo> getParticipantInfosByParticipantInfo(ParticipantInfo record);

    List<ParticipantInfo> getParticipantInfosByRaffleAddress(String raffleaddress);

    List<ParticipantInfo>  getParticipantInfosGroupByRaffleAddress(@Param("participantAddress")  String participantAddress,
                                                                   @Param("offset") int offset,
                                                                   @Param("limit") int limit,
                                                                   @Param("chainId") String chainId);
    List<ParticipantInfo> ParticipantInfos(String raffleaddress);


    Integer getParticipantCount(@Param("raffleaddress") String raffleaddress);

    Integer getMytickets(@Param("raffleaddress") String raffleaddress,@Param("participantAddress") String participantAddress);

}