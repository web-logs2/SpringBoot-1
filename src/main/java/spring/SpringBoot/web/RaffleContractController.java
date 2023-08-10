package spring.SpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.service.RaffleContractService;
import spring.SpringBoot.utils.ResponseUtil;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/raffleContract")
public class RaffleContractController {

    @Autowired
    RaffleContractService raffleContractService;

    @RequestMapping("/verifyNFTPresenceBeforeStart")
    public void verifyNFTPresenceBeforeStart(@RequestParam  String raffleAddress,@RequestParam  Long chainId) {
        raffleContractService.verifyNFTPresenceBeforeStart(raffleAddress,chainId);
    }

    @RequestMapping("/getState")
    public BigInteger getState(@RequestParam  String raffleAddress,@RequestParam  Long chainId) {
        return raffleContractService.getState(raffleAddress,chainId);
    }

    @RequestMapping("/getKing")
    public String getKing(@RequestParam  String raffleAddress,@RequestParam  Long chainId) {
        return raffleContractService.getKing(raffleAddress,chainId);
    }

    @RequestMapping("/getPurchasedTicketCount")
    public BigInteger getPurchasedTicketCount(@RequestParam  String raffleAddress,@RequestParam  String owner,@RequestParam  Long chainId) {
        return raffleContractService.getPurchasedTicketCount(raffleAddress,owner,chainId);
    }

    @RequestMapping("/getWinnerTicketNumber")
    public BigInteger getWinnerTicketNumber(@RequestParam  String raffleAddress,@RequestParam  Long chainId) {
        return raffleContractService.getWinnerTicketNumber(raffleAddress,chainId);
    }

    @RequestMapping("/test")
    public void test() {
        raffleContractService.execRetryIfNoRNG();
    }

}
