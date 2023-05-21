package spring.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.mapper.RaffleInfoMapper;
import spring.SpringBoot.service.RaffleContractService;
import spring.SpringBoot.service.RaffleInfoService;
import spring.SpringBoot.solidity.NRaffle;

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


    Web3j web3 = Web3j.build(new HttpService("https://Sepolia.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161"));

    //私钥
    Credentials credentials = Credentials.create("acdf03d0669bbd6191f79dfd224a0cd3a0b7d50df59fda874e48cc35d4a5619e");

    BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();



    @Override
    public String verifyNFTPresenceBeforeStart(String address) {
        //new一个合约实例
        NRaffle NRaffleContract = NRaffle.load(address,web3,credentials,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));

      System.out.println("verifyNFTPresenceBeforeStart方法内执行");


        RemoteFunctionCall<TransactionReceipt>  verifyNFT = NRaffleContract.verifyNFTPresenceBeforeStart();
        TransactionReceipt transactionReceipt = null;
        try {
            transactionReceipt = verifyNFT.sendAsync().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(null == transactionReceipt){
        String transactionHash = transactionReceipt.getTransactionHash();
        System.out.println("verifyNFTPresenceBeforeStart-transactionHash="+transactionHash);
        }
        return "ok";
    }

    @Override
    public BigInteger getState(String address) {
        //new一个合约实例
        NRaffle NRaffleContract = NRaffle.load(address,web3,credentials,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
      BigInteger value = null;
      try {
        value = NRaffleContract.getState().send();
      } catch (Exception e) {
        e.printStackTrace();
      }
      return value;
    }

    @Override
    public BigInteger getSwapStauts(String address) {
        NRaffle NRaffleContract = NRaffle.load(address,web3,credentials,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
        BigInteger value = null;
        try {
            value = NRaffleContract.getSwapStatus().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public String getKing(String address) {
        NRaffle NRaffleContract = NRaffle.load(address,web3,credentials,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
        String king = null;
        try {
            king = NRaffleContract.getWinnerAddress().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return king;
    }

    @Override
    public BigInteger getWinnerDrawTimestamp(String address) {
        NRaffle NRaffleContract = NRaffle.load(address,web3,credentials,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
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
     * @param address
     */
    @Override
    public void transferAllIfCompletedWithNFT(String address) {
        NRaffle NRaffleContract = NRaffle.load(address,web3,credentials,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
        try {
            NRaffleContract.transferAllIfCompletedWithNFT().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void transferAllIfCancelled(String address) {
        NRaffle NRaffleContract = NRaffle.load(address,web3,credentials,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
        try {
            NRaffleContract.transferAllIfCancelled().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cancelIfUnsold(String address) {
        NRaffle NRaffleContract = NRaffle.load(address,web3,credentials,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
        try {
            NRaffleContract.cancelIfUnsold().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execSwapTransferAllIfCompletedWithNFT() {
        List<RaffleInfo> raffleInfoList=raffleInfoMapper.getExecSwapRaffleInfos();
        System.out.println("execSwap共执行数据："+raffleInfoList.size());
        for(RaffleInfo raffleInfo:raffleInfoList){
            RaffleInfo raffleInfo1 = raffleInfoService.correctStatus(raffleInfo);
            if(new BigInteger("0").equals(raffleInfo1.getSwapStatus())){
                transferAllIfCompletedWithNFT(raffleInfo.getRaffleaddress());
            }
        }
    }

    @Override
    public void execTransferAllIfCancelled() {
        List<RaffleInfo> raffleInfoList=raffleInfoMapper.getTransferAllIfCancelledRaffleInfos();
        //获取数据逻辑要修改为：结束时间后+5天，swapstatus状态为0.发送请求后，修改状态为999（表明退款全部完成）
        System.out.println("execTransferAllIfCancelled共执行数据："+raffleInfoList.size());
        for(RaffleInfo raffleInfo:raffleInfoList){
          transferAllIfCancelled(raffleInfo.getRaffleaddress());
        }
    }

    /**
     * 到期未完成抽奖活动，变更为取消状态
     */
    @Override
    public void execCancelIfUnsold() {
        List<RaffleInfo> raffleInfoList=raffleInfoMapper.getCancelIfUnsoldRaffleInfos();
        //到期未完成抽奖活动清理
        System.out.println("execTransferAllIfCancelled共执行数据："+raffleInfoList.size());
        for(RaffleInfo raffleInfo:raffleInfoList){
            cancelIfUnsold(raffleInfo.getRaffleaddress());//这是一个合约方法
        }
    }


    public RaffleContractServiceImpl() throws IOException {
    }
}
