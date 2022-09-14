package com.qingtian.lcpes.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseService;
import com.qingtian.lcpes.modules.sys.entity.SysLogEntity;

/**
 * <p>
 * 系统日志表 服务类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
public interface SysLogService extends BaseService<SysLogEntity> {

    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    IPage<SysLogEntity> listByPage(IPage<SysLogEntity> page, SysLogEntity entity);

    /**
     * 保存登录日志
     *
     * @param loginId 登陆 ID
     * @param flag 1 成功 2 失败
     */
    void saveLoginLog(String loginId, Integer flag);

    /**
     * 保存登出日志
     *
     * @param loginId 登陆 ID
     * @param flag 1 成功 2 失败
     */
    void saveLogoutLog(String loginId, Integer flag);
}
