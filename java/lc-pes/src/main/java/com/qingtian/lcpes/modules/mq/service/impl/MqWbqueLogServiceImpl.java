package com.qingtian.lcpes.modules.mq.service.impl;

import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseServiceImpl;
import com.qingtian.lcpes.modules.mq.entity.MqWbqueLogEntity;
import com.qingtian.lcpes.modules.mq.enums.DLXMessageStatusEnum;
import com.qingtian.lcpes.modules.mq.mapper.MqWbqueLogMapper;
import com.qingtian.lcpes.modules.mq.service.MqWbqueLogService;
import com.qingtian.lcpes.modules.mq.vo.DlxMsgVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 运单消息队列日志表 服务实现类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Service
@Slf4j
public class MqWbqueLogServiceImpl extends BaseServiceImpl<MqWbqueLogMapper, MqWbqueLogEntity> implements MqWbqueLogService {

    @Value("${qingtian.mq.rabbitmq.dlx.queue-name}")
    private String dlxQueueName;
    @Value("${qingtian.mq.rabbitmq.wb2que.sender-key}")
    private String wb2queSenderKey;
    @Value("${qingtian.mq.rabbitmq.que2wb.sender-key}")
    private String que2wbSenderKey;

    @Autowired
    private MqWbqueLogMapper mqWbqueLogMapper;

    @Override
    public IPage<MqWbqueLogEntity> listByPage(IPage<MqWbqueLogEntity> page, MqWbqueLogEntity entity) {
        return page.setRecords(getBasicMapper().listByPage(page, entity));
    }

    @Override
    public MqWbqueLogEntity receiveDlMsg(String fromExchangeName, String fromQueueName, String dlReason, String dlMsg) {
        log.info("【DLX死信消息】收到死信消息。来自交换机：{}，来自队列名称：{}，死信原因：{}，死信消息：{}", fromExchangeName, fromQueueName, dlReason, dlMsg);
        MqWbqueLogEntity mqWbqueLogEntity = null;
        try {
            // 消息的唯一 ID
            String messageId = this.jsonDataHash(dlMsg);
            String messageTypeId = this.parseMessageTypeId(dlMsg);
            // 如果没有来自的队列，则根据 messageTypeId 推断
            if (fromQueueName == null) {
                fromQueueName = getQueueNameByMessageTypeId(messageTypeId);
            }

            // 若是新死信
            if ((mqWbqueLogEntity = this.getByMessageId(messageId)) == null) {
                mqWbqueLogEntity = new MqWbqueLogEntity();
                mqWbqueLogEntity.setMessageId(messageId);
                mqWbqueLogEntity.setMsgTypeId(messageTypeId);
                mqWbqueLogEntity.setDlReason(dlReason);
                mqWbqueLogEntity.setExchangeName(fromExchangeName);
                mqWbqueLogEntity.setQueueName(fromQueueName);
                mqWbqueLogEntity.setMsgStatus(DLXMessageStatusEnum.UNSEND.getCode());
                mqWbqueLogEntity.setRetryNum(0);
                mqWbqueLogEntity.setMsg(dlMsg);
                mqWbqueLogEntity.setMsgDt(LocalDateTime.now()); // 记录消息产生时间
                mqWbqueLogEntity.setRemoveStatus(0); // 未删除
                mqWbqueLogEntity.setNeedRetry(1); // 可重试
                save(mqWbqueLogEntity);
            }
            // 若是重复出现的死信
            else {
                // 先取消删除再更新
                this.unremove(mqWbqueLogEntity.getSid());
                mqWbqueLogEntity.setMsgTypeId(messageTypeId);
                mqWbqueLogEntity.setDlReason(dlReason);
                mqWbqueLogEntity.setExchangeName(fromExchangeName);
                mqWbqueLogEntity.setQueueName(fromQueueName);
                // 状态为重复处理
                mqWbqueLogEntity.setMsgStatus(DLXMessageStatusEnum.SEND_REPEATED.getCode());
                mqWbqueLogEntity.setRetryNum(mqWbqueLogEntity.getRetryNum() + 1); // 重试次数
                mqWbqueLogEntity.setMsg(dlMsg);
                mqWbqueLogEntity.setMsgDt(LocalDateTime.now()); // 更新消息产生时间
                mqWbqueLogEntity.setNeedRetry(1); // 可重试
                this.updateById(mqWbqueLogEntity);
            }
        }
        catch (Exception e) {
            log.error("【DLX死信消息】死信消息落库异常！", e);
        }
        return mqWbqueLogEntity;
    }

    @Override
    public void unremove(Long sid) {
        mqWbqueLogMapper.unremove(sid);
    }

    /**
     * MessageTypeId 到 QueueName 的映射
     *
     * @param messageTypeId
     * @return
     */
    private String getQueueNameByMessageTypeId(String messageTypeId) {
        Map<String, String> map = new HashMap<>();
        map.put("wb_to_que", wb2queSenderKey);
        map.put("que_to_wb", que2wbSenderKey);
        for (String key : map.keySet()) {
            if (messageTypeId.startsWith(key)) {
                return map.get(key);
            }
        }
        return "";
    }

    @Override
    public MqWbqueLogEntity getByMessageId(String messageId) {
        return mqWbqueLogMapper.findOneByMessageId(messageId);
    }

    // 根据 json 字符串获取 messageTypeId 字段值
    @Override
    public String parseMessageTypeId(String msgJsonStr) {
        String messageTypeId = "";
        DlxMsgVO vo = null;
        try {
            vo = JSON.parseObject(msgJsonStr, DlxMsgVO.class);
        }
        catch (JSONException e) {
            log.error("报文格式异常", e);
        }
        if (vo != null) messageTypeId = vo.getMessageTypeId();
        else {
            // 正则表达式匹配 "messageTypeId":"wb_to_que_unsign_create"
            String regex = "\"messageTypeId\":\"\\w*\"";
            boolean isMatch = ReUtil.isMatch(regex, msgJsonStr);
            if (isMatch) {
                String messageTypeIdStr = ReUtil.get(regex, msgJsonStr, 0);
                messageTypeId = messageTypeIdStr
                        .replace("\"messageTypeId\":\"", "")
                        .replace("\"", "");
            }
        }
        return messageTypeId;
    }

    @Override
    public String jsonDataHash(String jsonStr) {
        if (jsonStr == null) {
            jsonStr = "null";
        }
        // 去除所有的空格防止因为空格引起的加密不相等
        jsonStr = jsonStr.replaceAll(" ", "");
        return DigestUtil.sha1Hex(jsonStr);
    }
}
