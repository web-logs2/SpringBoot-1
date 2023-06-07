package spring.SpringBoot.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * token_info
 * @author 
 */
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
     * chain
     */
    private String chain;


    /**
     * RaffleAddress
     */
    private String waitingForNftRaffleAddress;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getNftstandardId() {
        return nftstandardId;
    }

    public void setNftstandardId(Integer nftstandardId) {
        this.nftstandardId = nftstandardId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
    public String getWaitingForNftRaffleAddress() {
        return waitingForNftRaffleAddress;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public void setWaitingForNftRaffleAddress(String waitingForNftRaffleAddress) {
        this.waitingForNftRaffleAddress = waitingForNftRaffleAddress;
    }

    private static final long serialVersionUID = 1L;
}