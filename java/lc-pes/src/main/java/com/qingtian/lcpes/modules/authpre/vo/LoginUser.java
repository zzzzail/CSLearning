package com.qingtian.lcpes.modules.authpre.vo;

import lombok.Data;

/**
 * @author J
 * @date 2022/4/29 16:03
 */
@Data
public class LoginUser {

    /**
     * 登录用户名主键
     */
    private Long userSid;

    /**
     * 登录用户名
     */
    private String loginId;


    /**
     * 密码
     */
    private String password;

    /**
     * 用户名称(汉字)
     */
    private String username;

    /**
     * 部门编码
     */
    private String orgCode;

    /**
     * 部门名称
     */
    private String orgName;

    /**
     * 岗位编码
     */
    private String positionCode;

    /**
     * 岗位名称
     */
    private String positionName;

    //仓库数据权限
    private String bizWarehouseSids;

    //地点数据权限
    private String bizLocationSids;

    //作业项目数据权限
    private String bizWorkItemSids;

    //车队数据权限
    private String bizFleetSids;

    //作业部数据权限
    private String bizPOrgSids;


    /**
     * 工号/账号 for 单点
     */
    private String loginSrc;

}
