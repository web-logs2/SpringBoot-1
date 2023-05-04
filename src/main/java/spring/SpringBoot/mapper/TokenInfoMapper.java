package spring.SpringBoot.mapper;

import org.springframework.stereotype.Repository;
import spring.SpringBoot.entry.TokenInfo;

@Repository
public interface TokenInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TokenInfo record);

    int insertSelective(TokenInfo record);

    TokenInfo selectByOwner(String owner);

    TokenInfo selectByTokenId(String contractAddress, String tokenId);

    int updateByPrimaryKeySelective(TokenInfo record);

    int updateByPrimaryKey(TokenInfo record);
}