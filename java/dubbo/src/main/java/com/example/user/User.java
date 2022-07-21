package com.example.user;

/**
 * @author zail
 * @date 2022/7/4
 */
public class User {
    
    private Integer userId;
    
    private String userRealname;
    
    public User() {
    }
    
    public User(Integer userId, String userRealname) {
        this.userId = userId;
        this.userRealname = userRealname;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getUserRealname() {
        return userRealname;
    }
    
    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }
}
