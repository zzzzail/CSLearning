package com.qingtian.lcpes.modules.mq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseServiceImpl;
import com.qingtian.lcpes.modules.mq.bo.MsgStatCountBO;
import com.qingtian.lcpes.modules.mq.entity.MqMsgHEntity;
import com.qingtian.lcpes.modules.mq.mapper.MqMsgHMapper;
import com.qingtian.lcpes.modules.mq.service.MqMsgHService;
import com.qingtian.lcpes.modules.mq.vo.MsgStatCountVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 消息历史表 服务实现类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Service
public class MqMsgHServiceImpl extends BaseServiceImpl<MqMsgHMapper, MqMsgHEntity> implements MqMsgHService {
    @Override
    public IPage<MqMsgHEntity> listByPage(IPage<MqMsgHEntity> page, MqMsgHEntity entity) {
        return page.setRecords(getBasicMapper().listByPage(page, entity));
    }

    @Override
    public List<MsgStatCountVO> countByBO(MsgStatCountBO bo) {
        return getBasicMapper().countByBO(bo);
    }

    @Override
    public Long countMsgNumByBO(MsgStatCountBO bo) {
        Long res = getBasicMapper().countMsgNumByBO(bo);
        return res == null ? 0 : res;
    }
}
