package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.entry.TokenInfo;
import spring.SpringBoot.mapper.RaffleInfoMapper;
import spring.SpringBoot.mapper.TokenInfoMapper;
import spring.SpringBoot.service.TokenInfoService;

import java.util.List;

@Service
public class TokenInfoServiceImpl implements TokenInfoService {

    @Autowired
    TokenInfoMapper tokenInfoMapper;

    @Autowired
    RaffleInfoMapper raffleInfoMapper;

    @Override
    public List<TokenInfo> getTokenInfoByOwner(String owner) {
        List<TokenInfo> tokenInfos = tokenInfoMapper.selectByOwner(owner);
        for(TokenInfo tokenInfo:tokenInfos){
            RaffleInfo raffleInfo =  raffleInfoMapper.getWaitingForNftRaffleInfos(tokenInfo.getContractAddress(),tokenInfo.getTokenId(),tokenInfo.getOwner());
            if(null != raffleInfo){
                String RaffleAddress = raffleInfo.getRaffleaddress();
                tokenInfo.setWaitingForNftRaffleAddress(RaffleAddress);
            }
        }
        return tokenInfos;
    }

    @Override
    public int createTokenInfo(TokenInfo tokenInfo) {
        //增加一个是不是本人nft的校验。
        if (null != tokenInfo && null != tokenInfo.getContractAddress() && "" != tokenInfo.getContractAddress() &&
                null != tokenInfo.getTokenId() && "" != tokenInfo.getTokenId()) {
            if (""==tokenInfo.getImage() &&""==tokenInfo.getName() && ""==tokenInfo.getDesc()){
                return 3;
            }
            TokenInfo tokenInfoExist = tokenInfoMapper.selectByTokenId(tokenInfo.getContractAddress(), tokenInfo.getTokenId());
            if (null!=tokenInfoExist) {
                return 2;
            }
            return tokenInfoMapper.insertSelective(tokenInfo);
        }
        return -1;
    }


}
