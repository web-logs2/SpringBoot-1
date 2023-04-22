package spring.SpringBoot.service;

import spring.SpringBoot.entry.UserInfo;

import java.util.List;

public interface UserInfoService {
    List<UserInfo> getUserInfos();

    int creatAccount(UserInfo userInfo);

    UserInfo selectAccount(Integer id);
}
