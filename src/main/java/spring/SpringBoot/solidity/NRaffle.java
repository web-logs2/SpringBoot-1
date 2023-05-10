package spring.SpringBoot.solidity;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 5.0.0.
 */
@SuppressWarnings("rawtypes")
@Component
public class NRaffle extends Contract {
  public static final String BINARY = "{\"linkReferences\": {},\n"
    + "   \"object\": \"608060405234801561001057600080fd5b50600436106100415760003560e01c80632e64cec1146100465780635c3fdcd9146100645780636057361d14610082575b600080fd5b61004e61009e565b60405161005b9190610118565b60405180910390f35b61006c6100a7565b604051610079919061014f565b60405180910390f35b61009c6004803603810190610097919061019b565b6100be565b005b60008054905090565b6000600160009054906101000a900460ff16905090565b806000819055507f93fe6d397c74fdf1402a8b72e47b68512f0510d7b98a4bc4cbdf6ac7108b3c59816040516100f49190610118565b60405180910390a150565b6000819050919050565b610112816100ff565b82525050565b600060208201905061012d6000830184610109565b92915050565b600060ff82169050919050565b61014981610133565b82525050565b60006020820190506101646000830184610140565b92915050565b600080fd5b610178816100ff565b811461018357600080fd5b50565b6000813590506101958161016f565b92915050565b6000602082840312156101b1576101b061016a565b5b60006101bf84828501610186565b9150509291505056fea2646970667358221220560a396990a6636d6adc0b948c3280f5b89a2c74a19fd9acc43c04401df2bba264736f6c63430008120033\",\n"
    + "   \"opcodes\": \"PUSH1 0x80 PUSH1 0x40 MSTORE CALLVALUE DUP1 ISZERO PUSH2 0x10 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH1 0x4 CALLDATASIZE LT PUSH2 0x41 JUMPI PUSH1 0x0 CALLDATALOAD PUSH1 0xE0 SHR DUP1 PUSH4 0x2E64CEC1 EQ PUSH2 0x46 JUMPI DUP1 PUSH4 0x5C3FDCD9 EQ PUSH2 0x64 JUMPI DUP1 PUSH4 0x6057361D EQ PUSH2 0x82 JUMPI JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x4E PUSH2 0x9E JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH2 0x5B SWAP2 SWAP1 PUSH2 0x118 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST PUSH2 0x6C PUSH2 0xA7 JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH2 0x79 SWAP2 SWAP1 PUSH2 0x14F JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST PUSH2 0x9C PUSH1 0x4 DUP1 CALLDATASIZE SUB DUP2 ADD SWAP1 PUSH2 0x97 SWAP2 SWAP1 PUSH2 0x19B JUMP JUMPDEST PUSH2 0xBE JUMP JUMPDEST STOP JUMPDEST PUSH1 0x0 DUP1 SLOAD SWAP1 POP SWAP1 JUMP JUMPDEST PUSH1 0x0 PUSH1 0x1 PUSH1 0x0 SWAP1 SLOAD SWAP1 PUSH2 0x100 EXP SWAP1 DIV PUSH1 0xFF AND SWAP1 POP SWAP1 JUMP JUMPDEST DUP1 PUSH1 0x0 DUP2 SWAP1 SSTORE POP PUSH32 0x93FE6D397C74FDF1402A8B72E47B68512F0510D7B98A4BC4CBDF6AC7108B3C59 DUP2 PUSH1 0x40 MLOAD PUSH2 0xF4 SWAP2 SWAP1 PUSH2 0x118 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 LOG1 POP JUMP JUMPDEST PUSH1 0x0 DUP2 SWAP1 POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH2 0x112 DUP2 PUSH2 0xFF JUMP JUMPDEST DUP3 MSTORE POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 ADD SWAP1 POP PUSH2 0x12D PUSH1 0x0 DUP4 ADD DUP5 PUSH2 0x109 JUMP JUMPDEST SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0xFF DUP3 AND SWAP1 POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH2 0x149 DUP2 PUSH2 0x133 JUMP JUMPDEST DUP3 MSTORE POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 ADD SWAP1 POP PUSH2 0x164 PUSH1 0x0 DUP4 ADD DUP5 PUSH2 0x140 JUMP JUMPDEST SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x178 DUP2 PUSH2 0xFF JUMP JUMPDEST DUP2 EQ PUSH2 0x183 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP JUMP JUMPDEST PUSH1 0x0 DUP2 CALLDATALOAD SWAP1 POP PUSH2 0x195 DUP2 PUSH2 0x16F JUMP JUMPDEST SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 DUP5 SUB SLT ISZERO PUSH2 0x1B1 JUMPI PUSH2 0x1B0 PUSH2 0x16A JUMP JUMPDEST JUMPDEST PUSH1 0x0 PUSH2 0x1BF DUP5 DUP3 DUP6 ADD PUSH2 0x186 JUMP JUMPDEST SWAP2 POP POP SWAP3 SWAP2 POP POP JUMP INVALID LOG2 PUSH5 0x6970667358 0x22 SLT KECCAK256 JUMP EXP CODECOPY PUSH10 0x90A6636D6ADC0B948C32 DUP1 CREATE2 0xB8 SWAP11 0x2C PUSH21 0xA19FD9ACC43C04401DF2BBA264736F6C6343000812 STOP CALLER \",\n"
    + "   \"sourceMap\": \"78:556:0:-:0;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;427:80;;;:::i;:::-;;;;;;;:::i;:::-;;;;;;;;543:85;;;:::i;:::-;;;;;;;:::i;:::-;;;;;;;;284:102;;;;;;;;;;;;;:::i;:::-;;:::i;:::-;;427:80;468:7;494:6;;487:13;;427:80;:::o;543:85::-;582:5;610:7;;;;;;;;;;;603:14;;543:85;:::o;284:102::-;340:5;331:6;:14;;;;360:19;373:5;360:19;;;;;;:::i;:::-;;;;;;;;284:102;:::o;7:77:1:-;44:7;73:5;62:16;;7:77;;;:::o;90:118::-;177:24;195:5;177:24;:::i;:::-;172:3;165:37;90:118;;:::o;214:222::-;307:4;345:2;334:9;330:18;322:26;;358:71;426:1;415:9;411:17;402:6;358:71;:::i;:::-;214:222;;;;:::o;442:86::-;477:7;517:4;510:5;506:16;495:27;;442:86;;;:::o;534:112::-;617:22;633:5;617:22;:::i;:::-;612:3;605:35;534:112;;:::o;652:214::-;741:4;779:2;768:9;764:18;756:26;;792:67;856:1;845:9;841:17;832:6;792:67;:::i;:::-;652:214;;;;:::o;953:117::-;1062:1;1059;1052:12;1199:122;1272:24;1290:5;1272:24;:::i;:::-;1265:5;1262:35;1252:63;;1311:1;1308;1301:12;1252:63;1199:122;:::o;1327:139::-;1373:5;1411:6;1398:20;1389:29;;1427:33;1454:5;1427:33;:::i;:::-;1327:139;;;;:::o;1472:329::-;1531:6;1580:2;1568:9;1559:7;1555:23;1551:32;1548:119;;;1586:79;;:::i;:::-;1548:119;1706:1;1731:53;1776:7;1767:6;1756:9;1752:22;1731:53;:::i;:::-;1721:63;;1677:117;1472:329;;;;:::o\"\n"
    + "  }";

