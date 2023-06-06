package spring.SpringBoot.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.entry.TokenInfo;

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

    RaffleInfo getWaitingForNftRaffleInfos(@Param("contractAddress") String contractAddress, @Param("tokenId") String tokenId,@Param("owner") String owner);

    //以下是定时任务的查询
    List<RaffleInfo> getExecSwapRaffleInfos();

    List<RaffleInfo> getTransferAllIfCancelledRaffleInfos();

    List<RaffleInfo> getCancelIfUnsoldRaffleInfos();

    List<RaffleInfo> getExecRetryIfNoRNGRaffleInfos();

}