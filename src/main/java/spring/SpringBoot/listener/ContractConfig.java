package spring.SpringBoot.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;
import spring.SpringBoot.constant.ChainConstants;
import spring.SpringBoot.constant.Constant;
import spring.SpringBoot.solidity.NRaffle;
import spring.SpringBoot.solidity.NRaffleFactory;

import java.io.IOException;
import java.math.BigInteger;

/**
 * 智能合約
 */
@Configuration
public class ContractConfig {

    ChainConstants.Chain chainSepolia = ChainConstants.CHAIN_CONFIGS.get(11155111);
    ChainConstants.Chain chainFantom = ChainConstants.CHAIN_CONFIGS.get(4002);
    ChainConstants.Chain chainPolygonMumbai = ChainConstants.CHAIN_CONFIGS.get(80001);
    ChainConstants.Chain chainBscTestnet = ChainConstants.CHAIN_CONFIGS.get(97);

    @Bean
    @Scope("prototype")
    public Web3j web3jSepolia() {
        return Web3j.build(new HttpService(chainSepolia.getNode()));
    }

    @Bean
    @Scope("prototype")
    public Web3j web3jFantom() {
        return Web3j.build(new HttpService(chainFantom.getNode()));
    }

    @Bean
    @Scope("prototype")
    public Web3j web3jPolygonMumbai() {
        return Web3j.build(new HttpService(chainPolygonMumbai.getNode()));
    }

    @Bean
    @Scope("prototype")
    public Web3j web3jBscTestnet() {
        return Web3j.build(new HttpService(chainBscTestnet.getNode()));
    }

