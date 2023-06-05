package spring.SpringBoot.entry;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint16;

public class MyTuple3 implements Type {
        private Address address;
        private Uint16 uint1;
        private Uint16 uint2;

        // 构造函数和其他方法根据需要定义

    @Override
    public Object getValue() {
        return null;
    }

    // 实现 Type 接口的方法
    @Override
        public String getTypeAsString() {
            return "tuple3"; // 返回合适的类型字符串
        }
    };
