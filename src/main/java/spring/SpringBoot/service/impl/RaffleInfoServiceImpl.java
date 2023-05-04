package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.entry.TokenInfo;
import spring.SpringBoot.mapper.RaffleInfoMapper;
import spring.SpringBoot.mapper.TokenInfoMapper;
import spring.SpringBoot.service.RaffleInfoService;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RaffleInfoServiceImpl implements RaffleInfoService {

    @Autowired
    RaffleInfoMapper raffleInfoMapper;

    @Autowired
    TokenInfoMapper tokenInfoMapper;

    @Override
    public List<TokenRaffleVo> getRaffleInfoListByOwner(String owner) {
        TokenRaffleVo  tokenRaffleVo = new TokenRaffleVo();
        List<TokenRaffleVo> list = new ArrayList<>();
        List<RaffleInfo> raffleInfos =  raffleInfoMapper.getRaffleInfoListByOwner(owner);
        for (RaffleInfo raffleInfo:raffleInfos){
            TokenInfo tokenInfo = tokenInfoMapper.selectByTokenId(raffleInfo.getContractAddress(), raffleInfo.getTokenId());
            tokenRaffleVo.setRaffleInfo(raffleInfo);
            tokenRaffleVo.setTokenInfo(tokenInfo);
            list.add(tokenRaffleVo);
        }
        return list;
    }

    @Override
    public int createRaffleInfo(RaffleInfo raffleInfo) {
        return raffleInfoMapper.insertSelective(raffleInfo);
    }

    @Override
    public int updateRaffleInfo(RaffleInfo raffleInfo) {
        return raffleInfoMapper.updateRaffleInfo(raffleInfo);
    }

    @Override
    public RaffleInfo getRaffleInfoByCondition(Map map) {
        return raffleInfoMapper.getRaffleInfoByCondition(map);
    }

    @Override
    public RaffleInfo getDetailByRaffleAddress(String raffleAddress) {
        return raffleInfoMapper.getDetailByRaffleAddress(raffleAddress);
    }


}
