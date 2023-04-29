package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.service.TokenInfoService;

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
        return tokenInfoService.getTokenInfoById(id);
    }
}
