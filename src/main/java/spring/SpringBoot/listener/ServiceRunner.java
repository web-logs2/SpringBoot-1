package spring.SpringBoot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.EthFilter;


import java.util.Arrays;
import java.util.List;

import static org.web3j.tx.Contract.staticExtractEventParameters;

/**
 * 服务监听器，继承ApplicationRunner，在spring启动时启动
 *
 * @author
 */
@Component
public class ServiceRunner implements ApplicationRunner {
    /**
     * 日志记录
     */
    private Logger log = LoggerFactory.getLogger(ServiceRunner.class);


    @Autowired
    private Web3j web3j;

    //如果多个监听，必须要注入新的过滤器
    @Autowired
    private EthFilter ethFilter;

    @Override
    public void run(ApplicationArguments var1) throws Exception {
        uploadProAuth();
        this.log.info("This will be execute when the project was started!");
    }




    /**
     * 收到上链事件
     */
    public void uploadProAuth() {

        Event event = new Event("RaffleCreated",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));


        ethFilter.addSingleTopic(EventEncoder.encode(event));

        log.info("启动监听RaffleCreated");


        web3j.ethLogFlowable(ethFilter).subscribe(log -> {
            List<Type> results = FunctionReturnDecoder.decode(log.getData(), event.getNonIndexedParameters());
            System.out.println("Event=====: " + results);
        });

    }

}