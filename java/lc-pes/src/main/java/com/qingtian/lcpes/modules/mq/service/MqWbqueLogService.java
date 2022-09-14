package com.qingtian.lcpes.modules.mq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseService;
import com.qingtian.lcpes.modules.mq.entity.MqWbqueLogEntity;

/**
 * <p>
 * 运单消息队列日志表 服务类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
public interface MqWbqueLogService extends BaseService<MqWbqueLogEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    IPage<MqWbqueLogEntity> listByPage(IPage<MqWbqueLogEntity> page, MqWbqueLogEntity entity);

    /**
     * 收到死信消息
     *
     * @param fromExchangeName 死信来自交换机名称
     * @param fromQueueName    死信来自队列名称
     * @param dlReason         死信原因
     * @param dlMsg            死信消息
     * @return
     */
    MqWbqueLogEntity receiveDlMsg(String fromExchangeName, String fromQueueName, String dlReason, String dlMsg);

    /**
     * 根据消息的唯一 ID 获取数据
     *
     * @param messageId 消息的唯一 ID
     * @return
     */
    MqWbqueLogEntity getByMessageId(String messageId);

    /**
     * 将消息字符串 hash，将 hash 值用于 messageId
     *
     * @param jsonStr 初始 数据
     * @return
     */
    String jsonDataHash(String jsonStr);

    /**
     * 根据 json 字符串获取 messageTypeId
     *
     * @param msgJsonStr
     * @return
     */
    String parseMessageTypeId(String msgJsonStr);

    /**
     * 取消删除
     */
    void unremove(Long sid);
}
