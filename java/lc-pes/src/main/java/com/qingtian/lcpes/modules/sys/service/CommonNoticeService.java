package com.qingtian.lcpes.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseService;
import com.qingtian.lcpes.modules.sys.entity.CommonNoticeEntity;

/**
 * <p>
 * 公告管理表 服务类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
public interface CommonNoticeService extends BaseService<CommonNoticeEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    IPage<CommonNoticeEntity> listByPage(IPage<CommonNoticeEntity> page, CommonNoticeEntity entity);
    boolean updateBathReadFlag(CommonNoticeEntity entity);
}
