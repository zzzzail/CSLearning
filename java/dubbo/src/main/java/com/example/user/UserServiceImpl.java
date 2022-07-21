package com.example.user;

import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author zail
 * @date 2022/7/4
 */
@DubboService(timeout = 2000, retries = 3)
public class UserServiceImpl implements UserService {
    
    @Override
    public User getUserById(Integer userId) {
        return new User(userId, "Zail");
    }
}
