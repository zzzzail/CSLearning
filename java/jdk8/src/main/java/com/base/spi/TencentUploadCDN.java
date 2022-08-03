package com.base.spi;

/**
 * @author zail
 * @date 2022/8/4
 */
public class TencentUploadCDN implements UploadCDN {
    
    @Override
    public void upload() {
        System.out.println("Tencent upload CDN！");
    }
}
