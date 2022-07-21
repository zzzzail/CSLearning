package com.example.order;

import com.example.user.User;
import com.example.user.UserService;
import org.apache.dubbo.config.annotation.DubboReference;

/**
 * @author zail
 * @date 2022/7/4
 */
public class OrderServiceImpl implements OrderService {
    
    @DubboReference(timeout = 3000, retries = 5)
    private UserService userService;
    
    @Override
    public void createOrder() {
        User user = userService.getUserById(10);
        System.out.println("为用户`" + user.getUserRealname() + "`创建订单");
    }
}
