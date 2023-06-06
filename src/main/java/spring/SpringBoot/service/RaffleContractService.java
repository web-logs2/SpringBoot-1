package spring.SpringBoot.service;

import org.springframework.stereotype.Component;

import java.math.BigInteger;


public interface RaffleContractService {

    String verifyNFTPresenceBeforeStart(String address);

    BigInteger getState(String address);

    BigInteger getSwapStauts(String address);

    //

    // 已出售的tickets数量
    BigInteger getSoldTickets(String address);

    // 已退还的tickets数量
    BigInteger getRefundTickets(String address);

    String getKing(String address);

    BigInteger getPurchasedTicketCount(String address,String owner);

    BigInteger getWinnerDrawTimestamp(String address);

    void transferAllIfCompletedWithNFT(String address);

    void execSwapTransferAllIfCompletedWithNFT();

    void execTransferAllIfCancelled();

    void execRetryIfNoRNG();

    void execCancelIfUnsold();
    void test();

    BigInteger getWinnerTicketNumber(String raffleAddress);

    BigInteger getAssignedTicketNumberRanges(String raffleAddress, String owner);

    BigInteger getAssignedTicketNumberRange(String raffleAddress, String owner, BigInteger index);

//    void getTicketNumberRange(String raffleAddress, BigInteger index);
}
