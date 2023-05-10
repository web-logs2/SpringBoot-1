package spring.SpringBoot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.web3j.protocol.Web3j;
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

    @Autowired
    private Web3j web3j;


    @Autowired
    private EthFilter ethNRaffleFactoryFilter;

    @Autowired
    private EthFilter ethNRaffleFilter;

   @Autowired
   private EthFilter changeStateFilter;

   @Autowired
   private EthFilter winnerDrawnFilter;

    @Resource
    private NRaffleFactory nraffleFactory;

    @Resource
    private NRaffle nraffle;

    @Autowired
    private RaffleInfoService raffleInfoService;

    @Override
    public void run(ApplicationArguments var1) {
        RaffleCreatedListener();
        TicketsPurchasedListener();
        ChangeStateListener();
        this.log.info("This will be execute when the project was started!");
    }

    /**
     * listen:RaffleCreated
     */
    public void RaffleCreatedListener() {
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

        ethNRaffleFactoryFilter.addSingleTopic(EventEncoder.encode(event));
        log.info("启动监听RaffleCreated");

        nraffleFactory.raffleCreatedEventFlowable(ethNRaffleFactoryFilter).subscribe(response -> {
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
        });
//        web3j.ethLogFlowable(ethNRaffleFactoryFilter).subscribe(log -> {
//            List<Type> results = FunctionReturnDecoder.decode(log.getData(), event.getNonIndexedParameters());
//            System.out.println("Event=====: " + results.get(0).getValue());
//        });
    }

    /**
     * listen:TicketsPurchased
     */
    public void TicketsPurchasedListener() {
        Event event = new Event("TicketsPurchased",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
                                                },
                        new TypeReference<Address>(true) {
                        },
                        new TypeReference<Uint16>() {
                        },
                        new TypeReference<Uint16>() {
                        }));

        ethNRaffleFilter.addSingleTopic(EventEncoder.encode(event));
        log.info("启动监听TicketsPurchased");

        nraffle.ticketsPurchasedEventFlowable(ethNRaffleFilter).subscribe(response -> {
            log.info("buyer:" + response.buyer);
            //更新插入参与表记录
        });
    }

    /**
     * listen:TicketsPurchased
     */
    public void ChangeStateListener() {
        Event event = new Event("ChangeState",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
                }, new TypeReference<Uint8>() {
                }, new TypeReference<Uint256>() {
                }));
        ethNRaffleFilter.addSingleTopic(EventEncoder.encode(event));
        log.info("启动监听:ChangeState");

        nraffle.changeStateEventFlowable(changeStateFilter).subscribe(response -> {
            log.info("newState:" + response.newState);
            RaffleInfo raffleInfo = new RaffleInfo();
            raffleInfo.setRaffleaddress(response.raffleAddress);
            raffleInfo.setRafflestatus(response.newState.intValue());
            raffleInfoService.updateRaffleInfo(raffleInfo);
        });
    }

    /**
     * listen:TicketsPurchased
     */
    public void WinnerDrawnListener() {
        Event event = new Event("WinnerDrawn",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
                }, new TypeReference<Uint16>() {
                }, new TypeReference<Address>() {
                }));
        ethNRaffleFilter.addSingleTopic(EventEncoder.encode(event));
        log.info("启动监听:WinnerDrawn");

        nraffle.winnerDrawnEventFlowable(winnerDrawnFilter).subscribe(response -> {
            log.info("owner:" + response.owner + "ticketNumber:" + response.ticketNumber);
            // 根据合约地址，更新db 中的king字段
        });
    }
}

