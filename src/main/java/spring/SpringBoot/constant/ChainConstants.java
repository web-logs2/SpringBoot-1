package spring.SpringBoot.constant;
import java.util.HashMap;

import java.util.Map;

public final class ChainConstants {
    public static final Map<Integer, Chain> CHAIN_CONFIGS;

    static {
        CHAIN_CONFIGS = new HashMap<>();
        CHAIN_CONFIGS.put(4002,
                new Chain(
                4002,
                "fantomTestnet",
                "https://rpcapi-tracing.testnet.fantom.network",
                "0xFe970248A59562674c7b02143b236BCd554fA9E7",
                "acdf03d0669bbd6191f79dfd224a0cd3a0b7d50df59fda874e48cc35d4a5619e",
                3000000L

        ));
        CHAIN_CONFIGS.put(11155111,
                new Chain(
                        11155111,
                        "sepolia",
                        "https://sepolia.infura.io/v3/3a4cf0ed857e458f8a704efd8211a336",
//                        "0xcD3F30766e447a9Ed86a909f7E918FBa411a34C2",
                        "0x93Bbfb6ec89600F3617d0465e5f3806FF7A88074",
                        "acdf03d0669bbd6191f79dfd224a0cd3a0b7d50df59fda874e48cc35d4a5619e",
                        3000000L
                ));
        CHAIN_CONFIGS.put(80001,
                new Chain(
                        80001,
                        "polygonMumbai",
                        "https://polygon-mumbai.g.alchemy.com/v2/ejNxExMIpy8ijb1Q4AX-VIG3ngHuTYxl",
                        "0x3d92e26916e94574e95944753800DfaCECFC1F5B",
                        "acdf03d0669bbd6191f79dfd224a0cd3a0b7d50df59fda874e48cc35d4a5619e",
                        3000000L
                ));
        CHAIN_CONFIGS.put(97,
                new Chain(
                        97,
                        "bsc-testnet",
                        "https://data-seed-prebsc-1-s1.binance.org:8545",
                        "0xE2A3987805a72F66d6B4CfA53D4873C6365E4230",
                        "acdf03d0669bbd6191f79dfd224a0cd3a0b7d50df59fda874e48cc35d4a5619e",
                        3000000L
                ));
    }

    private ChainConstants() {
        // 防止类实例化
    }

    public static final class Chain {
        private final int id;
        private final String name;
        private final String node;
        private final String RaffleFactoryaAddress;
        private final String privatekey;
        private final Long gasPrice;

        public Chain(int id, String name, String node,String raffleFactoryaAddress,String privatekey,Long gasPrice) {
            this.id = id;
            this.name = name;
            this.node = node;
            this.RaffleFactoryaAddress = raffleFactoryaAddress;
            this.privatekey = privatekey;
            this.gasPrice = gasPrice;
        }

        public int getId() {
            return id;
        }

        public String getNode() {
            return node;
        }

        public String getRaffleFactoryaAddress() {
            return RaffleFactoryaAddress;
        }

        public String getPrivatekey(){
            return privatekey;
        }
        public Long getGasPrice(){
            return gasPrice;
        }

    }
}
