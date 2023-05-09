package spring.SpringBoot.service;

import java.math.BigInteger;

public interface RaffleContractService {

    void verifyNFTPresenceBeforeStart(String address);

    BigInteger getState(String address);
}
