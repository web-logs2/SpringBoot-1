package spring.SpringBoot.service;

import spring.SpringBoot.entry.TokenInfo;

import java.util.List;

public interface TokenInfoService {
    List<TokenInfo> getTokenInfoByOwner(String owner);

    int createTokenInfo(TokenInfo tokenInfo);
}
