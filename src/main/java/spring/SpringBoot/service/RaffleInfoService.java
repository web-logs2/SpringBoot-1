package spring.SpringBoot.service;

import spring.SpringBoot.entry.RaffleInfo;

import java.util.List;
import java.util.Map;

public interface RaffleInfoService {
    List<RaffleInfo> getRaffleInfoList();

    int createRaffleInfo(RaffleInfo raffleInfo);

    int updateRaffleInfo(RaffleInfo raffleInfo);

    RaffleInfo getRaffleInfoByCondition(Map map);
}
