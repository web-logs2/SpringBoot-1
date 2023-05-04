package spring.SpringBoot.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import spring.SpringBoot.entry.TokenInfo;

import java.util.List;

@Repository
public interface TokenInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TokenInfo record);

    int insertSelective(TokenInfo record);

    List<TokenInfo> selectByOwner(String owner);

    TokenInfo selectByTokenId(@Param("contractAddress") String contractAddress, @Param("tokenId") String tokenId);

    int updateByPrimaryKeySelective(TokenInfo record);

    int updateByPrimaryKey(TokenInfo record);
}