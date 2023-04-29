package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.FavoriteInfo;
import spring.SpringBoot.mapper.FavoriteInfoMapper;
import spring.SpringBoot.service.FavoriteInfoService;

import java.util.List;

@Service
public class FavoriteInfoServiceImpl implements FavoriteInfoService {
    @Autowired
    FavoriteInfoMapper favoriteInfoMapper;

    @Override
    public List<FavoriteInfo> getFavoriteInfoList(String subscriberWallet) {
        return favoriteInfoMapper.getFavoriteInfoList(subscriberWallet);
    }

}
