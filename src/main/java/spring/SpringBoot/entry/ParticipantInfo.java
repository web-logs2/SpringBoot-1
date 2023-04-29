package spring.SpringBoot.entry;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * participant_info
 * @author 
 */
@Data
public class ParticipantInfo implements Serializable {
    /**
     * 用户编号
     */
    private Integer id;

    /**
     * tokenID
     */
    private String tokenId;

    /**
     * 参与者地址
     */
    private String userAddress;

    /**
     * raffleAddress
     */
    private String contractAddress;

    /**
     * 参与者票数
     */
    private Integer ticket;

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