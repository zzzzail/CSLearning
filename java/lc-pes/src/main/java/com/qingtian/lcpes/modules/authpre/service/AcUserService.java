package com.qingtian.lcpes.modules.authpre.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseService;
import com.qingtian.lcpes.modules.authpre.entity.AcUserEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author <a href="qingtian@shougang.com.cn">晴天</a>
 * @since 2022-05-17
 */
public interface AcUserService extends BaseService<AcUserEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    IPage<AcUserEntity> listByPage(IPage<AcUserEntity> page, AcUserEntity entity);


    /**
     * 获取自定义同步状态码
     *
     * @param loginId
     * @return 1：无需同步 |  2：新增  |  3:修改
     */
    Integer getAsynCode(String loginId);

    /**
     * 同步用户信息
     * 用户   组织机构   岗位
     */
    void asynUserInfo(String loginId);


    /**
     * 手动同步用户信息
     *
     * @return
     */
    Boolean doSynUser();

    /**
     * 最近一次手动同步用户信息的时间
     *
     * @return
     */
    LocalDateTime getSynDate();

    /**
     * 根据登录id查询用户
     *
     * @param loginId
     * @return
     */
    AcUserEntity getAcUserByLoginId(String loginId);


    /**
     * 获取仓库数据权限
     *
     * @param loginId
     * @return java.lang.String
     * @Author CaoShuJie
     * @Date 2022/7/25 8:12
     */
    String getBizWarehouseSids(String loginId);

    /**
     * 获取地点数据权限
     *
     * @param loginId
     * @return java.lang.String
     * @Author CaoShuJie
     * @Date 2022/7/25 8:12
     */
    String getBizLocationSids(String loginId);

    /**
     * 获取作业项目数据权限
     *
     * @param loginId
     * @return java.lang.String
     * @Author CaoShuJie
     * @Date 2022/7/25 8:12
     */
    String getBizWorkItemSids(String loginId);

    /**
     * 获取车队数据权限
     *
     * @param loginId
     * @return java.lang.String
     * @Author CaoShuJie
     * @Date 2022/7/25 8:12
     */
    String getBizFleetSids(String loginId);

    /**
     * 获取作业部数据权限
     *
     * @param loginId
     * @return java.lang.String
     * @Author CaoShuJie
     * @Date 2022/7/25 8:12
     */
    String getBizPOrgSids(String loginId);

    /**
     * 获取需求提报类型数据权限
     *
     * @param loginId
     * @return java.lang.String
     * @Author CaoShuJie
     * @Date 2022/8/19 8:12
     */
    String getBizTaskTypes(String loginId);


    /*
     * 保存数据权限到redis
     * @param loginId
     * @return java.lang.Boolean
     * @Author CaoShuJie
     * @Date 2022/7/25 10:22
     */
    Boolean setBizToRedis(String loginId);
}
