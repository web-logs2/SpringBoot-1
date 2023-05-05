package spring.SpringBoot.listener;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.http.HttpService;

@Controller
@RequestMapping("/api/listener")
public class ListenTransaction {
    @RequestMapping("/listen-transaction")
    public void listenTransaction(@RequestParam String transactionHash) {
        // 在这里启动新的线程来监听交易状态new Thread(() -> {
        new Thread(() -> {
            try {
                Web3j web3j = Web3j.build(new HttpService("https://sepolia.infura.io/v3/3a4cf0ed857e458f8a704efd8211a336"));
                TransactionListener listener = new TransactionListener(transactionHash, web3j);
                listener.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 在这里使用Web3j或The Graph等库来监听交易状态
        }).start();

    }


}
