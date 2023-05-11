package spring.SpringBoot.entry;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * raffle_info
 * @author 
 */
@Data
public class RaffleInfo implements Serializable {
    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 活动id：RaffleAddress地址唯一
     */
    private String raffleaddress;

    /**
     * 所有者
     */
    private String owner;

    /**
     * tokenID
     */
    private String tokenId;

    /**
     * 合约地址
     */
    private String contractAddress;

    /**
     *  tickets 销售总份数
     */
    private Integer tickets;

    /**
     *  tickets 单价
     */
    private Double ticketprice;

    /**
     * 抽奖活动开始时间（时间戳）
     */
    private Long starttimestamp;

    /**
     * 抽奖活动结束时间（时间戳）
     */
    private Long endtimestamp;

    /**
     *  抽奖活动状态
     */
    private Integer rafflestatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private String king;

    /**
     * 0: no swap, 1: eth, 2: nft

     */
    private Integer swapStatus;

    /**
     * 兑换窗口期
     */
    private Long swapDuration;

    private static final long serialVersionUID = 1L;
}