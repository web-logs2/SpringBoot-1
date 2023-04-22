package spring.SpringBoot.mapper;

import spring.SpringBoot.entry.SubscribeInfo;

public interface SubscribeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SubscribeInfo record);

    int insertSelective(SubscribeInfo record);

    SubscribeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SubscribeInfo record);

    int updateByPrimaryKey(SubscribeInfo record);
}