  public static final String FUNC_CANCELBEFORESTART = "cancelBeforeStart";

  public static final String FUNC_CANCELIFNORNG = "cancelIfNoRNG";

  public static final String FUNC_CANCELIFUNSOLD = "cancelIfUnsold";

  public static final String FUNC_CANCELSUBSCRIPTION = "cancelSubscription";

  public static final String FUNC_ENDTIMESTAMP = "endTimestamp";

  public static final String FUNC_FINDOWNEROFTICKETNUMBER = "findOwnerOfTicketNumber";

  public static final String FUNC_GETASSIGNEDTICKETCOUNT = "getAssignedTicketCount";

  public static final String FUNC_GETASSIGNEDTICKETNUMBERRANGE = "getAssignedTicketNumberRange";

  public static final String FUNC_GETASSIGNEDTICKETNUMBERRANGES = "getAssignedTicketNumberRanges";

  public static final String FUNC_GETCANCELTIMESTAMP = "getCancelTimestamp";

  public static final String FUNC_GETPURCHASEDTICKETCOUNT = "getPurchasedTicketCount";

  public static final String FUNC_GETSTATE = "getState";

  public static final String FUNC_GETSWAPSTATUS = "getSwapStatus";

  public static final String FUNC_GETTICKETNUMBERRANGELENGTH = "getTicketNumberRangeLength";

