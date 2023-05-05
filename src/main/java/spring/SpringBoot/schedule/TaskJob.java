package spring.SpringBoot.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import spring.SpringBoot.service.impl.ContractService;
import spring.SpringBoot.solidity.LeaveMsg;

import java.math.BigInteger;

@Component("taskJob")
public class TaskJob {
    @Autowired
    ContractService contractService;

    @Scheduled(cron = "0 */10 * * * ?")
    public void job1() throws Exception {
        System.out.println("通过cron定义的定时任务");
        LeaveMsg contract = contractService.buildContract();
        //todo 根据合约contract，执行开奖方法。待提供---

        //例子：写方法
        RemoteFunctionCall<TransactionReceipt> setWord = contract.store(BigInteger.valueOf(317));
        TransactionReceipt transactionReceipt = setWord.sendAsync().get();
        String transactionHash = transactionReceipt.getTransactionHash();
        System.out.println("【定时任务】执行交易Hash值："+transactionHash);

        //例子：读方法
        RemoteFunctionCall<BigInteger> randomWord = contract.retrieve();
        BigInteger value = randomWord.send();
        System.out.println("【定时任务】执行读合约返回信息："+value.toString());
    }
}
