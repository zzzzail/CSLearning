package com.qingtian.lcpes.modules.authpre.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseService;
import com.qingtian.lcpes.modules.authpre.entity.AcGroupEntity;

/**
 * <p>
 * 岗位表 服务类
 * </p>
 *
 * @author <a href="qingtian@shougang.com.cn">晴天</a>
 * @since 2022-05-20
 */
public interface AcGroupService extends BaseService<AcGroupEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    IPage<AcGroupEntity> listByPage(IPage<AcGroupEntity> page, AcGroupEntity entity);

}
