package com.qingtian.lcpes.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseServiceImpl;
import com.qingtian.lcpes.modules.sys.entity.CommonNoticeEntity;
import com.qingtian.lcpes.modules.sys.mapper.CommonNoticeMapper;
import com.qingtian.lcpes.modules.sys.service.CommonNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告管理表 服务实现类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Service
public class CommonNoticeServiceImpl extends BaseServiceImpl<CommonNoticeMapper, CommonNoticeEntity> implements CommonNoticeService {

    @Autowired
    private CommonNoticeMapper commonNoticeMapper;

    @Override
    public IPage<CommonNoticeEntity> listByPage(IPage<CommonNoticeEntity> page, CommonNoticeEntity entity) {
        return page.setRecords(getBasicMapper().listByPage(page, entity));
    }

    @Override
    public boolean updateBathReadFlag(CommonNoticeEntity entity) {
        return commonNoticeMapper.updateBathReadFlag(entity);
    }
}
