package spring.SpringBoot.mapper;

import org.springframework.stereotype.Repository;
import spring.SpringBoot.entry.RaffleInfo;

import java.util.List;

@Repository
public interface RaffleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RaffleInfo record);

    int insertSelective(RaffleInfo record);

    RaffleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RaffleInfo record);

    int updateByPrimaryKey(RaffleInfo record);

    List<RaffleInfo> getRaffleInfoList();
}