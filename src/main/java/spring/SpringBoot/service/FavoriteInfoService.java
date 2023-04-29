package spring.SpringBoot.service;

import spring.SpringBoot.entry.FavoriteInfo;

import java.util.List;

public interface FavoriteInfoService {
    List<FavoriteInfo> getFavoriteInfoList(String subscriberWallet);
}
