package spring.SpringBoot.entry;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * favorite_info
 * @author 
 */
@Data
public class FavoriteInfo implements Serializable {
    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 订阅者钱包
     */
    private String subscriberWallet;

    /**
     * 活动id
     */
    private String activityId;

    /**
     * 被订阅者钱包
     */
    private String subscribeeWallet;

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