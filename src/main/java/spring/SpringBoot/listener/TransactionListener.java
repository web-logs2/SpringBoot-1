//package spring.SpringBoot.listener;
//
//import org.apache.ibatis.transaction.TransactionException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
//import org.web3j.protocol.core.methods.response.EthTransaction;
//import org.web3j.protocol.core.methods.response.Transaction;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import spring.SpringBoot.entry.RaffleInfo;
//import spring.SpringBoot.service.RaffleInfoService;
//
//import java.io.IOException;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class TransactionListener {
//    private final String txHash;
//    private final Web3j web3j;
//    private final ScheduledExecutorService executorService;
//    @Autowired
//    RaffleInfoService raffleInfoService;
//    private RaffleInfo raffleInfo;
//
//    public TransactionListener(RaffleInfo raffleInfo, Web3j web3j) throws IOException {
//        this.txHash = raffleInfo.getTxHash();
//        this.raffleInfo = raffleInfo;
//        this.web3j = web3j;
//        this.executorService = Executors.newSingleThreadScheduledExecutor();
//    }
//
//    public void start() {
//        executorService.scheduleAtFixedRate(() -> {
//            try {
//                EthTransaction ethTransaction = web3j.ethGetTransactionByHash(txHash).send();
//                Transaction transaction = ethTransaction.getTransaction().orElse(null);
//                if (transaction != null) {
//                    EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt(txHash).send();
//                    if (ethGetTransactionReceipt.getTransactionReceipt().isPresent()) {
//                        TransactionReceipt transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt().get();
//                        if (transactionReceipt.getStatus().equals("0x0")) {
//                            // 交易确认失败，不做任何操作，中断线程
//                            System.out.println(Thread.currentThread().getId() + "Transaction confirmed: " + txHash);
//                            executorService.shutdown();
//                        } else {
//                            // 交易成功
//                            System.out.println(Thread.currentThread().getId() + "Transaction failed: " + txHash);
//                            //区块确认后，获得交易详情，获得 raffleAddress
//                            Object result1 = web3j.ethGetTransactionByHash(txHash).send().getTransaction().orElseThrow(TransactionException::new);
//                            Object result = raffleInfoService.createRaffleInfo(raffleInfo);
////                          executorService.shutdown();
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                // 异常处理
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getId() + "Transaction error: " + txHash);
//        }, 0, 5, TimeUnit.SECONDS);
//    }
//}
//
//
//
