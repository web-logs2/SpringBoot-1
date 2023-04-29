package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.mapper.RaffleInfoMapper;
import spring.SpringBoot.service.RaffleInfoService;

import java.util.List;

@Service
public class RaffleInfoServiceImpl implements RaffleInfoService {

    @Autowired
    RaffleInfoMapper raffleInfoMapper;

    @Override
    public List<RaffleInfo> getRaffleInfoList() {
        return raffleInfoMapper.getRaffleInfoList();
    }
}
