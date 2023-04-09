package spring.SpringBoot;

import spring.SpringBoot.utils.SolidityUtils;

public class TestGenerateClass {
    public static void main(String[] args) {
        String abiFile = "src/main/resources/leaveMsg2.abi";
        String binFile = "src/main/resources/leaveMsg2.bin";
        String generateFile = "src/main/java/spring/SpringBoot/solidity/";
        SolidityUtils.generateClass(abiFile, binFile, generateFile);
    }
}
