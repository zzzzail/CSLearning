package com.qingtian.lcpes.modules.mq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseService;
import com.qingtian.lcpes.modules.mq.entity.MqConfigEntity;

/**
 * <p>
 * 消息配置表 服务类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
public interface MqConfigService extends BaseService<MqConfigEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    IPage<MqConfigEntity> listByPage(IPage<MqConfigEntity> page, MqConfigEntity entity);

    MqConfigEntity getConfigByMsgId(String msgId);

}
