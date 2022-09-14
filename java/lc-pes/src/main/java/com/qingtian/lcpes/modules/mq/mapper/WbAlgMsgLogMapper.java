package com.qingtian.lcpes.modules.mq.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qingtian.lcpes.base.facade.BasicMapper;
import com.qingtian.lcpes.modules.mq.entity.WbAlgMsgLogEntity;
import com.qingtian.lcpes.modules.mq.vo.WbAlgMsgLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 算法接口日志表 Mapper
 * 自定义Mapper方法
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Mapper
public interface WbAlgMsgLogMapper extends BasicMapper<WbAlgMsgLogEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    public List<WbAlgMsgLogEntity> listByPage(IPage<WbAlgMsgLogEntity> page, @Param(Constants.ENTITY) WbAlgMsgLogEntity entity);

    List<WbAlgMsgLogVO> pageByParams(IPage<WbAlgMsgLogVO> page, @Param("cm") Map<String, Object> params);
}
