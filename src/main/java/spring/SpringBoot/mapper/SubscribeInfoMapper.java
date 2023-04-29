package spring.SpringBoot.mapper;

import org.springframework.stereotype.Repository;
import  spring.SpringBoot.entry.SubscribeInfo;

import java.util.List;

@Repository
public interface SubscribeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SubscribeInfo record);

    int insertSelective(SubscribeInfo record);

    SubscribeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SubscribeInfo record);

    int updateByPrimaryKey(SubscribeInfo record);

    List<SubscribeInfo> getSubscribees(String subscriberWallet);

    int updateSubscribeStatus(SubscribeInfo subscribeInfo);

}