package spring.SpringBoot.service;

import spring.SpringBoot.entry.RaffleInfo;

import java.util.List;

public interface RaffleInfoService {
    List<RaffleInfo> getRaffleInfoList();

    int createRaffleInfo(RaffleInfo raffleInfo);
}
