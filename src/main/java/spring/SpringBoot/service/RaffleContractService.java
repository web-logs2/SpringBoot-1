package spring.SpringBoot.service;

import org.springframework.stereotype.Component;

import java.math.BigInteger;


public interface RaffleContractService {

    String verifyNFTPresenceBeforeStart(String address);

    BigInteger getState(String address);
}
