package spring.SpringBoot.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;
import spring.SpringBoot.solidity.NRaffle;
import spring.SpringBoot.solidity.NRaffleFactory;

import java.io.IOException;
import java.math.BigInteger;

/**
 * 智能合約
 */
@Configuration
public class ContractConfig {

    //智能合约部署地址
    private String contractAddress = "0x05896f5A26b44929a85B5445C876f1CB3948E0B8";

    @Bean
    @Scope("prototype")
    public Web3j web3j() {
        return Web3j.build(new HttpService("https://sepolia.infura.io/v3/3a4cf0ed857e458f8a704efd8211a336"));
    }

    @Bean
    @Autowired
    public NRaffleFactory ethNRaffleFactoryTrace(Web3j web3j) throws IOException {
        NRaffleFactory nraffleFactory;
        try {
            Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            System.out.println("clientVersion" + clientVersion);
            //加载智能合约
            BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
            Credentials credentials = Credentials.create("acdf03d0669bbd6191f79dfd224a0cd3a0b7d50df59fda874e48cc35d4a5619e");

            nraffleFactory = NRaffleFactory.load(contractAddress, web3j, credentials,
                    new StaticGasProvider(gasPrice, BigInteger.valueOf(3000000L)));
            return nraffleFactory;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Bean
    @Autowired
    public NRaffle ethNRaffleTrace(Web3j web3j) throws IOException {
        NRaffle nRaffle;
        try {
            Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            System.out.println("clientVersion" + clientVersion);
            //加载智能合约
            BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
            Credentials credentials = Credentials.create("acdf03d0669bbd6191f79dfd224a0cd3a0b7d50df59fda874e48cc35d4a5619e");

            nRaffle = NRaffle.load(contractAddress, web3j, credentials,
                    new StaticGasProvider(gasPrice, BigInteger.valueOf(3000000L)));
            return nRaffle;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Bean
    //监听这里采用每次都生成一个新的对象，因为同时监听多个事件不能使用同一个实例
    @Scope("prototype")
    @Autowired
    public EthFilter ethNRaffleFactoryFilter(NRaffleFactory trace) {
        //获取启动时监听的区块
        return new EthFilter(DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                trace.getContractAddress());
    }

    @Bean
    //监听这里采用每次都生成一个新的对象，因为同时监听多个事件不能使用同一个实例
    @Scope("prototype")
    @Autowired
    public EthFilter ethNRaffleFilter(NRaffle trace) {
        //获取启动时监听的区块
        return new EthFilter(DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                trace.getContractAddress());
    }
}