  public static final String FUNC_GETTICKETS = "getTickets";

  public static final String FUNC_GETTICKETSLEFT = "getTicketsLeft";

  public static final String FUNC_GETTRANSFERNFTTOWINNERTIMESTAMP = "getTransferNFTToWinnerTimestamp";

  public static final String FUNC_GETVRFSUBSCRIPTIONID = "getVrfSubscriptionId";

  public static final String FUNC_GETWINNERADDRESS = "getWinnerAddress";

  public static final String FUNC_GETWINNERDRAWTIMESTAMP = "getWinnerDrawTimestamp";

  public static final String FUNC_GETWINNERTICKETNUMBER = "getWinnerTicketNumber";

  public static final String FUNC_NFTCONTRACT = "nftContract";

  public static final String FUNC_NFTSTANDARD = "nftStandard";

  public static final String FUNC_NFTTOKENID = "nftTokenId";

  public static final String FUNC_ONERC1155BATCHRECEIVED = "onERC1155BatchReceived";

  public static final String FUNC_ONERC1155RECEIVED = "onERC1155Received";

  public static final String FUNC_ONERC721RECEIVED = "onERC721Received";

  public static final String FUNC_OWNER = "owner";

  public static final String FUNC_PAYMENTS = "payments";

  public static final String FUNC_PURCHASETICKET = "purchaseTicket";

  public static final String FUNC_RAWFULFILLRANDOMWORDS = "rawFulfillRandomWords";

  public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

  public static final String FUNC_STARTTIMESTAMP = "startTimestamp";

  public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

  public static final String FUNC_TICKETPRICE = "ticketPrice";

  public static final String FUNC_TRANSFERALLIFCANCELLED = "transferAllIfCancelled";

  public static final String FUNC_TRANSFERALLIFCOMPLETEDWITHETH = "transferAllIfCompletedWithETH";

  public static final String FUNC_TRANSFERALLIFCOMPLETEDWITHNFT = "transferAllIfCompletedWithNFT";

  public static final String FUNC_TRANSFERETHTOOWNERIFCOMPLETED = "transferETHToOwnerIfCompleted";

  public static final String FUNC_TRANSFERNFTTOOWNERIFCANCELLED = "transferNFTToOwnerIfCancelled";

  public static final String FUNC_TRANSFERNFTTOWINNERIFCOMPLETED = "transferNFTToWinnerIfCompleted";

  public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

  public static final String FUNC_TRANSFERTICKETREFUNDIFCANCELLED = "transferTicketRefundIfCancelled";

  public static final String FUNC_VERIFYNFTPRESENCEBEFORESTART = "verifyNFTPresenceBeforeStart";

  public static final String FUNC_VRFRANDOMWORDS = "vrfRandomWords";

  public static final String FUNC_VRFREQUESTID = "vrfRequestId";

  public static final String FUNC_WITHDRAWPAYMENTS = "withdrawPayments";

