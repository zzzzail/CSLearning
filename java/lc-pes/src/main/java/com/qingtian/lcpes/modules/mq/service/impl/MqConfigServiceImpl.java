package com.qingtian.lcpes.modules.mq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseServiceImpl;
import com.qingtian.lcpes.modules.mq.entity.MqConfigEntity;
import com.qingtian.lcpes.modules.mq.mapper.MqConfigMapper;
import com.qingtian.lcpes.modules.mq.service.MqConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息配置表 服务实现类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Service
public class MqConfigServiceImpl extends BaseServiceImpl<MqConfigMapper, MqConfigEntity> implements MqConfigService {
    @Override
    public IPage<MqConfigEntity> listByPage(IPage<MqConfigEntity> page, MqConfigEntity entity) {
        return page.setRecords(getBasicMapper().listByPage(page, entity));
    }

    @Override
    public MqConfigEntity getConfigByMsgId(String msgId) {
        QueryWrapper<MqConfigEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("msg_Id", msgId);
        return this.getOne(wrapper, true);
    }
}
