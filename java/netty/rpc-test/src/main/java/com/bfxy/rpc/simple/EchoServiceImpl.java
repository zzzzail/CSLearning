package com.bfxy.rpc.simple;

public class EchoServiceImpl implements EchoService {
    
    @Override
    public String echo(String ping) {
        return ping != null ? ping + " --> I am ok" : " ping is null";
    }
    
}