    @Bean
    @Autowired
    public NRaffleFactory sepoliaNRaffleFactoryTrace(Web3j web3jSepolia) throws IOException {
        NRaffleFactory nraffleFactory;
        try {
            Web3ClientVersion web3ClientVersion = web3jSepolia.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            System.out.println("clientVersion" + clientVersion);
            //加载智能合约
            BigInteger gasPrice = web3jSepolia.ethGasPrice().send().getGasPrice();
            String nRaffleFactoryAddress = chainSepolia.getRaffleFactoryaAddress();
            String privatekey = chainSepolia.getPrivatekey();
            Credentials credentials = Credentials.create(privatekey);
            nraffleFactory = NRaffleFactory.load(nRaffleFactoryAddress, web3jSepolia, credentials,
                    new StaticGasProvider(gasPrice, BigInteger.valueOf(chainSepolia.getGasPrice())));
            return nraffleFactory;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Bean
    @Autowired
    public NRaffleFactory fantomNRaffleFactoryTrace(Web3j web3jFantom) throws IOException {
        NRaffleFactory nraffleFactory;
        try {
            Web3ClientVersion web3ClientVersion = web3jFantom.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            System.out.println("clientVersion" + clientVersion);
            //加载智能合约
            BigInteger gasPrice = web3jFantom.ethGasPrice().send().getGasPrice();
            String nRaffleFactoryAddress = chainFantom.getRaffleFactoryaAddress();
            String privatekey = chainFantom.getPrivatekey();
            Credentials credentials = Credentials.create(privatekey);
            nraffleFactory = NRaffleFactory.load(nRaffleFactoryAddress, web3jFantom, credentials,
                    new StaticGasProvider(gasPrice, BigInteger.valueOf(chainFantom.getGasPrice())));
            return nraffleFactory;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Bean
    @Autowired
    public NRaffleFactory polygonMumbaiNRaffleFactoryTrace(Web3j web3jPolygonMumbai) throws IOException {
        NRaffleFactory nraffleFactory;
        try {
            Web3ClientVersion web3ClientVersion = web3jPolygonMumbai.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            System.out.println("clientVersion" + clientVersion);
            //加载智能合约
            BigInteger gasPrice = web3jPolygonMumbai.ethGasPrice().send().getGasPrice();
            String nRaffleFactoryAddress = chainPolygonMumbai.getRaffleFactoryaAddress();
            String privatekey = chainPolygonMumbai.getPrivatekey();
            Credentials credentials = Credentials.create(privatekey);
            nraffleFactory = NRaffleFactory.load(nRaffleFactoryAddress, web3jPolygonMumbai, credentials,
                    new StaticGasProvider(gasPrice, BigInteger.valueOf(chainPolygonMumbai.getGasPrice())));
            return nraffleFactory;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Bean
    @Autowired
    public NRaffleFactory bscTestnetNRaffleFactoryTrace(Web3j web3jBscTestnet) throws IOException {
        NRaffleFactory nraffleFactory;
        try {
            Web3ClientVersion web3ClientVersion = web3jBscTestnet.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            System.out.println("clientVersion" + clientVersion);
            //加载智能合约
            BigInteger gasPrice = web3jBscTestnet.ethGasPrice().send().getGasPrice();
            String nRaffleFactoryAddress = chainBscTestnet.getRaffleFactoryaAddress();
            String privatekey = chainBscTestnet.getPrivatekey();
            Credentials credentials = Credentials.create(privatekey);
            nraffleFactory = NRaffleFactory.load(nRaffleFactoryAddress, web3jBscTestnet, credentials,
                    new StaticGasProvider(gasPrice, BigInteger.valueOf(chainBscTestnet.getGasPrice())));
            return nraffleFactory;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }






    @Bean
    //监听这里采用每次都生成一个新的对象，因为同时监听多个事件不能使用同一个实例
    @Scope("prototype")
    @Autowired
    public EthFilter sepoliaNRaffleFactoryFilter(@Qualifier("sepoliaNRaffleFactoryTrace") NRaffleFactory trace) {
       Web3j web3j = Web3j.build(new HttpService(chainSepolia.getNode()));
        BigInteger currentBlockNumber = BigInteger.ZERO; // Default value

        try {
             currentBlockNumber = web3j.ethBlockNumber().send().getBlockNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new EthFilter(DefaultBlockParameter.valueOf(currentBlockNumber),
                DefaultBlockParameterName.LATEST,
                trace.getContractAddress());
        //获取启动时监听的区块
    }

    @Bean
    //监听这里采用每次都生成一个新的对象，因为同时监听多个事件不能使用同一个实例
    @Scope("prototype")
    @Autowired
    public EthFilter fantomNRaffleFactoryFilter(@Qualifier("fantomNRaffleFactoryTrace") NRaffleFactory trace) {
        Web3j web3j = Web3j.build(new HttpService(chainFantom.getNode()));
        BigInteger currentBlockNumber = BigInteger.ZERO; // Default value

        try {
            currentBlockNumber = web3j.ethBlockNumber().send().getBlockNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new EthFilter(DefaultBlockParameter.valueOf(currentBlockNumber),
                DefaultBlockParameterName.LATEST,
                trace.getContractAddress());
        //获取启动时监听的区块
    }

    @Bean
    //监听这里采用每次都生成一个新的对象，因为同时监听多个事件不能使用同一个实例
    @Scope("prototype")
    @Autowired
    public EthFilter polygonMumbaiNRaffleFactoryFilter(@Qualifier("polygonMumbaiNRaffleFactoryTrace") NRaffleFactory trace) {
        Web3j web3j = Web3j.build(new HttpService(chainPolygonMumbai.getNode()));
        BigInteger currentBlockNumber = BigInteger.ZERO; // Default value

        try {
            currentBlockNumber = web3j.ethBlockNumber().send().getBlockNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new EthFilter(DefaultBlockParameter.valueOf(currentBlockNumber),
                DefaultBlockParameterName.LATEST,
                trace.getContractAddress());
        //获取启动时监听的区块
    }



    @Bean
//监听这里采用每次都生成一个新的对象，因为同时监听多个事件不能使用同一个实例
    @Scope("prototype")
    @Autowired
    public EthFilter bscTestnetNRaffleFactoryFilter(@Qualifier("bscTestnetNRaffleFactoryTrace") NRaffleFactory trace) {
        Web3j web3j = Web3j.build(new HttpService(chainBscTestnet.getNode()));
        BigInteger currentBlockNumber = BigInteger.ZERO; // Default value

        try {
            currentBlockNumber = web3j.ethBlockNumber().send().getBlockNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new EthFilter(DefaultBlockParameter.valueOf(currentBlockNumber),
                DefaultBlockParameterName.LATEST,
                trace.getContractAddress());
        //获取启动时监听的区块
    }

}



