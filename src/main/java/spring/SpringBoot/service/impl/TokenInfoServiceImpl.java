package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.TokenInfo;
import spring.SpringBoot.mapper.TokenInfoMapper;
import spring.SpringBoot.service.TokenInfoService;

@Service
public class TokenInfoServiceImpl implements TokenInfoService {

    @Autowired
    TokenInfoMapper tokenInfoMapper;

    @Override
    public TokenInfo getTokenInfoByOwner(String owner) {
        return tokenInfoMapper.selectByOwner(owner);
    }

    @Override
    public int createTokenInfo(TokenInfo tokenInfo) {
        if (null != tokenInfo && null != tokenInfo.getContractAddress() && "" != tokenInfo.getContractAddress() &&
                null != tokenInfo.getTokenId() && "" != tokenInfo.getTokenId()) {
            TokenInfo tokenInfoExist = tokenInfoMapper.selectByTokenId(tokenInfo.getContractAddress(), tokenInfo.getTokenId());
            if (null!=tokenInfoExist) {
                return 2;
            }
            return tokenInfoMapper.insertSelective(tokenInfo);
        }
        return -1;
    }
}
