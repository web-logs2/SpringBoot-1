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
    public TokenInfo getTokenInfoById(int id) {
        return tokenInfoMapper.selectByPrimaryKey(id);
    }
}
