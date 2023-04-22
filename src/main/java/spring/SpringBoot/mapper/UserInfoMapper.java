package spring.SpringBoot.mapper;

import org.springframework.stereotype.Repository;
import spring.SpringBoot.entry.UserInfo;

import java.util.List;

@Repository
public interface UserInfoMapper {

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateAccount(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> getUserInfos();

    UserInfo selectWalletId(String walletId);

    int deleteWalletId(String walletId);
}