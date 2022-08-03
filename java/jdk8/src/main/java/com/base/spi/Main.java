package com.base.spi;

import java.util.ServiceLoader;

/**
 * @author zail
 * @date 2022/8/4
 */
public class Main {
    
    public static void main(String[] args) {
        ServiceLoader<UploadCDN> uploadCDNS = ServiceLoader.load(UploadCDN.class);
        for (UploadCDN uploadCDN : uploadCDNS) {
            System.out.println(uploadCDN);
            uploadCDN.upload();
        }
    }
}
