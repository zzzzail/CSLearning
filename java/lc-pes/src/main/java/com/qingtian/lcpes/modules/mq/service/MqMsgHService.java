package com.qingtian.lcpes.modules.mq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseService;
import com.qingtian.lcpes.modules.mq.bo.MsgStatCountBO;
import com.qingtian.lcpes.modules.mq.entity.MqMsgHEntity;
import com.qingtian.lcpes.modules.mq.vo.MsgStatCountVO;

import java.util.List;

/**
 * <p>
 * 消息历史表 服务类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
public interface MqMsgHService extends BaseService<MqMsgHEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    IPage<MqMsgHEntity> listByPage(IPage<MqMsgHEntity> page, MqMsgHEntity entity);

    /**
     * 消息统计
     *
     * @param bo
     * @return
     */
    List<MsgStatCountVO> countByBO(MsgStatCountBO bo);

    /**
     * 统计消息数量
     *
     * @param bo
     * @return
     */
    Long countMsgNumByBO(MsgStatCountBO bo);
}
