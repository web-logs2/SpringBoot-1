package spring.SpringBoot.mapper;

import org.springframework.stereotype.Repository;
import spring.SpringBoot.entry.EmailInfo;

import java.util.List;

@Repository
public interface EmailInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmailInfo record);

    int insertSelective(EmailInfo record);

    EmailInfo selectByEmail(String email);

    EmailInfo selectByWalletId(String walletId);

    int updateByPrimaryKeySelective(EmailInfo record);

    int updateByPrimaryKey(EmailInfo record);

    List<EmailInfo> getEmailInfoList();
}