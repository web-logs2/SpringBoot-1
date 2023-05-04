package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.mapper.RaffleInfoMapper;
import spring.SpringBoot.service.RaffleInfoService;

import java.util.List;
import java.util.Map;

@Service
public class RaffleInfoServiceImpl implements RaffleInfoService {

    @Autowired
    RaffleInfoMapper raffleInfoMapper;

    @Override
    public List<RaffleInfo> getRaffleInfoList() {
        return raffleInfoMapper.getRaffleInfoList();
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

}
