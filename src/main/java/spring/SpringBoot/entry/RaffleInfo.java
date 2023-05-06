package spring.SpringBoot.entry;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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
     * txHash 交易hash
     */
    private String txHash;


    /**
     * Raffle 地址唯一
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

    private static final long serialVersionUID = 1L;

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }


    public String getRaffleaddress() {
        return raffleaddress;
    }

    public void setRaffleaddress(String raffleaddress) {
        this.raffleaddress = raffleaddress;
    }


    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }


}