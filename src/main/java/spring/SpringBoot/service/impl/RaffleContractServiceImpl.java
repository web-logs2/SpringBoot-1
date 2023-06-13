package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;
import spring.SpringBoot.constant.ChainConstants;
import spring.SpringBoot.constant.Constant;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.entry.TokenInfo;
import spring.SpringBoot.mapper.RaffleInfoMapper;
import spring.SpringBoot.mapper.TokenInfoMapper;
import spring.SpringBoot.service.RaffleContractService;
import spring.SpringBoot.service.RaffleInfoService;
import spring.SpringBoot.solidity.NRaffle;
import spring.SpringBoot.solidity.NRaffleFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class RaffleContractServiceImpl implements RaffleContractService {

    @Autowired
    RaffleInfoMapper raffleInfoMapper;
    @Autowired
    RaffleInfoService raffleInfoService;

    @Autowired
    TokenInfoMapper tokenInfoMapper;

    @Override
    public Long getTokenChainIdByRaffleAddress(String raffleAddress){
        RaffleInfo raffleInfo = raffleInfoMapper.getDetailByRaffleAddress(raffleAddress);
        TokenInfo tokenInfo = tokenInfoMapper.selectByTokenId(raffleInfo.getContractAddress(),raffleInfo.getTokenId());
        return Long.valueOf(tokenInfo.getChain());
    }

    @Override
    public NRaffle createNRaffle(String address,Long chainId){
        ChainConstants.Chain chain = ChainConstants.CHAIN_CONFIGS.get(chainId.intValue());
        Web3j web3 = Web3j.build(new HttpService(chain.getNode()));
        //私钥
        Credentials credentials = Credentials.create(chain.getPrivatekey());

        BigInteger gasPrice = null;
        try {
            gasPrice = web3.ethGasPrice().send().getGasPrice();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TransactionManager txManager = new RawTransactionManager(web3, credentials, chainId);

        NRaffle NRaffleContract = NRaffle.load(address, web3, txManager,
                new StaticGasProvider(gasPrice, BigInteger.valueOf(chain.getGasPrice())));
        return NRaffleContract;
    }

    @Override
    public String verifyNFTPresenceBeforeStart(String address,Long chainId) {
        //new一个合约实例
        NRaffle NRaffleContract=createNRaffle(address,chainId);

        RemoteFunctionCall<TransactionReceipt> verifyNFT = NRaffleContract.verifyNFTPresenceBeforeStart();
        TransactionReceipt transactionReceipt = null;
        try {
            transactionReceipt = verifyNFT.sendAsync().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (null != transactionReceipt) {
            String transactionHash = transactionReceipt.getTransactionHash();
            System.out.println("verifyNFTPresenceBeforeStart-transactionHash=" + transactionHash);
        }
        return "ok";
    }

    @Override
    public BigInteger getState(String address,Long chainId) {
        //new一个合约实例
        NRaffle NRaffleContract = createNRaffle(address,chainId);
        BigInteger value = null;
        try {
            value = NRaffleContract.getState().send();
        } catch (Exception e) {
            value=null;
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public BigInteger getSwapStauts(String address,Long chainId) {
        NRaffle NRaffleContract = createNRaffle(address,chainId);
        BigInteger value = null;
        try {
            value = NRaffleContract.getSwapStatus().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    // 已出售的tickets数量
    @Override
    public BigInteger getSoldTickets(String address,Long chainId) {
        NRaffle NRaffleContract = createNRaffle(address,chainId);
        BigInteger value = null;
        try {
            value = NRaffleContract.getSoldTickets().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    // 已退还的tickets数量
    @Override
    public BigInteger getRefundTickets(String address,Long chainId) {
        NRaffle NRaffleContract = createNRaffle(address,chainId);
        BigInteger value = null;
        try {
            value = NRaffleContract.getRefundTickets().send();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public String getKing(String address,Long chainId) {
        NRaffle NRaffleContract = createNRaffle(address,chainId);
        String king = null;
        try {
            king = NRaffleContract.getWinnerAddress().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return king;
    }

    @Override
    public BigInteger getPurchasedTicketCount(String address,String owner,Long chainId) {
        NRaffle NRaffleContract = createNRaffle(address,chainId);
        BigInteger purchasedTicketCount = null;
        try {
            purchasedTicketCount = NRaffleContract.getPurchasedTicketCount(address).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return purchasedTicketCount;
    }

    @Override
    public BigInteger getWinnerDrawTimestamp(String address,Long chainId) {
        NRaffle NRaffleContract = createNRaffle(address,chainId);
        BigInteger winnerDrawTimestamp = null;
        try {
            winnerDrawTimestamp = NRaffleContract.getWinnerDrawTimestamp().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return winnerDrawTimestamp;
    }

    //定时任务调用

    /**
     * 兑换窗口结束后退回所有的nft和eth
     *
     * @param address
     */
    @Override
    public void transferAllIfCompletedWithNFT(String address,Long chainId) {
        NRaffle NRaffleContract = createNRaffle(address,chainId);
        try {
            NRaffleContract.transferAllIfCompletedWithNFT().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 所有的都返回
     * @param address
     */
    public void transferAllIfCancelled(String address,Long chainId) {
        NRaffle NRaffleContract = createNRaffle(address,chainId);
        try {
//            if(2 == raffleAsset){
//                NRaffleContract.transferNFTToOwnerIfCancelled().send();
//
//            }
//            if(999==raffleAsset){
//                NRaffleContract.transferAllIfCancelled().send();
//            }
           NRaffleContract.transferAllIfCancelled().send();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelIfUnsold(String address,Long chainId) {
        NRaffle NRaffleContract = createNRaffle(address,chainId);
        try {
            NRaffleContract.cancelIfUnsold().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 失败后的重试
    @Override
    public void execRetryIfNoRNG() {

        List<RaffleInfo> raffleInfoList = raffleInfoMapper.getExecRetryIfNoRNGRaffleInfos();
        for(RaffleInfo raffleInfo:raffleInfoList){
            Long chainId = getTokenChainIdByRaffleAddress(raffleInfo.getRaffleaddress());
            NRaffle NRaffleContract = createNRaffle(raffleInfo.getRaffleaddress(),chainId);
            try {
                NRaffleContract.retryIfNoRNG().send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void execSwapTransferAllIfCompletedWithNFT() {
        List<RaffleInfo> raffleInfoList = raffleInfoMapper.getExecSwapRaffleInfos();
        System.out.println("execSwap共执行数据：" + raffleInfoList.size());
        for (RaffleInfo raffleInfo : raffleInfoList) {
            RaffleInfo raffleInfo1 = raffleInfoService.correctStatus(raffleInfo);
            Long chainId = getTokenChainIdByRaffleAddress(raffleInfo.getRaffleaddress());
            if (new BigInteger("0").equals(raffleInfo1.getSwapStatus())) {
                transferAllIfCompletedWithNFT(raffleInfo1.getRaffleaddress(),chainId);
            }
        }
    }

    @Override
    public void execTransferAllIfCancelled() {
        List<RaffleInfo> raffleInfoList = raffleInfoMapper.getTransferAllIfCancelledRaffleInfos();
        System.out.println("execTransferAllIfCancelled共执行数据：" + raffleInfoList.size());
        for (RaffleInfo raffleInfo : raffleInfoList) {
            Long chainId = getTokenChainIdByRaffleAddress(raffleInfo.getRaffleaddress());
            transferAllIfCancelled(raffleInfo.getRaffleaddress(),chainId);
        }
    }

    /**
     * 到期未完成抽奖活动，变更为取消状态
     */
    @Override
    public void execCancelIfUnsold() {

        List<RaffleInfo> raffleInfoList = raffleInfoMapper.getCancelIfUnsoldRaffleInfos();
        //到期未完成抽奖活动清理
        System.out.println("execTransferAllIfCancelled共执行数据：" + raffleInfoList.size());
        for (RaffleInfo raffleInfo : raffleInfoList) {
            Long chainId = getTokenChainIdByRaffleAddress(raffleInfo.getRaffleaddress());
            cancelIfUnsold(raffleInfo.getRaffleaddress(),chainId);
        }
    }

    /**
     * 获胜票的index
     * @param raffleAddress
     * @return
     */
    @Override
    public BigInteger getWinnerTicketNumber(String raffleAddress,Long chainId) {
        NRaffle NRaffleContract =createNRaffle(raffleAddress,chainId);
        BigInteger value = null;

        try {
            value = NRaffleContract.getWinnerTicketNumber().send();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 用户购买了多少次
     * @param raffleAddress
     * @param owner
     * @return
     */
    @Override
    public BigInteger getAssignedTicketNumberRanges(String raffleAddress, String owner,Long chainId) {
        NRaffle NRaffleContract = createNRaffle(raffleAddress,chainId);
        BigInteger value = null;

        try {
            value = NRaffleContract.getAssignedTicketNumberRanges(owner).send();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 用户每次购买的numberRange的编号（index）
     * @param raffleAddress
     * @param owner
     * @return
     */
    @Override
    public BigInteger getAssignedTicketNumberRange(String raffleAddress, String owner, BigInteger index,Long chainId) {
        NRaffle NRaffleContract = createNRaffle(raffleAddress,chainId);
        BigInteger value = null;

        try {
            value = NRaffleContract.getAssignedTicketNumberRange(owner,index).send();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取到这个range的详情
     * @param
     * @param
     */
//    @Override
//    public void getTicketNumberRange(String raffleAddress,BigInteger index) {
//        NRaffle NRaffleContract = NRaffle.load(raffleAddress, web3, txManager,
//                new StaticGasProvider(gasPrice, BigInteger.valueOf(Constant.GASPRICE)));
//        Object value = null;
//
//        try {
//            Tuple3<Address, Uint16, Uint16> result = NRaffleContract.getTicketNumberRange(index).send();
//
//            Address owner = result.component1();
//            Uint16 from = result.component2();
//            Uint16 to = result.component3();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    import java.lang.reflect.Method;
//import java.util.concurrent.Future;

    public RaffleContractServiceImpl() throws IOException {
    }
}
