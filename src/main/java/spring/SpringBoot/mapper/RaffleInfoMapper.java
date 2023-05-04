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

    RaffleInfo selectByPrimaryKey(Integer id);

    int updateRaffleInfo(RaffleInfo record);

    int updateByPrimaryKey(RaffleInfo record);

    List<RaffleInfo> getRaffleInfoList();

    RaffleInfo getRaffleInfoByCondition(Map map);
}