package spring.SpringBoot;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;
import spring.SpringBoot.solidity.LeaveMsg;

import java.math.BigInteger;

import static org.web3j.tx.Transfer.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

//参考文档：https://blog.csdn.net/weixin_45938441/article/details/122796670
public class TestContract {
    public static void main(String[] args) throws Exception {
//        RPC调用url(此处为Sepolia)
//        测试网：https://Sepolia.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161
//        主网域名：https://mainnet.infura.io/v3/
//        Web3j web3 = Web3j.build(new HttpService("https://Sepolia.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161"));
//        "填入自己账户的私钥"
//        Credentials credentials = Credentials.create("acdf03d0669bbd6191f79dfd224a0cd3a0b7d50df59fda874e48cc35d4a5619e");
//        加载已经部署在链上的合约地址
//        主网地址：0xFBCb38DF439692aed1005AF3d0e978EE3359a254
//        测试网地址：0xE2A3987805a72F66d6B4CfA53D4873C6365E4230
//        BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
//        LeaveMsg contract = LeaveMsg.load("0xE2A3987805a72F66d6B4CfA53D4873C6365E4230",web3,credentials,
//                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));

        // 部署合约,获取合约地址
//        LeaveMsg contract = LeaveMsg.deploy(web3,credentials,web3.ethGasPrice().send().getGasPrice()
//                ,Contract.GAS_PRICE).send();
        // 异步调用写法:写合约
//        RemoteFunctionCall<TransactionReceipt> setWord = contract.store(BigInteger.valueOf(317));
//        TransactionReceipt transactionReceipt = setWord.sendAsync().get();
//        String transactionHash = transactionReceipt.getTransactionHash();
//        System.out.println("transactionHash="+transactionHash);
//
//        // 读合约
//        RemoteFunctionCall<BigInteger> randomWord = contract.retrieve();
//        BigInteger value = randomWord.send();
//        System.out.println("value="+value.toString());

    }
}
