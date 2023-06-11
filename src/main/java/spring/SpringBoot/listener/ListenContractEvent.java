package spring.SpringBoot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.protocol.core.methods.request.EthFilter;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.service.RaffleInfoService;
import spring.SpringBoot.solidity.NRaffle;
import spring.SpringBoot.solidity.NRaffleFactory;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 服务监听器，继承ApplicationRunner，在spring启动时启动
 *
 * @author
 */
@Component
public class ListenContractEvent implements ApplicationRunner {
    /**
     * 日志记录
     */
    private Logger log = LoggerFactory.getLogger(ListenContractEvent.class);
//
//    @Autowired
//    private Web3j web3j;


    @Autowired
    private EthFilter sepoliaNRaffleFactoryFilter;

    @Autowired
    private EthFilter fantomNRaffleFactoryFilter;

//    @Autowired
//    private EthFilter ethNRaffleFilter;
//
//    @Autowired
//    private EthFilter changeStateFilter;
//
//    @Autowired
//    private EthFilter winnerDrawnFilter;

    @Resource
    @Qualifier("sepoliaNRaffleFactoryTrace")
    private NRaffleFactory sepoliaNraffleFactory;
    @Resource
    @Qualifier("fantomNRaffleFactoryTrace")
    private NRaffleFactory fantomNraffleFactory;
//
//    @Resource
//    private NRaffle nraffle;

    @Autowired
    private RaffleInfoService raffleInfoService;

    @Override
    public void run(ApplicationArguments var1) {
        sepoliaRaffleCreatedListener();
        fantomRaffleCreatedListener();
        this.log.info("This will be execute when the project was started!");
    }

    /**
     * listen:RaffleCreated
     */
    public void sepoliaRaffleCreatedListener() {
        Event event = new Event("RaffleCreated",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
                }, new TypeReference<Address>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Uint16>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Address>() {
                }, new TypeReference<Bytes32>() {
                }, new TypeReference<Address>(true) {
                }));


        sepoliaNRaffleFactoryFilter.addSingleTopic(EventEncoder.encode(event));
        log.info("sepolia启动监听RaffleCreated");
        sepoliaNraffleFactory.raffleCreatedEventFlowable(sepoliaNRaffleFactoryFilter)
                .subscribe(
                        response -> {
                            try {
                                RaffleInfo result = raffleInfoService.getRaffleDetailByRaffleAddress(response.raffleAddress);
                                if (null == result) {
                                    RaffleInfo raffleInfo = new RaffleInfo();
                                    raffleInfo.setContractAddress(response.nftContract);
                                    raffleInfo.setOwner(response.owner);
                                    raffleInfo.setRaffleaddress(response.raffleAddress);
                                    raffleInfo.setTokenId(String.valueOf(response.nftTokenId));
                                    raffleInfo.setTickets(response.tickets.intValue());
                                    raffleInfo.setTicketprice(response.ticketPrice.doubleValue());
                                    raffleInfo.setStarttimestamp(response.startTimestamp.longValue());
                                    raffleInfo.setEndtimestamp(response.endTimestamp.longValue());
                                    raffleInfo.setRafflestatus(0);

                                    raffleInfoService.createRaffleInfo(raffleInfo);
                                    log.info("该raffleAddress活动创建成功！raffleAddress:" + response.raffleAddress);
                                } else {
                                    log.info("该raffleAddress活动已存在！raffleAddress:" + response.raffleAddress);
                                }
                            } catch (Exception e) {
                                log.error("处理活动创建事件时发生异常！", e);
                            }
                        },
                        error -> {
                            log.error("订阅活动创建事件时发生错误！", error);
                            // 在这里处理错误情况
                        }
                );

    }

    public void fantomRaffleCreatedListener() {
        Event event = new Event("RaffleCreated",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
                }, new TypeReference<Address>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Uint16>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Address>() {
                }, new TypeReference<Bytes32>() {
                }, new TypeReference<Address>(true) {
                }));


        fantomNRaffleFactoryFilter.addSingleTopic(EventEncoder.encode(event));
        log.info("fantom启动监听RaffleCreated");
        fantomNraffleFactory.raffleCreatedEventFlowable(fantomNRaffleFactoryFilter)
                .subscribe(
                        response -> {
                            try {
                                RaffleInfo result = raffleInfoService.getRaffleDetailByRaffleAddress(response.raffleAddress);
                                if (null == result) {
                                    RaffleInfo raffleInfo = new RaffleInfo();
                                    raffleInfo.setContractAddress(response.nftContract);
                                    raffleInfo.setOwner(response.owner);
                                    raffleInfo.setRaffleaddress(response.raffleAddress);
                                    raffleInfo.setTokenId(String.valueOf(response.nftTokenId));
                                    raffleInfo.setTickets(response.tickets.intValue());
                                    raffleInfo.setTicketprice(response.ticketPrice.doubleValue());
                                    raffleInfo.setStarttimestamp(response.startTimestamp.longValue());
                                    raffleInfo.setEndtimestamp(response.endTimestamp.longValue());
                                    raffleInfo.setRafflestatus(0);

                                    raffleInfoService.createRaffleInfo(raffleInfo);
                                    log.info("该raffleAddress活动创建成功！raffleAddress:" + response.raffleAddress);
                                } else {
                                    log.info("该raffleAddress活动已存在！raffleAddress:" + response.raffleAddress);
                                }
                            } catch (Exception e) {
                                log.error("处理活动创建事件时发生异常！", e);
                            }
                        },
                        error -> {
                            log.error("订阅活动创建事件时发生错误！", error);
                            // 在这里处理错误情况
                        }
                );

    }
}

