package spring.SpringBoot.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import spring.SpringBoot.constant.ChainConstants;
import spring.SpringBoot.entry.ParticipantInfo;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.mapper.RaffleInfoMapper;
import spring.SpringBoot.mapper.TokenInfoMapper;
import spring.SpringBoot.service.ParticipantInfoService;
import spring.SpringBoot.service.RaffleContractService;
import spring.SpringBoot.service.RaffleInfoService;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TransactionListener {
    private final String txHash;
    private final ScheduledExecutorService executorService;

    private Map<String, Object> map;

    Web3j web3j = null;
    Long chainId;
    ParticipantInfoService participantInfoService;
    @Autowired
    RaffleInfoService raffleInfoService;

    TokenInfoMapper tokenInfoMapper;

    RaffleInfoMapper raffleInfoMapper;
    RaffleContractService raffleContractService;

    public TransactionListener(Map<String, Object> map, ParticipantInfoService participantInfoService,
                               TokenInfoMapper tokenInfoMapper,
                               RaffleInfoMapper raffleInfoMapper,
                               RaffleContractService raffleContractService) {

        txHash = map.get("txHash").toString();
        this.map = map;
        this.participantInfoService = participantInfoService;
        this.tokenInfoMapper = tokenInfoMapper;
        this.raffleInfoMapper = raffleInfoMapper;
        this.raffleContractService = raffleContractService;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        chainId = raffleContractService.getTokenChainIdByRaffleAddress(map.get("raffleAddress").toString());
        web3j = Web3j.build(new HttpService(ChainConstants.CHAIN_CONFIGS.get(chainId.intValue()).getNode()));
    }

    public void start() {
        executorService.scheduleAtFixedRate(() -> {
            try {
                EthTransaction ethTransaction = web3j.ethGetTransactionByHash(txHash).send();
                Transaction transaction = ethTransaction.getTransaction().orElse(null);
                if (transaction != null) {
                    EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt(txHash).send();
                    System.out.println("ethGetTransactionReceipt=="+ethGetTransactionReceipt);

                    if (ethGetTransactionReceipt.getTransactionReceipt().isPresent()) {
                        TransactionReceipt transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt().get();
                        System.out.println("transactionReceipt=="+transactionReceipt);
                        System.out.println("transactionReceipt.getStatus=="+transactionReceipt.getStatus());
                        if (("0x0").equals(transactionReceipt.getStatus())) {
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
                                    raffleContractService.verifyNFTPresenceBeforeStart(map.get("newOwner").toString(),chainId);
                                    tokenInfoMapper.updateOwnerInt(map.get("newOwner").toString(),map.get("TokenContractAddress").toString(),map.get("tokenId").toString());
                                    RaffleInfo raffleInfo3 = new RaffleInfo();
                                    raffleInfo3.setRaffleaddress(map.get("newOwner").toString());
                                    int raffleAssets = raffleInfoMapper.getDetailByRaffleAddress(map.get("newOwner").toString()).getRaffleAssets();
                                    if(0==raffleAssets){
                                        raffleInfo3.setRaffleAssets(2);
                                    }
                                    raffleInfoMapper.updateRaffleInfo(raffleInfo3);
                                    break;
                                case "UpdateTokenOwner":
                                    //增加功能：取回nft如果状态没更新过来，做个补救。监控下返回的error信息。来有不同的处理
                                    tokenInfoMapper.updateOwnerInt(map.get("newOwner").toString(),map.get("TokenContractAddress").toString(),map.get("tokenId").toString());
                                    RaffleInfo raffleInfo = new RaffleInfo();
                                    raffleInfo.setSwapStatus(Integer.parseInt(map.get("swapStatus").toString()));
                                    if(999 == raffleInfoMapper.getDetailByRaffleAddress(map.get("raffleaddress").toString()).getRaffleAssets()){
                                        raffleInfo.setRaffleAssets(1);
                                    }
                                    if(2 == raffleInfoMapper.getDetailByRaffleAddress(map.get("raffleaddress").toString()).getRaffleAssets()){
                                        raffleInfo.setRaffleAssets(0);
                                    }
                                    raffleInfo.setRaffleaddress(map.get("raffleAddress").toString());
                                    raffleInfoMapper.updateRaffleInfo(raffleInfo);
                                    break;
                                case "UpdateSwapStatus":
                                    RaffleInfo raffleInfo2 = new RaffleInfo();
                                    raffleInfo2.setSwapStatus(Integer.parseInt(map.get("swapStatus").toString()));
                                    raffleInfo2.setRaffleaddress(map.get("raffleaddress").toString());
                                    raffleInfoMapper.updateRaffleInfo(raffleInfo2);
                                    break;
                                case "updateRaffleAssets":
                                    RaffleInfo raffle = new RaffleInfo();
                                    //判断如果取回的是：NFT
                                    if(2 == Integer.parseInt(map.get("updateRaffleAsset").toString())){
                                        if(999 == raffleInfoMapper.getDetailByRaffleAddress(map.get("raffleAddress").toString()).getRaffleAssets()){
                                            raffle.setRaffleAssets(1);
                                        }
                                        if( 2 == raffleInfoMapper.getDetailByRaffleAddress(map.get("raffleAddress").toString()).getRaffleAssets()){
                                            raffle.setRaffleAssets(0);
                                        }
                                    }
                                    if(1 == Integer.parseInt(map.get("updateRaffleAsset").toString())){
                                        if(999 == raffleInfoMapper.getDetailByRaffleAddress(map.get("raffleAddress").toString()).getRaffleAssets()){
                                            raffle.setRaffleAssets(2);
                                        }
                                        if(1 == raffleInfoMapper.getDetailByRaffleAddress(map.get("raffleAddress").toString()).getRaffleAssets()){
                                            raffle.setRaffleAssets(0);
                                        }
                                    }
                                    raffle.setRaffleaddress(map.get("raffleAddress").toString());
                                    raffleInfoMapper.updateRaffleInfo(raffle);
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
                executorService.shutdown();
            }
            System.out.println(Thread.currentThread().getId() + "Transaction error: " + txHash);
        }, 0, 5, TimeUnit.SECONDS);
    }

//    private void verifyNFTPresenceBeforeStart(String address) {
//
//        Web3j web3 = Web3j.build(new HttpService(Constant.SEPOLIAURL));
//        //私钥
//        Credentials credentials = Credentials.create(Constant.PRIVATEKEY);
//        long chainId = Constant.FANTOM_TESTNET;
//        TransactionManager txManager = new RawTransactionManager(web3, credentials, chainId);
//
//        BigInteger gasPrice = null;
//        try {
//            gasPrice = web3.ethGasPrice().send().getGasPrice();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        NRaffle NRaffleContract = NRaffle.load(address, web3, txManager,
//                new StaticGasProvider(gasPrice, BigInteger.valueOf(Constant.GASPRICE)));
//        System.out.println("verifyNFTPresenceBeforeStart方法内执行");
//
//
//        RemoteFunctionCall<TransactionReceipt> verifyNFT = NRaffleContract.verifyNFTPresenceBeforeStart();
//        TransactionReceipt transactionReceipt = null;
//        try {
//            transactionReceipt = verifyNFT.sendAsync().get();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//    }
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


