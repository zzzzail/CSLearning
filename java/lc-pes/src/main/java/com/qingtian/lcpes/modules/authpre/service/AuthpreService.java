package com.qingtian.lcpes.modules.authpre.service;

import com.qingtian.lcpes.modules.authpre.vo.LoginUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthpreService {

    /**
     * 登录
     *
     * @return
     */
    String login(String loginId, String password, HttpServletResponse response) throws IOException;

    /*
     * 单点登录,接入
     * @param loginSrc 账号或者工号
     * @param loginId 工号
     * @param password
     * @return java.lang.String
     * @Author TruE
     * @Date 2022/8/30 13:04
     */
    String splogin(String loginSrc, String loginId, String password) throws IOException;


    /**
     * 获取登录用户ID
     *
     * @return
     */
    String getLoginId(HttpServletRequest request);


    /**
     * 获取token
     *
     * @param loginId
     * @return
     */
    String getTokenFromRedis(String loginId);

    /**
     * 获取用户基础信息
     *
     * @param loginId
     * @return
     */
    LoginUser getUserInfo(String loginId);

    /**
     * 获取用户权限信息
     *
     * @param loginId
     * @return
     */
    String getAuthInfoFromRedis(String loginId, String sysId) throws IOException;

    /*
     * 按钮权限
     * @param loginId
     * @return java.lang.String
     * @Author CaoShuJie
     * @Date 2022/8/3 14:04
     */
    String getUserBtnAuth(String loginId) throws IOException;

    /*
     * 菜单权限
     * @param loginId
     * @return java.lang.String
     * @Author CaoShuJie
     * @Date 2022/8/3 14:04
     */
    String getUserPageAuth(String loginId) throws IOException;

    /*
     * 登出
     * @param
     * @return java.lang.Boolean
     * @Author CaoShuJie
     * @Date 2022/8/23 8:14
     */
    Boolean logout();

    /*
     * 重置登录token过期时间
     */
    void reset(String loginId);

}
