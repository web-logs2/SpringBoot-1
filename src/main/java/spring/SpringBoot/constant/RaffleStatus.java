package spring.SpringBoot.constant;

public enum RaffleStatus {
    WaitingForNFT(0, "Waiting for NFT"),
    WaitingForStart(1, "Waiting for Start"),
    SellingTickets(2, "Selling Tickets"),
    WaitingForRNG(3, "Waiting for RNG"),
    Completed(4, "Completed"),
    Cancelled(5, "Cancelled");

    private final int code;
    private final String description;

    RaffleStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static RaffleStatus fromCode(int code) {
        for (RaffleStatus status : RaffleStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("No RaffleStatus found for code: " + code);
    }

}
