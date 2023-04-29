package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entry.FavoriteInfo;
import spring.SpringBoot.service.FavoriteInfoService;
import spring.SpringBoot.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/favoriteinfo")
public class FavoriteInfoController {
    @Autowired
    FavoriteInfoService favoriteInfoService;

    /**
     * 获取用户关注的 活动列表
     *
     * @return
     */
    @RequestMapping("/getFavoriteInfoList")
    public Object getFavoriteInfoList(@RequestParam String subscriberWallet) {
        List<FavoriteInfo> favoriteInfos = favoriteInfoService.getFavoriteInfoList(subscriberWallet);
        return ResponseUtil.ok(favoriteInfos);
    }

}
