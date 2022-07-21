package com.bfxy.rpc.simple;

import java.net.InetSocketAddress;

public class RpcTest {
    
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RpcExporter.exporter("localhost", 8087);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        RpcImporter<EchoService> importer = new RpcImporter<EchoService>();
        
        EchoService echo = importer.importer(EchoServiceImpl.class, new InetSocketAddress("localhost", 8087));
        
        System.err.println(echo.echo("Are you ok?"));
        
    }
}
