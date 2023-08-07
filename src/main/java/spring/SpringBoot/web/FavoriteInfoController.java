package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entry.FavoriteInfo;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.mapper.FavoriteInfoMapper;
import spring.SpringBoot.service.FavoriteInfoService;
import spring.SpringBoot.utils.ResponseUtil;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.util.List;

@RestController
@RequestMapping("/api/favoriteInfo")
public class FavoriteInfoController {
    @Autowired
    FavoriteInfoService favoriteInfoService;

    @Autowired
    FavoriteInfoMapper favoriteInfoMapper;

    /**
     * 获取用户关注的 活动列表
     *
     * @return
     */
    @RequestMapping("/getFavoriteInfoList")
    public Object getFavoriteInfoList(
            @RequestParam String subscriberWallet,
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam String chainId

    ) {
        List<TokenRaffleVo>  favoriteInfos = favoriteInfoService.getFavoriteInfoList(subscriberWallet,pageNumber,pageSize,chainId);
        return ResponseUtil.ok(favoriteInfos);
    }

    /**
     * 创建关注信息
     *
     * @return
     */
    @RequestMapping("/createFavorite")
    public Object createFavorite(@RequestBody FavoriteInfo favoriteInfo) {
        int number = favoriteInfoMapper.getFavoriteInfoListbyRaffleAndSubscriber(favoriteInfo.getSubscriberWallet(),favoriteInfo.getRaffleaddress()).size();
        if(number>0){
            return ResponseUtil.fail(-2, "\n" +
                    "You have already subscribed. Please do not subscribe again.");
        }
        Object  favoriteInfos = favoriteInfoService.insertFavoriteInfo(favoriteInfo);
        if (favoriteInfos.equals(-1)) {
            return ResponseUtil.fail(-1, "create FavoriteInfo fail!");
        }
        return ResponseUtil.ok(favoriteInfos);
    }

}
