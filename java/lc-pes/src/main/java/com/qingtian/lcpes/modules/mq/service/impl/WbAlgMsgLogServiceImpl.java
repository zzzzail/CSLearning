package com.qingtian.lcpes.modules.mq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingtian.lcpes.base.facade.BaseServiceImpl;
import com.qingtian.lcpes.modules.mq.entity.WbAlgMsgLogEntity;
import com.qingtian.lcpes.modules.mq.mapper.WbAlgMsgLogMapper;
import com.qingtian.lcpes.modules.mq.service.WbAlgMsgLogService;
import com.qingtian.lcpes.modules.mq.vo.WbAlgMsgLogVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 算法接口日志表 服务实现类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Service
public class WbAlgMsgLogServiceImpl extends BaseServiceImpl<WbAlgMsgLogMapper, WbAlgMsgLogEntity> implements WbAlgMsgLogService {
    @Override
    public IPage<WbAlgMsgLogEntity> listByPage(IPage<WbAlgMsgLogEntity> page, WbAlgMsgLogEntity entity) {
        return page.setRecords(getBasicMapper().listByPage(page, entity));
    }

    @Override
    public IPage<WbAlgMsgLogVO> pageByParams(Map<String, Object> params) {
        Integer pageNum = (Integer) params.getOrDefault("pageNum",1);
        Integer pageSize = (Integer) params.getOrDefault("pageSize",10);
        IPage<WbAlgMsgLogVO> page = new Page<>(pageNum,pageSize);
        List<WbAlgMsgLogVO> list = basicMapper.pageByParams(page,params);
        page.setRecords(list);
        return page;
    }
}
