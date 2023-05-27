package spring.SpringBoot.vo;

import spring.SpringBoot.entry.ParticipantInfo;
import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.entry.TokenInfo;

import java.util.List;

public class TokenRaffleVo {
    TokenInfo tokenInfo;

    RaffleInfo raffleInfo;

    List<ParticipantInfo> participantInfos;

    Integer myTickets;


    public Integer getMyTickets() {
        return myTickets;
    }

    public void setMyTickets(Integer myTickets) {
        this.myTickets = myTickets;
    }


    public TokenInfo getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public RaffleInfo getRaffleInfo() {
        return raffleInfo;
    }

    public void setRaffleInfo(RaffleInfo raffleInfo) {
        this.raffleInfo = raffleInfo;
    }

    public List<ParticipantInfo> getParticipantInfos() {
        return participantInfos;
    }

    public void setParticipantInfos(List<ParticipantInfo> participantInfos) {
        this.participantInfos = participantInfos;
    }


}
