package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.service.RaffleInfoService;
import spring.SpringBoot.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/raffleInfo")
public class RaffleInfoController {

    @Autowired
    RaffleInfoService raffleInfoService;

    /**
     * 获取所有活动列表
     *
     * @return
     */
    @RequestMapping("/getRaffleInfoList")
    public Object getRaffleInfoList() {
        List<RaffleInfo> raffleInfos = raffleInfoService.getRaffleInfoList();
        return ResponseUtil.ok(raffleInfos);
    }


}
