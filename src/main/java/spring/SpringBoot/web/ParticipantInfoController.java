package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entry.ParticipantInfo;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.service.ParticipantInfoService;
import spring.SpringBoot.utils.ResponseUtil;
import spring.SpringBoot.vo.TokenRaffleVo;

import java.util.List;

@RestController
@RequestMapping("/api/participantInfo")
public class ParticipantInfoController {
    @Autowired
    ParticipantInfoService participantInfoService;

    /**
     * 获取用户
     *
     * @return
     */
    @RequestMapping("/getParticipantInfoList")
    public Object getParticipantInfoList(@RequestParam String userAddress) {
      List<TokenRaffleVo> participantInfos = participantInfoService.getParticipantInfos(userAddress);
        return ResponseUtil.ok(participantInfos);
    }

    /**
     *创建夺宝活动
     * @param participantInfo
     * @return
     */
    @RequestMapping("/createParticipantInfo")
    public Object createParticipantInfo(@RequestBody ParticipantInfo participantInfo) {
        Object result = participantInfoService.createParticipantInfo(participantInfo);
        if (result.equals(-1)) {
            return ResponseUtil.fail(-1, "createParticipantInfo fail!");
        }
        return ResponseUtil.ok(result);
    }

}
