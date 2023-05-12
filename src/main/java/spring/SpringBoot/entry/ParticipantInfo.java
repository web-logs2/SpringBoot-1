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
    private String participantAddress;

    /**
     * raffleAddress
     */
    private String raffleaddress;

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

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getParticipantAddress() {
        return participantAddress;
    }

    public void setParticipantAddress(String participantAddress) {
        this.participantAddress = participantAddress;
    }

    public String getRaffleaddress() {
        return raffleaddress;
    }

    public void setRaffleaddress(String raffleaddress) {
        this.raffleaddress = raffleaddress;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }



    private static final long serialVersionUID = 1L;
}