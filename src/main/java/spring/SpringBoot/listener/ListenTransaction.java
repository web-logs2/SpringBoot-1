package spring.SpringBoot.listener;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.http.HttpService;

@Controller
@RequestMapping("/api/listener")
public class ListenTransaction {
    @PostMapping("/listen-transaction")
    public void listenTransaction(@RequestBody String transactionHash) {
        // 在这里启动新的线程来监听交易状态new Thread(() -> {
        new Thread(() -> {
            try {
                // 获取Web3j实例
                Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

                // 获取交易状态
                EthGetTransactionReceipt receipt = web3j.ethGetTransactionReceipt(transactionHash).send();

                if (receipt.getTransactionReceipt().isPresent()) {
                    // 交易已被确认，更新数据库状态
                } else {
                    // 交易仍在等待确认，继续监听
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 在这里使用Web3j或The Graph等库来监听交易状态
        }).start();

    }


}
