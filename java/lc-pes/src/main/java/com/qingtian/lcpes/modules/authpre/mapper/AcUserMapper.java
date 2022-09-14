package com.qingtian.lcpes.modules.authpre.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qingtian.lcpes.base.facade.BasicMapper;
import com.qingtian.lcpes.modules.authpre.entity.AcUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper
 * 自定义Mapper方法
 * </p>
 *
 * @author <a href="qingtian@shougang.com.cn">晴天</a>
 * @since 2022-05-17
 */
@Mapper
public interface AcUserMapper extends BasicMapper<AcUserEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    public List<AcUserEntity> listByPage(IPage<AcUserEntity> page, @Param(Constants.ENTITY) AcUserEntity entity);

    AcUserEntity getUserEx(@Param("loginId") String loginId);


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
}
