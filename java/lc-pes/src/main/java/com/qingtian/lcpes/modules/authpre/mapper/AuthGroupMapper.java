package com.qingtian.lcpes.modules.authpre.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qingtian.lcpes.base.facade.BasicMapper;
import com.qingtian.lcpes.modules.authpre.entity.AcGroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 代办岗位表 Mapper
 * 自定义Mapper方法
 * </p>
 *
 * @author <a href="qingtian@shougang.com.cn">晴天</a>
 * @since 2022-05-20
 */
@Mapper
@DS("auth")
public interface AuthGroupMapper extends BasicMapper<AcGroupEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    public List<AcGroupEntity> listByPage(IPage<AcGroupEntity> page, @Param(Constants.ENTITY) AcGroupEntity entity);
}
