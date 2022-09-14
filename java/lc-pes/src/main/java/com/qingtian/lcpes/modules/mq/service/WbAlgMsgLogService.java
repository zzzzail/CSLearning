package com.qingtian.lcpes.modules.mq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseService;
import com.qingtian.lcpes.modules.mq.entity.WbAlgMsgLogEntity;
import com.qingtian.lcpes.modules.mq.vo.WbAlgMsgLogVO;

import java.util.Map;

/**
 * <p>
 * 算法接口日志表 服务类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
public interface WbAlgMsgLogService extends BaseService<WbAlgMsgLogEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    IPage<WbAlgMsgLogEntity> listByPage(IPage<WbAlgMsgLogEntity> page, WbAlgMsgLogEntity entity);

    IPage<WbAlgMsgLogVO> pageByParams(Map<String,Object> params);
}
