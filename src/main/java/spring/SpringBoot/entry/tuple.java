package spring.SpringBoot.entry;

import java.math.BigInteger;

public class tuple {
    String owner;
    BigInteger from;
    BigInteger to;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigInteger getFrom() {
        return from;
    }

    public void setFrom(BigInteger from) {
        this.from = from;
    }

    public BigInteger getTo() {
        return to;
    }

    public void setTo(BigInteger to) {
        this.to = to;
    }

}
