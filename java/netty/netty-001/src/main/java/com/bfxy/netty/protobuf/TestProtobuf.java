package com.bfxy.netty.protobuf;

import com.bfxy.netty.protobuf.UserModule.User;
import com.bfxy.netty.protobuf.UserModule.User.Builder;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

public class TestProtobuf {
    
    /**
     * serialObject2Bytes 序列化
     */
    public static byte[] serialObject2Bytes() {
        Builder userBuilder = UserModule.User.newBuilder();
        
        User user = userBuilder
                .setUserId("1001")
                .setAge(30)
                .setUserName("张三")
                .addFavorite("足球")
                .addFavorite("撸码").build();
        
        /**
         *  序列化机制：
         *  1. java 序列化 比如一个int类型(4个字节长度)
         *  // int a = 2 & int a = 110000000
         *  java 的序列化无论真是的 int 类型数值大小实际占用多少个字节，在内存中都是以 4 个字节
         *
         *  2. protobuf 序列化机制：
         *  是按照实际的数据大小动态伸缩的, 因此很多时候我们的 int 数据并没有实际占用到 4 个字节，
         *  所以 protobuf 序列化后一般都会比 int 类型(java 序列化机制)的占用长度要小很多！
         */
        byte[] data = user.toByteArray();
        System.err.println(Arrays.toString(data));
        return data;
    }
    
    /**
     * serialBytes2Object 反序列化
     */
    public static User serialBytes2Object(byte[] data) {
        try {
            return UserModule.User.parseFrom(data);
        }
        catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        byte[] data = serialObject2Bytes();
        User user = serialBytes2Object(data);
        
        System.err.println("userId: " + user.getUserId());
        System.err.println("age: " + user.getAge());
        System.err.println("userName: " + user.getUserName());
        System.err.println("favorite: " + user.getFavoriteList());
    }
    
}
