package spring.SpringBoot.service;

import spring.SpringBoot.entry.FavoriteInfo;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.util.List;

public interface FavoriteInfoService {
    List<TokenRaffleVo>  getFavoriteInfoList(String subscriberWallet);

    int insertFavoriteInfo(FavoriteInfo record);
}