  public static final Event CHANGESTATE_EVENT = new Event("ChangeState",
    Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
  ;

  public static final Event NFTVERIFIED_EVENT = new Event("NFTVerified",
    Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}));
  ;

  public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred",
    Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
  ;

  public static final Event TICKETSPURCHASED_EVENT = new Event("TicketsPurchased",
    Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}));
  ;

  public static final Event TRANSFERETH_EVENT = new Event("TransferETH",
    Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}));
  ;

  public static final Event TRANSFERNFT_EVENT = new Event("TransferNFT",
    Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}));
  ;

  public static final Event WINNERDRAWN_EVENT = new Event("WinnerDrawn",
    Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint16>() {}, new TypeReference<Address>() {}));
  ;

  @Deprecated
  protected NRaffle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
    super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  protected NRaffle(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
    super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
  }

  @Deprecated
  protected NRaffle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
    super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
  }

  protected NRaffle(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
    super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
  }

  public List<ChangeStateEventResponse> getChangeStateEvents(TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANGESTATE_EVENT, transactionReceipt);
    ArrayList<ChangeStateEventResponse> responses = new ArrayList<ChangeStateEventResponse>(valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      ChangeStateEventResponse typedResponse = new ChangeStateEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.raffleAddress = (String) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.newState = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
      typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Flowable<ChangeStateEventResponse> changeStateEventFlowable(EthFilter filter) {
    return web3j.ethLogFlowable(filter).map(new Function<Log, ChangeStateEventResponse>() {
      @Override
      public ChangeStateEventResponse apply(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANGESTATE_EVENT, log);
        ChangeStateEventResponse typedResponse = new ChangeStateEventResponse();
        typedResponse.log = log;
        typedResponse.raffleAddress = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newState = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
      }
    });
  }

  public Flowable<ChangeStateEventResponse> changeStateEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(CHANGESTATE_EVENT));
    return changeStateEventFlowable(filter);
  }

  public List<NFTVerifiedEventResponse> getNFTVerifiedEvents(TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NFTVERIFIED_EVENT, transactionReceipt);
    ArrayList<NFTVerifiedEventResponse> responses = new ArrayList<NFTVerifiedEventResponse>(valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      NFTVerifiedEventResponse typedResponse = new NFTVerifiedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.raffleAddress = (String) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.nftContract = (String) eventValues.getIndexedValues().get(1).getValue();
      typedResponse.nftTokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
      typedResponse.nftStandard = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Flowable<NFTVerifiedEventResponse> nFTVerifiedEventFlowable(EthFilter filter) {
    return web3j.ethLogFlowable(filter).map(new Function<Log, NFTVerifiedEventResponse>() {
      @Override
      public NFTVerifiedEventResponse apply(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NFTVERIFIED_EVENT, log);
        NFTVerifiedEventResponse typedResponse = new NFTVerifiedEventResponse();
        typedResponse.log = log;
        typedResponse.raffleAddress = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.nftContract = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.nftTokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.nftStandard = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
      }
    });
  }

  public Flowable<NFTVerifiedEventResponse> nFTVerifiedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(NFTVERIFIED_EVENT));
    return nFTVerifiedEventFlowable(filter);
  }

  public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
    ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
    return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
      @Override
      public OwnershipTransferredEventResponse apply(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
        OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
        typedResponse.log = log;
        typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
      }
    });
  }

  public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
    return ownershipTransferredEventFlowable(filter);
  }

  public List<TicketsPurchasedEventResponse> getTicketsPurchasedEvents(TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TICKETSPURCHASED_EVENT, transactionReceipt);
    ArrayList<TicketsPurchasedEventResponse> responses = new ArrayList<TicketsPurchasedEventResponse>(valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      TicketsPurchasedEventResponse typedResponse = new TicketsPurchasedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.raffleAddress = (String) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.buyer = (String) eventValues.getIndexedValues().get(1).getValue();
      typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
      typedResponse.ticketsLeft = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Flowable<TicketsPurchasedEventResponse> ticketsPurchasedEventFlowable(EthFilter filter) {
    return web3j.ethLogFlowable(filter).map(new Function<Log, TicketsPurchasedEventResponse>() {
      @Override
      public TicketsPurchasedEventResponse apply(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TICKETSPURCHASED_EVENT, log);
        TicketsPurchasedEventResponse typedResponse = new TicketsPurchasedEventResponse();
        typedResponse.log = log;
        typedResponse.raffleAddress = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.buyer = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.ticketsLeft = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
      }
    });
  }

  public Flowable<TicketsPurchasedEventResponse> ticketsPurchasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(TICKETSPURCHASED_EVENT));
    return ticketsPurchasedEventFlowable(filter);
  }

  public List<TransferETHEventResponse> getTransferETHEvents(TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERETH_EVENT, transactionReceipt);
    ArrayList<TransferETHEventResponse> responses = new ArrayList<TransferETHEventResponse>(valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      TransferETHEventResponse typedResponse = new TransferETHEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.raffleAddress = (String) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.receiverAddress = (String) eventValues.getIndexedValues().get(1).getValue();
      typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
      typedResponse.currentState = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Flowable<TransferETHEventResponse> transferETHEventFlowable(EthFilter filter) {
    return web3j.ethLogFlowable(filter).map(new Function<Log, TransferETHEventResponse>() {
      @Override
      public TransferETHEventResponse apply(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFERETH_EVENT, log);
        TransferETHEventResponse typedResponse = new TransferETHEventResponse();
        typedResponse.log = log;
        typedResponse.raffleAddress = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.receiverAddress = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.currentState = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
      }
    });
  }

  public Flowable<TransferETHEventResponse> transferETHEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(TRANSFERETH_EVENT));
    return transferETHEventFlowable(filter);
  }

  public List<TransferNFTEventResponse> getTransferNFTEvents(TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERNFT_EVENT, transactionReceipt);
    ArrayList<TransferNFTEventResponse> responses = new ArrayList<TransferNFTEventResponse>(valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      TransferNFTEventResponse typedResponse = new TransferNFTEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.raffleAddress = (String) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.receiverAddress = (String) eventValues.getIndexedValues().get(1).getValue();
      typedResponse.nftContract = (String) eventValues.getIndexedValues().get(2).getValue();
      typedResponse.nftTokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
      typedResponse.nftStandard = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
      typedResponse.currentState = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Flowable<TransferNFTEventResponse> transferNFTEventFlowable(EthFilter filter) {
    return web3j.ethLogFlowable(filter).map(new Function<Log, TransferNFTEventResponse>() {
      @Override
      public TransferNFTEventResponse apply(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFERNFT_EVENT, log);
        TransferNFTEventResponse typedResponse = new TransferNFTEventResponse();
        typedResponse.log = log;
        typedResponse.raffleAddress = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.receiverAddress = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.nftContract = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.nftTokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.nftStandard = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.currentState = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
      }
    });
  }

  public Flowable<TransferNFTEventResponse> transferNFTEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(TRANSFERNFT_EVENT));
    return transferNFTEventFlowable(filter);
  }

  public List<WinnerDrawnEventResponse> getWinnerDrawnEvents(TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(WINNERDRAWN_EVENT, transactionReceipt);
    ArrayList<WinnerDrawnEventResponse> responses = new ArrayList<WinnerDrawnEventResponse>(valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      WinnerDrawnEventResponse typedResponse = new WinnerDrawnEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.raffleAddress = (String) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.ticketNumber = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
      typedResponse.owner = (String) eventValues.getNonIndexedValues().get(1).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public Flowable<WinnerDrawnEventResponse> winnerDrawnEventFlowable(EthFilter filter) {
    return web3j.ethLogFlowable(filter).map(new Function<Log, WinnerDrawnEventResponse>() {
      @Override
      public WinnerDrawnEventResponse apply(Log log) {
        Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(WINNERDRAWN_EVENT, log);
        WinnerDrawnEventResponse typedResponse = new WinnerDrawnEventResponse();
        typedResponse.log = log;
        typedResponse.raffleAddress = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.ticketNumber = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.owner = (String) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
      }
    });
  }

  public Flowable<WinnerDrawnEventResponse> winnerDrawnEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
    EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
    filter.addSingleTopic(EventEncoder.encode(WINNERDRAWN_EVENT));
    return winnerDrawnEventFlowable(filter);
  }

  public RemoteFunctionCall<TransactionReceipt> cancelBeforeStart() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_CANCELBEFORESTART,
      Arrays.<Type>asList(),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> cancelIfNoRNG() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_CANCELIFNORNG,
      Arrays.<Type>asList(),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> cancelIfUnsold() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_CANCELIFUNSOLD,
      Arrays.<Type>asList(),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> cancelSubscription() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_CANCELSUBSCRIPTION,
      Arrays.<Type>asList(),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<BigInteger> endTimestamp() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ENDTIMESTAMP,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<String> findOwnerOfTicketNumber(BigInteger ticketNumber) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FINDOWNEROFTICKETNUMBER,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint16(ticketNumber)),
      Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    return executeRemoteCallSingleValueReturn(function, String.class);
  }

  public RemoteFunctionCall<BigInteger> getAssignedTicketCount(String owner) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETASSIGNEDTICKETCOUNT,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> getAssignedTicketNumberRange(String owner, BigInteger index) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETASSIGNEDTICKETNUMBERRANGE,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner),
        new org.web3j.abi.datatypes.generated.Uint16(index)),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> getAssignedTicketNumberRanges(String owner) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETASSIGNEDTICKETNUMBERRANGES,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> getCancelTimestamp() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCANCELTIMESTAMP,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> getPurchasedTicketCount(String owner) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPURCHASEDTICKETCOUNT,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> getState() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSTATE,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> getSwapStatus() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSWAPSTATUS,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> getTicketNumberRangeLength() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTICKETNUMBERRANGELENGTH,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> getTickets() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTICKETS,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> getTicketsLeft() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTICKETSLEFT,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> getTransferNFTToWinnerTimestamp() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTRANSFERNFTTOWINNERTIMESTAMP,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> getVrfSubscriptionId() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVRFSUBSCRIPTIONID,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<String> getWinnerAddress() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWINNERADDRESS,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    return executeRemoteCallSingleValueReturn(function, String.class);
  }

  public RemoteFunctionCall<BigInteger> getWinnerDrawTimestamp() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWINNERDRAWTIMESTAMP,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> getWinnerTicketNumber() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWINNERTICKETNUMBER,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<String> nftContract() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NFTCONTRACT,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    return executeRemoteCallSingleValueReturn(function, String.class);
  }

  public RemoteFunctionCall<BigInteger> nftStandard() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NFTSTANDARD,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> nftTokenId() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NFTTOKENID,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<TransactionReceipt> onERC1155BatchReceived(String param0, String param1, List<BigInteger> param2, List<BigInteger> param3, byte[] param4) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_ONERC1155BATCHRECEIVED,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0),
        new org.web3j.abi.datatypes.Address(160, param1),
        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
          org.web3j.abi.datatypes.generated.Uint256.class,
          org.web3j.abi.Utils.typeMap(param2, org.web3j.abi.datatypes.generated.Uint256.class)),
        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
          org.web3j.abi.datatypes.generated.Uint256.class,
          org.web3j.abi.Utils.typeMap(param3, org.web3j.abi.datatypes.generated.Uint256.class)),
        new org.web3j.abi.datatypes.DynamicBytes(param4)),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> onERC1155Received(String param0, String param1, BigInteger param2, BigInteger param3, byte[] param4) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_ONERC1155RECEIVED,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0),
        new org.web3j.abi.datatypes.Address(160, param1),
        new org.web3j.abi.datatypes.generated.Uint256(param2),
        new org.web3j.abi.datatypes.generated.Uint256(param3),
        new org.web3j.abi.datatypes.DynamicBytes(param4)),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> onERC721Received(String param0, String param1, BigInteger param2, byte[] param3) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_ONERC721RECEIVED,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0),
        new org.web3j.abi.datatypes.Address(160, param1),
        new org.web3j.abi.datatypes.generated.Uint256(param2),
        new org.web3j.abi.datatypes.DynamicBytes(param3)),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<String> owner() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    return executeRemoteCallSingleValueReturn(function, String.class);
  }

  public RemoteFunctionCall<BigInteger> payments(String dest) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PAYMENTS,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, dest)),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<TransactionReceipt> purchaseTicket(BigInteger count) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_PURCHASETICKET,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint16(count)),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> rawFulfillRandomWords(BigInteger requestId, List<BigInteger> randomWords) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_RAWFULFILLRANDOMWORDS,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(requestId),
        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
          org.web3j.abi.datatypes.generated.Uint256.class,
          org.web3j.abi.Utils.typeMap(randomWords, org.web3j.abi.datatypes.generated.Uint256.class))),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_RENOUNCEOWNERSHIP,
      Arrays.<Type>asList(),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<BigInteger> startTimestamp() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_STARTTIMESTAMP,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)),
      Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
    return executeRemoteCallSingleValueReturn(function, Boolean.class);
  }

  public RemoteFunctionCall<BigInteger> ticketPrice() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TICKETPRICE,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<TransactionReceipt> transferAllIfCancelled() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_TRANSFERALLIFCANCELLED,
      Arrays.<Type>asList(),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> transferAllIfCompletedWithETH() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_TRANSFERALLIFCOMPLETEDWITHETH,
      Arrays.<Type>asList(),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> transferAllIfCompletedWithNFT() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_TRANSFERALLIFCOMPLETEDWITHNFT,
      Arrays.<Type>asList(),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> transferETHToOwnerIfCompleted() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_TRANSFERETHTOOWNERIFCOMPLETED,
      Arrays.<Type>asList(),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> transferNFTToOwnerIfCancelled(BigInteger _nftStandard, String contractAddress, BigInteger tokenId) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_TRANSFERNFTTOOWNERIFCANCELLED,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_nftStandard),
        new org.web3j.abi.datatypes.Address(160, contractAddress),
        new org.web3j.abi.datatypes.generated.Uint256(tokenId)),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> transferNFTToWinnerIfCompleted() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_TRANSFERNFTTOWINNERIFCOMPLETED,
      Arrays.<Type>asList(),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_TRANSFEROWNERSHIP,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> transferTicketRefundIfCancelled() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_TRANSFERTICKETREFUNDIFCANCELLED,
      Arrays.<Type>asList(),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<TransactionReceipt> verifyNFTPresenceBeforeStart() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_VERIFYNFTPRESENCEBEFORESTART,
      Arrays.<Type>asList(),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteFunctionCall<BigInteger> vrfRandomWords(BigInteger param0) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VRFRANDOMWORDS,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<BigInteger> vrfRequestId() {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VRFREQUESTID,
      Arrays.<Type>asList(),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteFunctionCall<TransactionReceipt> withdrawPayments(String payee) {
    final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
      FUNC_WITHDRAWPAYMENTS,
      Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, payee)),
      Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  @Deprecated
  public static NRaffle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
    return new NRaffle(contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  @Deprecated
  public static NRaffle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
    return new NRaffle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
  }

  public static NRaffle load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
    return new NRaffle(contractAddress, web3j, credentials, contractGasProvider);
  }

  public static NRaffle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
    return new NRaffle(contractAddress, web3j, transactionManager, contractGasProvider);
  }

  public static RemoteCall<NRaffle> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
    return deployRemoteCall(NRaffle.class, web3j, credentials, contractGasProvider, BINARY, "");
  }

  @Deprecated
  public static RemoteCall<NRaffle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
    return deployRemoteCall(NRaffle.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
  }

  public static RemoteCall<NRaffle> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
    return deployRemoteCall(NRaffle.class, web3j, transactionManager, contractGasProvider, BINARY, "");
  }

  @Deprecated
  public static RemoteCall<NRaffle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
    return deployRemoteCall(NRaffle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
  }

  public static class ChangeStateEventResponse extends BaseEventResponse {
    public String raffleAddress;

    public BigInteger newState;

    public BigInteger timestamp;
  }

  public static class NFTVerifiedEventResponse extends BaseEventResponse {
    public String raffleAddress;

    public String nftContract;

    public BigInteger nftTokenId;

    public BigInteger nftStandard;
  }

  public static class OwnershipTransferredEventResponse extends BaseEventResponse {
    public String previousOwner;

    public String newOwner;
  }

  public static class TicketsPurchasedEventResponse extends BaseEventResponse {
    public String raffleAddress;

    public String buyer;

    public BigInteger count;

    public BigInteger ticketsLeft;
  }

  public static class TransferETHEventResponse extends BaseEventResponse {
    public String raffleAddress;

    public String receiverAddress;

    public BigInteger amount;

    public BigInteger currentState;
  }

  public static class TransferNFTEventResponse extends BaseEventResponse {
    public String raffleAddress;

    public String receiverAddress;

    public String nftContract;

    public BigInteger nftTokenId;

    public BigInteger nftStandard;

    public BigInteger currentState;
  }

  public static class WinnerDrawnEventResponse extends BaseEventResponse {
    public String raffleAddress;

    public BigInteger ticketNumber;

    public String owner;
  }
}
