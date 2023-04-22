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
    public UserInfo selectAccount(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserInfo selectWalletId(String walletId) {
        return userInfoMapper.selectWalletId(walletId);
    }

    @Override
    public int register(UserInfo userInfo) {
        if (null != userInfo &&
                null != userInfo.getWalletId() && "" != userInfo.getWalletId() &&
                null != userInfo.getEmail() && "" != userInfo.getEmail()) {
            UserInfo userInfoExist = userInfoMapper.selectWalletId(userInfo.getWalletId());
            if (null == userInfoExist) {
                return userInfoMapper.insertSelective(userInfo);
            }
        }
        return -1;
    }

    @Override
    public int cancel(String walletId) {
        return userInfoMapper.deleteWalletId(walletId);
    }

    @Override
    public int updateAccount(UserInfo userInfo) {
        return userInfoMapper.updateAccount(userInfo);
    }
}
