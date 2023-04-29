package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entry.ParticipantInfo;
import spring.SpringBoot.service.ParticipantInfoService;
import spring.SpringBoot.utils.ResponseUtil;

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
        List<ParticipantInfo> participantInfos = participantInfoService.getParticipantInfos(userAddress);
        return ResponseUtil.ok(participantInfos);
    }
}
