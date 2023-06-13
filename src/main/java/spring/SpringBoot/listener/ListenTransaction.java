
package spring.SpringBoot.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import spring.SpringBoot.constant.ChainConstants;
import spring.SpringBoot.mapper.RaffleInfoMapper;
import spring.SpringBoot.mapper.TokenInfoMapper;
import spring.SpringBoot.service.ParticipantInfoService;
import spring.SpringBoot.service.RaffleContractService;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
    @RequestMapping("/api/listener")
    public class ListenTransaction {

        @Autowired
        ParticipantInfoService participantInfoService;

        @Autowired
        TokenInfoMapper tokenInfoMapper;

        @Autowired
        RaffleInfoMapper raffleInfoMapper;

        @Autowired
        RaffleContractService raffleContractService;

        private ExecutorService threadPool = Executors.newFixedThreadPool(100);

        @RequestMapping("/listen-transaction")
        public void listenTransaction(@RequestParam Map<String,Object> map) {
            threadPool.execute(() -> {
                try {
                    TransactionListener listener = new TransactionListener(map,participantInfoService, tokenInfoMapper, raffleInfoMapper,raffleContractService);
                    listener.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 在这里使用Web3j或The Graph等库来监听交易状态
            });
            // 在这里启动新的线程来监听交易状态new Thread(() -> {
//            new Thread(() -> {
//                try {
//                    TransactionListener listener = new TransactionListener(map,participantInfoService,tokenInfoMapper,raffleInfoMapper);
//                    listener.start();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                // 在这里使用Web3j或The Graph等库来监听交易状态
//            }).start();
        }



    }
