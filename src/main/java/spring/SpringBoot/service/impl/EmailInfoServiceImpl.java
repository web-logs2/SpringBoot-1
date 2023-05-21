package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entry.EmailInfo;
import spring.SpringBoot.mapper.EmailInfoMapper;
import spring.SpringBoot.service.EmailInfoService;

import java.util.List;

@Service
public class EmailInfoServiceImpl implements EmailInfoService {

    @Autowired
    EmailInfoMapper emailInfoMapper;

    @Override
    public List<EmailInfo> getEmailInfoList() {
        return emailInfoMapper.getEmailInfoList();
    }

    @Override
    public int createEmailInfo(EmailInfo emailInfo) {
        EmailInfo result = emailInfoMapper.selectByEmail(emailInfo.getEmail());
        if (null != result) {
            return -1;
        }
        return emailInfoMapper.insertSelective(emailInfo);
    }
}
