package spring.SpringBoot.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.SpringBoot.service.RaffleContractService;
import spring.SpringBoot.service.impl.ContractService;

import java.time.LocalDateTime;

@Component("taskJob")
public class TaskJob {
    @Autowired
    ContractService contractService;
    @Autowired
    RaffleContractService raffleContractService;

    /**
     *
     *
     * //king 没有选择后，默认选择NFT,每天的凌晨1点执行
     * 条件：swap_status = 0 AND king IS NOT NULL AND UNIX_TIMESTAMP(NOW()) > (winnerDrawTimestamp + (2 * 24 * 60 * 60))
     * @throws Exception
     **/
    @Scheduled(cron = "0 0 1 * * ?")
   public void job1() throws Exception {
        System.out.println("transferAllIfCompletedWithNFT定义的定时开始执行"+ LocalDateTime.now() );
        raffleContractService.execSwapTransferAllIfCompletedWithNFT();
        System.out.println("transferAllIfCompletedWithNFT执行结束"+ LocalDateTime.now() );
    }

    /**
     * rafflestatus IN (0, 1, 2) AND UNIX_TIMESTAMP(NOW()) > (endTimestamp+ (2 * 24 * 60 * 60))
     * 到期未完成的变更取消状态
     * 每天0点执行
     * @throws Exception
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void cancelIfUnsoldJob() throws Exception {
        System.out.println("cancelIfUnsoldJob定义的定时开始执行"+ LocalDateTime.now() );
        raffleContractService.execCancelIfUnsold();
        System.out.println("cancelIfUnsoldJob执行结束"+ LocalDateTime.now() );
    }

    /**
     *取消状态下，退回所有的NFT和Eth
     * ri.rafflestatus = 5 AND ri.raffleAssets != 0 AND (UNIX_TIMESTAMP(NOW()) > ri.endTimestamp + (2 * 24 * 60 * 60) )
     * 每天下午的14点执行
     * @throws Exception
     */
    @Scheduled(cron = "0 0 14 * * ?")
    public void execTransferAllIfCancelledJob() throws Exception {
        System.out.println("execTransferAllIfCancelled定义的定时开始执行"+ LocalDateTime.now() );
        raffleContractService.execTransferAllIfCancelled();
        System.out.println("execTransferAllIfCancelled执行结束"+ LocalDateTime.now() );
    }
    /**
     * 更新取消后的db本地的状态，这个等着合约回复吧，看合约那边有没有可以当前合约是否已经退回nft和eth的状态
     */


    /**
     * 开奖失败后
     * 开奖失败，24小时后重试一次
     */
    @Scheduled(cron = "0 0 15 * * ?")
    public void retryIfNoRNGJob() throws Exception {
        System.out.println("retryIfNoRNG定义的定时开始执行"+ LocalDateTime.now() );
        raffleContractService.execRetryIfNoRNG();
        System.out.println("retryIfNoRNG执行结束"+ LocalDateTime.now() );
    }
}
