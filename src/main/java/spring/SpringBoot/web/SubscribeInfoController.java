package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entry.SubscribeInfo;
import spring.SpringBoot.entry.UserInfo;
import spring.SpringBoot.service.SubscribeInfoService;
import spring.SpringBoot.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/subscribeInfo")
public class SubscribeInfoController {

    @Autowired
    SubscribeInfoService subscribeInfoService;
    /**
     * 获取当前用户的订阅者列表
     *
     * @return
     */
    @RequestMapping("/getSubscribeeList")
    public Object getSubscribeeList(@RequestParam String subscriberWallet) {
        List<UserInfo> userInfos = subscribeInfoService.getSubscribeeList(subscriberWallet);
        if (null!= userInfos){
            return ResponseUtil.ok(userInfos);
        }
        return ResponseUtil.ok("您还未订阅任何作者，赶紧去订阅吧！");
    }

    /**
     * 订阅&取消订阅接口，1：订阅，2：取消订阅
     *
     * @return
     */
    @RequestMapping("/subscribe")
    public Object subscribe(@RequestBody  SubscribeInfo subscribeInfo) {
        int result = subscribeInfoService.subscribe(subscribeInfo);
        if (result == 1){
            return ResponseUtil.ok("订阅&取消订阅成功！");
        }
        return ResponseUtil.ok("订阅&取消订阅失败！");
    }

}
