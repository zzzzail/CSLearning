package com.qingtian.lcpes.modules.mq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseService;
import com.qingtian.lcpes.modules.mq.bo.MsgStatCountBO;
import com.qingtian.lcpes.modules.mq.entity.MqMsgEntity;
import com.qingtian.lcpes.modules.mq.jmsg.NodeInfo;
import com.qingtian.lcpes.modules.mq.vo.MsgStatCountVO;

import java.util.List;

/**
 * <p>
 * 消息表 服务类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
public interface MqMsgService extends BaseService<MqMsgEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    IPage<MqMsgEntity> listByPage(IPage<MqMsgEntity> page, MqMsgEntity entity);

    MqMsgEntity doRecieveMsg(String jsonStr);

    MqMsgEntity doSendMsg(NodeInfo nodeInfo);

    MqMsgEntity manualSendRecvMsg(String jsonStr);

    void processMsg(MqMsgEntity msgEntity);

    void archiveMsg(List<MqMsgEntity> entityList);

    List<MsgStatCountVO> countByBO(MsgStatCountBO bo);

    List<MsgStatCountVO> countMsgAndMsgHByBO(MsgStatCountBO bo);

    Long countMsgNumByBO(MsgStatCountBO bo);

    Long countMsgNumByBOWithMsgH(MsgStatCountBO bo);
}
