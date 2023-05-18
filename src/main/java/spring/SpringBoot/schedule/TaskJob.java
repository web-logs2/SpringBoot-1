package spring.SpringBoot.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.StaticGasProvider;
import spring.SpringBoot.service.RaffleContractService;
import spring.SpringBoot.service.impl.ContractService;
import spring.SpringBoot.solidity.LeaveMsg;
import spring.SpringBoot.solidity.NRaffle;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Component("taskJob")
public class TaskJob {
    @Autowired
    ContractService contractService;
    @Autowired
    RaffleContractService raffleContractService;

    @Scheduled(cron = "0 */30 * * * ?")
//@Scheduled(cron = "0 */1 * * * ?")
public void job1() throws Exception {
        System.out.println("transferAllIfCompletedWithNFT定义的定时开始执行"+ LocalDateTime.now() );
        raffleContractService.execSwapTransferAllIfCompletedWithNFT();
        System.out.println("transferAllIfCompletedWithNFT执行结束"+ LocalDateTime.now() );

    }

//    @Scheduled(cron = "0 0 */12 * * ?")
    @Scheduled(cron = "0 */30 * * * ?")
//    @Scheduled(cron = "0 */1 * * * ?")
    public void execTransferAllIfCancelledJob() throws Exception {
        System.out.println("execTransferAllIfCancelled定义的定时开始执行"+ LocalDateTime.now() );
        raffleContractService.execTransferAllIfCancelled();
        System.out.println("execTransferAllIfCancelled执行结束"+ LocalDateTime.now() );

    }

    /**
     * 到期未售完变更取消状态
     * @throws Exception
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void cancelIfUnsoldJob() throws Exception {
        System.out.println("cancelIfUnsoldJob定义的定时开始执行"+ LocalDateTime.now() );
        raffleContractService.execCancelIfUnsold();
        System.out.println("cancelIfUnsoldJob执行结束"+ LocalDateTime.now() );

    }
}
