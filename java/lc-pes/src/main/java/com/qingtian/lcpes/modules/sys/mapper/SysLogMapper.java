package com.qingtian.lcpes.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qingtian.lcpes.base.facade.BasicMapper;
import com.qingtian.lcpes.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统日志表 Mapper
 * 自定义Mapper方法
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Mapper
public interface SysLogMapper extends BasicMapper<SysLogEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysLogEntity> listByPage(IPage<SysLogEntity> page, @Param(Constants.ENTITY) SysLogEntity entity);
}
