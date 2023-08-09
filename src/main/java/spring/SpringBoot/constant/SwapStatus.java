package spring.SpringBoot.constant;

public enum SwapStatus {
    NoSwap(0, "no swap"),
    ETH(1, "eth"),
    NFT(2, "NFT");


    private final int code;
    private final String description;

    SwapStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static SwapStatus fromCode(int code) {
        for (SwapStatus status : SwapStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("No RaffleStatus found for code: " + code);
    }
}
