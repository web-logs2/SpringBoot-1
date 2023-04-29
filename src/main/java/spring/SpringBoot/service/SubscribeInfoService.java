package spring.SpringBoot.service;

import spring.SpringBoot.entry.SubscribeInfo;
import spring.SpringBoot.entry.UserInfo;

import java.util.List;

public interface SubscribeInfoService {

    List<UserInfo> getSubscribeeList(String subscriberWallet);

    int subscribe(SubscribeInfo subscribeInfo);

}
