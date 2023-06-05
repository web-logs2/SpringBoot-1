package spring.SpringBoot.entry;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint16;

import java.math.BigInteger;

public class TicketNumberRange implements Type {
    private Address owner;
    private Uint16 from;
    private Uint16 to;

    public TicketNumberRange(Address owner, Uint16 from, Uint16 to) {
        this.owner = owner;
        this.from = from;
        this.to = to;
    }

    public Address getOwner() {
        return owner;
    }

    public Uint16 getFrom() {
        return from;
    }

    public Uint16 getTo() {
        return to;
    }

    @Override
    public String getTypeAsString() {
        return "tuple";
    }

    @Override
    public Object getValue() {
        return this; // Return the tuple object itself
    }
}