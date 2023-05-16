package spring.SpringBoot.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import spring.SpringBoot.service.RaffleContractService;
import spring.SpringBoot.service.impl.ContractService;
import spring.SpringBoot.solidity.LeaveMsg;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Component("taskJob")
public class TaskJob {
    @Autowired
    ContractService contractService;
    @Autowired
    RaffleContractService raffleContractService;

//    @Scheduled(cron = "0 */10 * * * ?")
@Scheduled(cron = "0 */1 * * * ?")
public void job1() throws Exception {
        System.out.println("transferAllIfCompletedWithNFT定义的定时开始执行"+ LocalDateTime.now() );
        raffleContractService.execSwap();
        System.out.println("transferAllIfCompletedWithNFT执行结束"+ LocalDateTime.now() );

//        //例子：写方法
//        RemoteFunctionCall<TransactionReceipt> setWord = contract.store(BigInteger.valueOf(317));
//        TransactionReceipt transactionReceipt = setWord.sendAsync().get();
//        String transactionHash = transactionReceipt.getTransactionHash();
//        System.out.println("【定时任务】执行交易Hash值："+transactionHash);
//
//        //例子：读方法
//        RemoteFunctionCall<BigInteger> randomWord = contract.retrieve();
//        BigInteger value = randomWord.send();
//        System.out.println("【定时任务】执行读合约返回信息："+value.toString());
    }

//    @Scheduled(cron = "0 0 */12 * * ?")
//    @Scheduled(cron = "0 */10 * * * ?")
    @Scheduled(cron = "0 */1 * * * ?")
    public void execTransferAllIfCancelledJob() throws Exception {
        System.out.println("execTransferAllIfCancelled定义的定时开始执行"+ LocalDateTime.now() );
        raffleContractService.execTransferAllIfCancelled();
        System.out.println("execTransferAllIfCancelled执行结束"+ LocalDateTime.now() );

    }
}
