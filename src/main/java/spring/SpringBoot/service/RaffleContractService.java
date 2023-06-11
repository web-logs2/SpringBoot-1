package spring.SpringBoot.service;

import org.springframework.stereotype.Component;
import spring.SpringBoot.solidity.NRaffle;

import java.math.BigInteger;


public interface RaffleContractService {

    NRaffle createNRaffle(String address, Long chainId);

    String verifyNFTPresenceBeforeStart(String address, Long chainId);

    BigInteger getState(String address,Long chainId);

    BigInteger getSwapStauts(String address,Long chainId);

    // 已出售的tickets数量
    BigInteger getSoldTickets(String address,Long chainId);

    // 已退还的tickets数量
    BigInteger getRefundTickets(String address,Long chainId);

    String getKing(String address,Long chainId);

    BigInteger getPurchasedTicketCount(String address,String owner,Long chainId);

    BigInteger getWinnerDrawTimestamp(String address,Long chainId);

    void transferAllIfCompletedWithNFT(String address,Long chainId);

    void execSwapTransferAllIfCompletedWithNFT();

    void execTransferAllIfCancelled();

    void execRetryIfNoRNG();

    void execCancelIfUnsold();

    public Long getTokenChainIdByRaffleAddress(String raffleAddress);

    BigInteger getWinnerTicketNumber(String raffleAddress,Long chainId);

    BigInteger getAssignedTicketNumberRanges(String raffleAddress, String owner,Long chainId);

    BigInteger getAssignedTicketNumberRange(String raffleAddress, String owner, BigInteger index,Long chainId);

//    void getTicketNumberRange(String raffleAddress, BigInteger index);
}
