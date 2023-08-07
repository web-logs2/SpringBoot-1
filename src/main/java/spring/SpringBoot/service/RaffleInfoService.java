package spring.SpringBoot.service;

import org.springframework.web.bind.annotation.RequestParam;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.util.List;
import java.util.Map;

public interface RaffleInfoService {
    List<TokenRaffleVo> getRaffleInfoListByOwner(String owner);

    int createRaffleInfo(RaffleInfo raffleInfo);

    int updateRaffleInfo(RaffleInfo raffleInfo);

    RaffleInfo getRaffleInfoByCondition(Map map);

    TokenRaffleVo getDetailByRaffleAddress(String raffleAddress);

    RaffleInfo  getRaffleDetailByRaffleAddress(String raffleAddress);

    List<TokenRaffleVo> findAll(String owner,int pageNumber, int pageSize,String chainId);

    RaffleInfo correctStatus(RaffleInfo raffleInfo);

}
