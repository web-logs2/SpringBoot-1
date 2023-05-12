package spring.SpringBoot.mapper;

import org.springframework.stereotype.Repository;
import spring.SpringBoot.entry.RaffleInfo;

import java.util.List;
import java.util.Map;

@Repository
public interface RaffleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RaffleInfo record);

    int insertSelective(RaffleInfo record);

    RaffleInfo getDetailByRaffleAddress(String raffleAddress);

    int updateRaffleInfo(RaffleInfo record);

    int updateByPrimaryKey(RaffleInfo record);

    List<RaffleInfo> getRaffleInfoListByOwner(String owner);

    RaffleInfo getRaffleInfoByCondition(Map map);

    List<RaffleInfo> getExecSwapRaffleInfos();
    List<RaffleInfo> getTransferAllIfCancelledRaffleInfos();
}