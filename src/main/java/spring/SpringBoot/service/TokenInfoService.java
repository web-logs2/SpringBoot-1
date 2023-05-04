package spring.SpringBoot.service;

import spring.SpringBoot.entry.TokenInfo;

public interface TokenInfoService {
    TokenInfo getTokenInfoByOwner(String owner);

    int createTokenInfo(TokenInfo tokenInfo);
}
