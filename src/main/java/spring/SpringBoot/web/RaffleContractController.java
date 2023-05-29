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
    public void verifyNFTPresenceBeforeStart(@RequestParam  String contractAddress) {
        raffleContractService.verifyNFTPresenceBeforeStart(contractAddress);
    }

    @RequestMapping("/getState")
    public BigInteger getState(@RequestParam  String contractAddress) {
        return raffleContractService.getState(contractAddress);
    }

    @RequestMapping("/getKing")
    public String getKing(@RequestParam  String contractAddress) {
        return raffleContractService.getKing(contractAddress);
    }

    @RequestMapping("/test")
    public void  test() {
         raffleContractService.test();
    }

}
