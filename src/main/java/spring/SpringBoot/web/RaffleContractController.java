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
    public void verifyNFTPresenceBeforeStart(@RequestParam  String raffleAddress) {
        raffleContractService.verifyNFTPresenceBeforeStart(raffleAddress);
    }

    @RequestMapping("/getState")
    public BigInteger getState(@RequestParam  String raffleAddress) {
        return raffleContractService.getState(raffleAddress);
    }

    @RequestMapping("/getKing")
    public String getKing(@RequestParam  String raffleAddress) {
        return raffleContractService.getKing(raffleAddress);
    }

    @RequestMapping("/getPurchasedTicketCount")
    public BigInteger getPurchasedTicketCount(@RequestParam  String raffleAddress,@RequestParam  String owner) {
        return raffleContractService.getPurchasedTicketCount(raffleAddress,owner);
    }

    @RequestMapping("/getWinnerTicketNumber")
    public BigInteger getWinnerTicketNumber(@RequestParam  String raffleAddress) {
        return raffleContractService.getWinnerTicketNumber(raffleAddress);
    }

    @RequestMapping("/test")
    public void  test() {
         raffleContractService.test();
    }

}
