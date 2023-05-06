package spring.SpringBoot.listener;

import com.sun.deploy.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.web3j.abi.EventEncoder;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;
import spring.SpringBoot.solidity.NRaffleFactory;

import java.io.IOException;
import java.math.BigInteger;


/**
 * 智能合約
 */
@Configuration
public class ContractConfig {

    //智能合约部署地址
    private String contractAddress = "0xC56104e2108CBC57720Cb58eCd8000e57F25C7f8";

    @Bean
    @Scope("prototype")
    public Web3j web3j() {
        return Web3j.build(new HttpService("https://sepolia.infura.io/v3/3a4cf0ed857e458f8a704efd8211a336"));

    }

    @Bean
    @Autowired
    public NRaffleFactory trace(Web3j web3j) throws IOException {
        NRaffleFactory nraffleFactory;
        try {
            Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            System.out.println("clientVersion" + clientVersion);
            // 以某个用户的身份调用合约
            TransactionManager transactionManager = new ClientTransactionManager(web3j, "acdf03d0669bbd6191f79dfd224a0cd3a0b7d50df59fda874e48cc35d4a5619e");
            //加载智能合约

            BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
            Credentials credentials = Credentials.create("acdf03d0669bbd6191f79dfd224a0cd3a0b7d50df59fda874e48cc35d4a5619e");

            nraffleFactory = NRaffleFactory.load(contractAddress, web3j, credentials,
                new StaticGasProvider(gasPrice, BigInteger.valueOf(3000000L)));
            return nraffleFactory;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
            //throw new RRException("连接智能合约异常");
        }
    }

    @Bean
    //监听这里才用每次都生成一个新的对象，因为同时监听多个事件不能使用同一个实例
    @Scope("prototype")
    @Autowired
    public EthFilter ethFilter(NRaffleFactory trace) throws IOException {
        //获取启动时监听的区块
        return new EthFilter(DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                trace.getContractAddress());
    }



}
