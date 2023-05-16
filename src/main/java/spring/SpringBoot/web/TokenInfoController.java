package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entry.TokenInfo;
import spring.SpringBoot.service.TokenInfoService;
import spring.SpringBoot.utils.ResponseUtil;

@RestController
@RequestMapping("/api/tokenInfo")
public class TokenInfoController {

    @Autowired
    TokenInfoService tokenInfoService;

    /**
     * 根据owner获取TokenInfo信息
     *
     * @return
     */
    @RequestMapping("/getTokenInfoByOwner")
    public Object getTokenInfoByOwner(@RequestParam("owner") String owner) {
        return ResponseUtil.ok(tokenInfoService.getTokenInfoByOwner(owner));
    }

    /**
     * 创建token,如果数据库存在，则返回状态码2，失败返回-1，成功返回200
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
