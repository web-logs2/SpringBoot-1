package spring.SpringBoot.constant;

public enum RaffleAssetsStatus {
    ALL(999, "ALL"),
    NO(0, "no"),
    ETH(1, "eth"),
    NFT(2, "NFT");

    private final int code;
    private final String description;

    RaffleAssetsStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static RaffleAssetsStatus fromCode(int code) {
        for (RaffleAssetsStatus status : RaffleAssetsStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("No RaffleStatus found for code: " + code);
    }
}
