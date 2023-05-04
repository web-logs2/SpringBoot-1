package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entry.TokenInfo;
import spring.SpringBoot.entry.UserInfo;
import spring.SpringBoot.service.TokenInfoService;
import spring.SpringBoot.utils.ResponseUtil;

@RestController
@RequestMapping("/api/tokenInfo")
public class TokenInfoController {

    @Autowired
    TokenInfoService tokenInfoService;

    /**
     * 根据id获取TokenInfo信息
     *
     * @return
     */
    @RequestMapping("/getTokenInfoById")
    public Object getTokenInfoById(@RequestParam int id) {
        return ResponseUtil.ok(tokenInfoService.getTokenInfoById(id));
    }

    /**
     * @param tokenInfo
     * @return
     */
    @RequestMapping("/createTokenInfo")
    public Object createTokenInfo(@RequestBody TokenInfo tokenInfo) {
        Object result = tokenInfoService.createTokenInfo(tokenInfo);
        if (result.equals(2)) {
            return ResponseUtil.fail(2, "token exist!");
        } else if (result.equals(-1)) {
            return ResponseUtil.fail(-1, "create token fail!");
        }
        return ResponseUtil.ok("create token success!");
    }
}
