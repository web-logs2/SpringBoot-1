package spring.SpringBoot.service.impl;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;
import spring.SpringBoot.solidity.LeaveMsg;

import java.io.IOException;
import java.math.BigInteger;

@Service
public class ContractService {
    public LeaveMsg buildContract() throws IOException {
        //        RPC调用url(此处为ropsten)9aa3d95b3bc440fa88ea12eaa4456161
        Web3j web3 = Web3j.build(new HttpService("https://Sepolia.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161"));
//        "填入自己账户的私钥"
        Credentials credentials = Credentials.create("acdf03d0669bbd6191f79dfd224a0cd3a0b7d50df59fda874e48cc35d4a5619e");
//        加载已经部署在链上的合约地址
        BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
        LeaveMsg contract = LeaveMsg.load("0xE2A3987805a72F66d6B4CfA53D4873C6365E4230", web3, credentials,
                new StaticGasProvider(gasPrice, BigInteger.valueOf(3000000L)));
        return contract;
    }
}
