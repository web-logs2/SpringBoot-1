package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.FavoriteInfo;
import spring.SpringBoot.entry.ParticipantInfo;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.entry.TokenInfo;
import spring.SpringBoot.mapper.FavoriteInfoMapper;
import spring.SpringBoot.mapper.ParticipantInfoMapper;
import spring.SpringBoot.mapper.RaffleInfoMapper;
import spring.SpringBoot.mapper.TokenInfoMapper;
import spring.SpringBoot.service.FavoriteInfoService;
import spring.SpringBoot.service.RaffleInfoService;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteInfoServiceImpl implements FavoriteInfoService {
    @Autowired
    FavoriteInfoMapper favoriteInfoMapper;

    @Autowired
    RaffleInfoMapper raffleInfoMapper;

    @Autowired
    ParticipantInfoMapper participantInfoMapper;
    @Autowired
    RaffleInfoService raffleInfoService;

    @Autowired
    TokenInfoMapper tokenInfoMapper;

    @Override
    public List<TokenRaffleVo> getFavoriteInfoList(String subscriberWallet,int pageNumber, int pageSize,String chainId) {
        int offset = pageNumber * pageSize;
        int limit = pageSize;
        List<FavoriteInfo> favoriteInfos = favoriteInfoMapper.getFavoriteInfoList(subscriberWallet,offset,limit,chainId);

        List<TokenRaffleVo> list = new ArrayList<>();

        for (FavoriteInfo favoriteInfo : favoriteInfos) {
            TokenRaffleVo tokenRaffleVo = new TokenRaffleVo();
            RaffleInfo raffleInfo = raffleInfoMapper.getDetailByRaffleAddress(favoriteInfo.getRaffleaddress());
            Integer participantCount = participantInfoMapper.getParticipantCount(favoriteInfo.getRaffleaddress());
            raffleInfo.setParticipants(null == participantCount ? 0 : participantCount);
            //纠正DB状态偏差
            raffleInfo = raffleInfoService.correctStatus(raffleInfo);
            tokenRaffleVo.setRaffleInfo(raffleInfo);
            TokenInfo tokenInfo = tokenInfoMapper.selectByTokenId(raffleInfo.getContractAddress(), raffleInfo.getTokenId());
            tokenRaffleVo.setTokenInfo(tokenInfo);
            list.add(tokenRaffleVo);
        }
        return list;
    }

    @Override
    public int insertFavoriteInfo(FavoriteInfo record) {
        return favoriteInfoMapper.insertSelective(record);
    }

}
