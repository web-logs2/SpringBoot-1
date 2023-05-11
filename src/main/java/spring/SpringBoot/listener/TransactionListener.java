package spring.SpringBoot.listener;

import ch.qos.logback.classic.util.LogbackMDCAdapter;
import org.apache.ibatis.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;
import spring.SpringBoot.entry.ParticipantInfo;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.mapper.ParticipantInfoMapper;
import spring.SpringBoot.mapper.TokenInfoMapper;
import spring.SpringBoot.service.ParticipantInfoService;
import spring.SpringBoot.service.RaffleContractService;
import spring.SpringBoot.service.RaffleInfoService;
import spring.SpringBoot.service.impl.RaffleInfoServiceImpl;
import spring.SpringBoot.solidity.NRaffle;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TransactionListener {
    private final String txHash;
    private final Web3j web3j;
    private final ScheduledExecutorService executorService;

    private RaffleInfo raffleInfo;

    private Map<String, Object> map;

    @Autowired
    ParticipantInfoService participantInfoService;

    @Autowired
    RaffleInfoService raffleInfoService;

    public TransactionListener(Map<String, Object> map, ParticipantInfoService participantInfoService) {
        txHash = map.get("txHash").toString();
        this.map = map;
        web3j = Web3j.build(new HttpService("https://sepolia.infura.io/v3/3a4cf0ed857e458f8a704efd8211a336"));
        this.participantInfoService = participantInfoService;
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
                        if (transactionReceipt.getStatus().equals("0x0")) {
                            // 交易确认失败，不做任何操作，中断线程
                            System.out.println(Thread.currentThread().getId() + "Transaction confirmed: " + txHash);
                            executorService.shutdown();
                        } else {
                            // 交易成功
                            System.out.println(Thread.currentThread().getId() + "Transaction failed: " + txHash);
                            //区块确认后，获得交易详情，获得 raffleAddress
//                            Object result = web3j.ethGetTransactionByHash(txHash).send().getTransaction().orElseThrow(TransactionException::new);
                            //根据交易类型不同，调用不同的方法

                            switch (map.get("op").toString()) {
                                case "verifyNFTPresenceBeforeStart":
                                    verifyNFTPresenceBeforeStart(map.get("address").toString());
//                  executorService.shutdown();
                                    break;
                                case "TicketsPurchased":
                                    ParticipantInfo participantInfo = new ParticipantInfo();
                                    participantInfo.setParticipantAddress(map.get("participantAddress").toString());
                                    participantInfo.setRaffleaddress(map.get("raffleAddress").toString());
                                    participantInfo.setTicket(Integer.valueOf(map.get("ticketNum").toString()));
                                    //todo 再set下token等一些必填字段
                                    participantInfoService.createParticipantInfo(participantInfo);
                                    break;
                                default:
                                    System.out.println("Unknown fruit selected");
                            }
                            executorService.shutdown();
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

    private void verifyNFTPresenceBeforeStart(String address) {

        Web3j web3 = Web3j.build(new HttpService("https://Sepolia.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161"));
        //私钥
        Credentials credentials = Credentials.create("acdf03d0669bbd6191f79dfd224a0cd3a0b7d50df59fda874e48cc35d4a5619e");
        BigInteger gasPrice = null;
        try {
            gasPrice = web3.ethGasPrice().send().getGasPrice();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NRaffle NRaffleContract = NRaffle.load(address, web3, credentials,
                new StaticGasProvider(gasPrice, BigInteger.valueOf(3000000L)));
        System.out.println("verifyNFTPresenceBeforeStart方法内执行");


        RemoteFunctionCall<TransactionReceipt> verifyNFT = NRaffleContract.verifyNFTPresenceBeforeStart();
        TransactionReceipt transactionReceipt = null;
        try {
            transactionReceipt = verifyNFT.sendAsync().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

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
////                            Object result = web3j.ethGetTransactionByHash(txHash).send().getTransaction().orElseThrow(TransactionException::new);
//                            //根据交易类型不同，调用不同的方法
//
//                            executorService.shutdown();
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


