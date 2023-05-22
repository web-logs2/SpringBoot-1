package spring.SpringBoot.service;

import spring.SpringBoot.entry.EmailInfo;

import java.util.List;

public interface EmailInfoService {
    List<EmailInfo> getEmailInfoList();

    int createEmailInfo(EmailInfo emailInfo);
}
