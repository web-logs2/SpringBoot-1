package spring.SpringBoot.listener;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TransactionListener {
    private final String txHash;
    private final Web3j web3j;
    private final ScheduledExecutorService executorService;

    public TransactionListener(String txHash, Web3j web3j) {
        this.txHash = txHash;
        this.web3j = web3j;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        executorService.scheduleAtFixedRate(() -> {
            try {
                EthTransaction ethTransaction = web3j.ethGetTransactionByHash(txHash).send();
                Transaction transaction = ethTransaction.getTransaction().orElse(null);
                if (transaction != null) {
                    EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt(txHash).send();
                    if (ethGetTransactionReceipt.getTransactionReceipt().isPresent()) {
                        TransactionReceipt transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt().get();
                        if (transactionReceipt.getStatus().equals("0x1")) {
                            // 交易已确认1111122
                            System.out.println(Thread.currentThread().getId() + "Transaction confirmed: " + txHash);
//                            executorService.shutdown();
                            // 更新数据库1
                            // ...22
                        } else {
                            // 交易失败:1.删除raffleInfo该条数据记录

                            System.out.println(Thread.currentThread().getId() + "Transaction failed: " + txHash);
//                            executorService.shutdown();
                        }
                    }
                }
            } catch (Exception e) {
                // 异常处理
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + "Transaction error: " + txHash);
        }, 0, 5, TimeUnit.SECONDS);
    }
}
