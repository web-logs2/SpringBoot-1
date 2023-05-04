package spring.SpringBoot.vo;

import spring.SpringBoot.entry.RaffleInfo;
import spring.SpringBoot.entry.TokenInfo;

public class TokenRaffleVo {
    TokenInfo tokenInfo;

    RaffleInfo raffleInfo;

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


}
