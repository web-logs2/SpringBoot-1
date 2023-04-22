package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.UserInfo;
import spring.SpringBoot.mapper.UserInfoMapper;
import spring.SpringBoot.service.UserInfoService;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getUserInfos() {
        return userInfoMapper.getUserInfos();
    }

    @Override
    public int creatAccount(UserInfo userInfo) {
        return 0;
    }

    @Override
    public UserInfo selectAccount(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
}
