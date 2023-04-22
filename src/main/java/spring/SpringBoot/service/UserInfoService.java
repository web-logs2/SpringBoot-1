package spring.SpringBoot.service;

import spring.SpringBoot.entry.UserInfo;

import java.util.List;

public interface UserInfoService {
    List<UserInfo> getUserInfos();
    
    UserInfo selectAccount(Integer id);

    UserInfo selectWalletId(String walletId);

    int register(UserInfo userInfo);

    int cancel(String walletId);

    int updateAccount(UserInfo userInfo);
}
