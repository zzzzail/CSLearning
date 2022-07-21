package com.test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author zail
 */
public class Application {
    
    public static void main(String[] args) {
        System.out.println("JDK 17");
        System.out.println("Where is the new features?");
        
        feature01();
        feature02();
        
    }
    
    public static void feature01() {
        // 标准的HttpClient
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();
        client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
    
    public static void feature02() {
        // 可以使用关键字 var 声明局部变量
        var s = "Hello Java 11.";
        System.out.println(s);
    }
    
}
