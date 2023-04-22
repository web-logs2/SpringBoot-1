package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entry.UserInfo;
import spring.SpringBoot.service.UserInfoService;

import java.util.List;

@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/getAccounts")
    public List<UserInfo> getAccounts() {
        return userInfoService.getUserInfos();
    }
    @RequestMapping("/getAccount")
    public UserInfo getAccounts(@RequestParam Integer id) {
        return userInfoService.selectAccount(id);
    }
}
