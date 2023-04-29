package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.SubscribeInfo;
import spring.SpringBoot.entry.UserInfo;
import spring.SpringBoot.mapper.SubscribeInfoMapper;
import spring.SpringBoot.mapper.UserInfoMapper;
import spring.SpringBoot.service.SubscribeInfoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscribeInfoServiceImpl implements SubscribeInfoService {

    @Autowired
    SubscribeInfoMapper subscribeInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getSubscribeeList(String subscriberWallet) {
        List<SubscribeInfo> subscribeInfos = subscribeInfoMapper.getSubscribees(subscriberWallet);
        List<UserInfo> userInfos = new ArrayList<>();
        for (SubscribeInfo subscribeInfo : subscribeInfos) {
            String subscribeeWallet = subscribeInfo.getSubscribeeWallet();
            userInfos.add(userInfoMapper.selectWalletId(subscribeeWallet));
        }
        return userInfos;

    }

    @Override
    public int subscribe(SubscribeInfo subscribeInfo) {
        if (null != subscribeInfo && null != subscribeInfo.getSubscriberWallet() && "" != subscribeInfo.getSubscriberWallet()) {
            List<SubscribeInfo> subscribees = subscribeInfoMapper.getSubscribees(subscribeInfo.getSubscriberWallet());
            if (null != subscribees) {
                //遍历订阅者的订阅列表，订阅或取消订阅，更新状态
                for (SubscribeInfo subscribee : subscribees) {
                    if (subscribee.getSubscribeeWallet().equals(subscribeInfo.getSubscribeeWallet())) {
                        return subscribeInfoMapper.updateSubscribeStatus(subscribeInfo);
                    }
                }
            }
            return subscribeInfoMapper.insertSelective(subscribeInfo);
        }
        return 0;
    }
}
