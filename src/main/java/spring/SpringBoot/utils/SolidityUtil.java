package spring.SpringBoot.utils;

import org.web3j.codegen.SolidityFunctionWrapperGenerator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class SolidityUtil {
    /**
     * 利用abi信息 与 bin信息 生成对应的abi,bin文件
     * @param abi 合约编译后的abi信息
     * @param bin 合约编译后的bin信息
     */
    public static void generateABIAndBIN(String abi,String bin,String abiFileName,String binFileName) {

        File abiFile = new File("src/main/resources/"+abiFileName);
        File binFile = new File("src/main/resources/"+binFileName);
        BufferedOutputStream abiBos = null;
        BufferedOutputStream binBos = null;
        try{
            FileOutputStream abiFos = new FileOutputStream(abiFile);
            FileOutputStream binFos = new FileOutputStream(binFile);
            abiBos = new BufferedOutputStream(abiFos);
            binBos = new BufferedOutputStream(binFos);
            abiBos.write(abi.getBytes());
            abiBos.flush();
            binBos.write(bin.getBytes());
            binBos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(abiBos != null){
                try{
                    abiBos.close();;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(binBos != null){
                try {
                    binBos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * 生成合约的java代码
     * 其中 -p 为生成java代码的包路径此参数和 -o 参数配合使用，以便将java文件放入正确的路径当中
     * @param abiFile abi的文件路径
     * @param binFile bin的文件路径
     * @param generateFile 生成的java文件路径
     */
    public static void generateClass(String abiFile,String binFile,String generateFile){
        String[] args = Arrays.asList(
                "-a",abiFile,
                "-b",binFile,
                "-p","",
                "-o",generateFile
        ).toArray(new String[0]);
        Stream.of(args).forEach(System.out::println);
        SolidityFunctionWrapperGenerator.main(args);
    }
}
