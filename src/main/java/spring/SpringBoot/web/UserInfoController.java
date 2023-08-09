package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entry.UserInfo;
import spring.SpringBoot.service.UserInfoService;
import spring.SpringBoot.service.impl.RaffleContractServiceImpl;
import spring.SpringBoot.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @RequestMapping("/getAccounts")
    public Object getAccounts() {
        List<UserInfo> userInfos = userInfoService.getUserInfos();
        return ResponseUtil.ok(userInfos);
    }

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/getAccountById")
    public Object getAccountById(@RequestParam Integer id) {
        UserInfo userInfo = userInfoService.selectAccount(id);
        return ResponseUtil.ok(userInfo);
    }

    /**
     * 根据钱包地址获取用户信息
     *
     * @param walletId
     * @return
     */
    @RequestMapping("/getAccountInfo")
    public Object getAccountInfo(@RequestParam String walletId) {
        UserInfo userInfo = userInfoService.selectWalletId(walletId);
        return ResponseUtil.ok(userInfo);
    }

    /**
     * 注册新账户，绑定邮箱（必填）其他非必填
     *
     * @param userInfo
     * @return
     */
    @RequestMapping("/register")
    public Object register(@RequestBody UserInfo userInfo) {
        Object result = userInfoService.register(userInfo);
        if (result.equals(-1)) {
            return ResponseUtil.fail(-1, "注册失败");
        }
        return ResponseUtil.ok(result);
    }

    /**
     * 注销账户
     *
     * @param walletId
     * @return
     */
    @RequestMapping("/cancelAccount")
    public Object cancelAccount(@RequestParam String walletId) {
        Object result = userInfoService.cancel(walletId);
        if (result.equals(-1)) {
            return ResponseUtil.fail(-1, "注销失败");
        }
        return ResponseUtil.ok(result);
    }

    /**
     * 更新账户信息，
     * 只更新昵称，邮箱，头像，描述信息
     *
     * @param userInfo
     * @return
     */
    @RequestMapping("/updateAccount")
    public Object updateAccount(@RequestBody UserInfo userInfo) {
        Object result = userInfoService.updateAccount(userInfo);
        if (!result.equals(1)) {
            return ResponseUtil.fail(-1, "更新账户信息失败");
        }
        return ResponseUtil.ok(result);
    }
}
