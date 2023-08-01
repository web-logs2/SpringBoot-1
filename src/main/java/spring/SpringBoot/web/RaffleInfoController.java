package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.service.RaffleInfoService;
import spring.SpringBoot.utils.ResponseUtil;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/raffleInfo")
public class RaffleInfoController {

    @Autowired
    RaffleInfoService raffleInfoService;

    /**
     * 获取当前用户所有活动列表(包含图片，name)
     *
     * @return
     */
    @RequestMapping("/getRaffleInfoListByOwner")
    public Object getRaffleInfoListByOwner(@RequestParam("owner") String owner) {
        List<TokenRaffleVo> tokenRaffleVos = raffleInfoService.getRaffleInfoListByOwner(owner);
        return ResponseUtil.ok(tokenRaffleVos);
    }


    /**
     *创建夺宝活动
     * @param raffleInfo
     * @return
     */
    @RequestMapping("/createRaffleInfo")
    public Object createRaffleInfo(@RequestBody RaffleInfo raffleInfo) {
        Object result = raffleInfoService.createRaffleInfo(raffleInfo);
        if (result.equals(-1)) {
            return ResponseUtil.fail(-1, "createRaffleInfo fail!");
        }
        return ResponseUtil.ok(result);
    }

    /**
     *根据活动id：raffleAddress更新活动状态raffleStatus和king
     * @param
     * @return
     */
    @RequestMapping("/updateRaffleInfo")
    public Object updateRaffleInfo(@RequestBody RaffleInfo raffleInfo) {
        Object result = raffleInfoService.updateRaffleInfo(raffleInfo);
        return ResponseUtil.ok(result);
    }


    /**
     *根据条件查询个人维度存在的活动(非取消，非完成状态)
     * @param map
     * @return
     */
    @RequestMapping("/getRaffleInfoByCondition")
    public Object getRaffleInfoByCondition(@RequestParam Map<String,Object> map) {
        Object result = raffleInfoService.getRaffleInfoByCondition(map);
        return ResponseUtil.ok(result);
    }

    /**
     * 根据活动id获取raffletoken详情信息
     * @param raffleAddress
     * @return
     */
    @RequestMapping("/getDetailByRaffleAddress")
    public Object getDetailByRaffleAddress(@RequestParam String raffleAddress) {
        TokenRaffleVo tokenRaffleVo= raffleInfoService.getDetailByRaffleAddress(raffleAddress);
        return ResponseUtil.ok(tokenRaffleVo);
    }
    /**
     * 根据活动id获取raffle详情信息
     * @param raffleAddress
     * @return
     */
    @RequestMapping("/getRaffleDetailByRaffleAddress")
    public Object getRaffleDetailByRaffleAddress(@RequestParam String raffleAddress) {
        RaffleInfo raffleInfo= raffleInfoService.getRaffleDetailByRaffleAddress(raffleAddress);
        return ResponseUtil.ok(raffleInfo);
    }

}
