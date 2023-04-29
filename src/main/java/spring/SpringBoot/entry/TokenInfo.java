package spring.SpringBoot.entry;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * token_info
 * @author 
 */
@Data
public class TokenInfo implements Serializable {
    /**
     * 用户编号
     */
    private Integer id;

    /**
     * tokenID
     */
    private String tokenId;

    /**
     * 合约地址
     */
    private String contractAddress;

    /**
     * 所有者
     */
    private String owner;

    /**
     * 合约类型0：721；1：1155
     */
    private Integer nftstandardId;

    /**
     * token image
     */
    private String image;

    /**
     * name
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}