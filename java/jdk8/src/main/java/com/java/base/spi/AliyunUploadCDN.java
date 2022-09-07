package com.java.base.spi;

/**
 * @author zail
 * @date 2022/8/4
 */
public class AliyunUploadCDN  implements UploadCDN {
    @Override
    public void upload() {
        System.out.println("Aliyun uploiad CDN!");
    }
}
