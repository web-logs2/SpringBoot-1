package spring.SpringBoot;

import spring.SpringBoot.utils.SolidityUtil;

public class TestGenerateClass {
    public static void main(String[] args) {
        String abiFile = "src/main/resources/aaa.abi";
        String binFile = "src/main/resources/NRaffle.bin";
        String generateFile = "src/main/java/spring/SpringBoot/solidity/";
        SolidityUtil.generateClass(abiFile, binFile, generateFile);
    }
}
