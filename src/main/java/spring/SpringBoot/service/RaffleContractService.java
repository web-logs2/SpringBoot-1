package spring.SpringBoot.service;

import org.springframework.stereotype.Component;

import java.math.BigInteger;


public interface RaffleContractService {

    String verifyNFTPresenceBeforeStart(String address);

    BigInteger getState(String address);

    BigInteger getSwapStauts(String address);

    String getKing(String address);

    BigInteger getWinnerDrawTimestamp(String address);

    void transferAllIfCompletedWithNFT(String address);

    void execSwapTransferAllIfCompletedWithNFT();

    void execTransferAllIfCancelled();

    void execCancelIfUnsold();
}
