package com.bfxy.rpc.simple;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RpcExporter {
    
    static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    
    public static void exporter(String hostname, int port) throws Exception {
        
        ServerSocket server = new ServerSocket();
        
        server.bind(new InetSocketAddress(hostname, port));
        
        try {
            while (true) {
                executor.execute(new ExporterTask(server.accept()));
            }
        }
        finally {
            server.close();
        }
    }
    
    private static class ExporterTask implements Runnable {
        
        private Socket client = null;
        
        public ExporterTask(Socket client) {
            this.client = client;
        }
        
        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try {
                input = new ObjectInputStream(client.getInputStream());
                //1
                String interfaceName = input.readUTF();
                Class<?> service = Class.forName(interfaceName);
                
                //2
                String methodName = input.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                
                //3
                Object[] arguments = (Object[]) input.readObject();
                Method method = service.getMethod(methodName, parameterTypes);
                
                //4
                Object result = method.invoke(service.newInstance(), arguments);
                
                //5
                output = new ObjectOutputStream(client.getOutputStream());
                output.writeObject(result);
                
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (output != null) {
                    try {
                        output.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (client != null) {
                    try {
                        client.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
    }
}
