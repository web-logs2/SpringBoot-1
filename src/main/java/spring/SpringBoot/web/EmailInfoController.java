package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entry.EmailInfo;
import spring.SpringBoot.service.EmailInfoService;
import spring.SpringBoot.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/emailInfo")
public class EmailInfoController {
    @Autowired
    EmailInfoService emailInfoService;

    /**
     * 获取用户email列表
     *
     * @return
     */
    @RequestMapping("/getEmailInfoList")
    public Object getEmailInfoList() {
        List<EmailInfo> emailInfos = emailInfoService.getEmailInfoList();
        return ResponseUtil.ok(emailInfos);
    }

    /**
     * 保存邮箱
     *
     * @param emailInfo
     * @return
     */
    @RequestMapping("/createEmailInfo")
    public Object createEmailInfo(@RequestBody EmailInfo emailInfo) {
        Object result = emailInfoService.createEmailInfo(emailInfo);
        if (result.equals(-1)) {
            return ResponseUtil.fail(-1, "createEmailInfo fail!");
        }
        return ResponseUtil.ok(result);
    }
}
