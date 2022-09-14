package com.qingtian.lcpes.modules.authpre.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseServiceImpl;
import com.qingtian.lcpes.modules.authpre.entity.AcGroupEntity;
import com.qingtian.lcpes.modules.authpre.mapper.AcGroupMapper;
import com.qingtian.lcpes.modules.authpre.service.AcGroupService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位表 服务实现类
 * </p>
 *
 * @author <a href="qingtian@shougang.com.cn">晴天</a>
 * @since 2022-05-20
 */
@Service
public class AcGroupServiceImpl extends BaseServiceImpl<AcGroupMapper, AcGroupEntity> implements AcGroupService {
    @Override
    public IPage<AcGroupEntity> listByPage(IPage<AcGroupEntity> page, AcGroupEntity entity) {
        return page.setRecords(getBasicMapper().listByPage(page, entity));
    }
}
