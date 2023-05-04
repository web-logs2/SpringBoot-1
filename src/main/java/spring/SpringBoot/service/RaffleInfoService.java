package spring.SpringBoot.service;

import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.util.List;
import java.util.Map;

public interface RaffleInfoService {
    List<TokenRaffleVo> getRaffleInfoListByOwner(String owner);

    int createRaffleInfo(RaffleInfo raffleInfo);

    int updateRaffleInfo(RaffleInfo raffleInfo);

    RaffleInfo getRaffleInfoByCondition(Map map);

    RaffleInfo getDetailByRaffleAddress(String raffleAddress);
}
