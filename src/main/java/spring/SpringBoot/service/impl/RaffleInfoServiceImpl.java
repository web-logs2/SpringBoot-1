package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.entry.TokenInfo;
import spring.SpringBoot.mapper.RaffleInfoMapper;
import spring.SpringBoot.mapper.TokenInfoMapper;
import spring.SpringBoot.service.RaffleContractService;
import spring.SpringBoot.service.RaffleInfoService;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RaffleInfoServiceImpl implements RaffleInfoService {

    @Autowired
    RaffleInfoMapper raffleInfoMapper;

    @Autowired
    TokenInfoMapper tokenInfoMapper;

    @Autowired
    RaffleContractService raffleContractService;

    @Override
    public List<TokenRaffleVo> getRaffleInfoListByOwner(String owner) {
        List<TokenRaffleVo> list = new ArrayList<>();
        List<RaffleInfo> raffleInfos =  raffleInfoMapper.getRaffleInfoListByOwner(owner);
        for (RaffleInfo raffleInfo:raffleInfos){
          TokenRaffleVo  tokenRaffleVo = new TokenRaffleVo();
          TokenInfo tokenInfo = tokenInfoMapper.selectByTokenId(raffleInfo.getContractAddress(), raffleInfo.getTokenId());
          if(!(new BigInteger("4").equals(raffleInfo.getRafflestatus())||new BigInteger("5").equals(raffleInfo.getRafflestatus()))){
            raffleInfo = correctStatus(raffleInfo);
          }
            tokenRaffleVo.setRaffleInfo(raffleInfo);
            tokenRaffleVo.setTokenInfo(tokenInfo);
            list.add(tokenRaffleVo);
        }
        return list;
    }

  @Override
    public RaffleInfo correctStatus(RaffleInfo raffleInfo){
      BigInteger status = raffleContractService.getState(raffleInfo.getRaffleaddress());
      if(!status.equals(raffleInfo.getRafflestatus())){
        raffleInfo.setRafflestatus(status);
        updateRaffleInfo(raffleInfo);
      }
      return raffleInfo;

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
    public TokenRaffleVo getDetailByRaffleAddress(String raffleAddress) {
        TokenRaffleVo  tokenRaffleVo = new TokenRaffleVo();
        RaffleInfo raffleInfo =  raffleInfoMapper.getDetailByRaffleAddress(raffleAddress);
        TokenInfo tokenInfo = tokenInfoMapper.selectByTokenId(raffleInfo.getContractAddress(), raffleInfo.getTokenId());
        tokenRaffleVo.setRaffleInfo(raffleInfo);
        tokenRaffleVo.setTokenInfo(tokenInfo);
        return tokenRaffleVo;
    }

    @Override
    public RaffleInfo getRaffleDetailByRaffleAddress(String raffleAddress) {
        if (null!=raffleAddress && ""!=raffleAddress){
            return raffleInfoMapper.getDetailByRaffleAddress(raffleAddress);
        }
        return null;
    }
}
