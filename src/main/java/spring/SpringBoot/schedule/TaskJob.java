package spring.SpringBoot.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.SpringBoot.service.RaffleContractService;
import spring.SpringBoot.service.impl.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Component("taskJob")
public class TaskJob {
    @Autowired
    ContractService contractService;
    @Autowired
    RaffleContractService raffleContractService;

    private static final Logger logger = LoggerFactory.getLogger(TaskJob.class);

    /**
     * 测试完成
     *
     * king 没有选择后，默认选择NFT,每天的凌晨1点执行
     * 条件：swap_status = 0 AND king IS NOT NULL AND UNIX_TIMESTAMP(NOW()) > (winnerDrawTimestamp + (2 * 24 * 60 * 60))
     **/
    @Scheduled(cron = "0 0 1 * * ?")
   public void job1()  {
        logger.info("transferAllIfCompletedWithNFT定义的定时开始执行{}", LocalDateTime.now());
        try {
            raffleContractService.execSwapTransferAllIfCompletedWithNFT();
        }catch (Exception e){
            logger.info("transferAllIfCompletedWithNFT exec error{}", e);

        }
        logger.info("transferAllIfCompletedWithNFT执行结束{}", LocalDateTime.now());

    }

    /** 测试完成
     * rafflestatus IN (1, 2) AND UNIX_TIMESTAMP(NOW()) > (endTimestamp+ (2 * 24 * 60 * 60))
     * 到期未完成的变更取消状态
     *合约要求： "Must be in WaitingForNFT or WaitingForStart"
     * 每天0点执行
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void cancelIfUnsoldJob() {
        logger.info("cancelIfUnsoldJob定义的定时开始执行{}", LocalDateTime.now());

        try {
            raffleContractService.execCancelIfUnsold();
        }catch (Exception e) {
            logger.info("cancelIfUnsoldJob exec error{}", e);
        }
        logger.info("cancelIfUnsoldJob执行结束{}", LocalDateTime.now());

    }

    /**
     * 测试完成，问题：资产退回后，db内部没有办法更新，每次调用链上函数时候增加一个监听。监听完成后再kill掉，防止越积累越多
     *取消状态下，退回所有的NFT和Eth
     * ri.rafflestatus = 5 AND ri.raffleAssets != 0 AND (UNIX_TIMESTAMP(NOW()) > ri.endTimestamp + (2 * 24 * 60 * 60) )
     * 每天下午的14点执行
     */
    @Scheduled(cron = "0 0 14 * * ?")
    public void execTransferAllIfCancelledJob() {
        logger.info("execTransferAllIfCancelled定义的定时开始执行{}", LocalDateTime.now());

        try {
            raffleContractService.execTransferAllIfCancelled();
        }catch (Exception e){
            logger.info("execTransferAllIfCancelled exec error,{}", e);

        }
        logger.info("execTransferAllIfCancelled执行结束{}", LocalDateTime.now());

    }

    /**
     * 开奖失败后
     * 开奖失败(状态是：3)，24小时后重试一次？或者是只要大于结束时间就重试。合约函数：block.timestamp > endTimestamp + 1 days
     * WHERE rafflestatus IN ( 3 ) AND UNIX_TIMESTAMP(NOW()) > (endTimestamp+ (1 * 24 * 60 * 60))
     */
    @Scheduled(cron = "0 0 15 * * ?")
    public void retryIfNoRNGJob() {
        logger.info("retryIfNoRNG定义的定时开始执行{}", LocalDateTime.now());

        try {
            raffleContractService.execRetryIfNoRNG();
        }catch (Exception e){
            logger.info("retryIfNoRNG exec error,{}", e);

        }
        logger.info("retryIfNoRNG执行结束{}", LocalDateTime.now());

    }
}
