package spring.SpringBoot.service.impl;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;
import spring.SpringBoot.service.RaffleContractService;
import spring.SpringBoot.solidity.NRaffle;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

@Service
public class RaffleContractServiceImpl implements RaffleContractService {


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
        RemoteFunctionCall<BigInteger> status = NRaffleContract.getState();
        BigInteger value = null;
        try {
            value = status.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public RaffleContractServiceImpl() throws IOException {
    }
}
