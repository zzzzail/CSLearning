package com.qingtian.lcpes.modules.authpre.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseService;
import com.qingtian.lcpes.modules.authpre.entity.AcOrgnizationEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组织机构 服务类
 * </p>
 *
 * @author <a href="qingtian@shougang.com.cn">晴天</a>
 * @since 2022-05-17
 */
public interface AcOrgnizationService extends BaseService<AcOrgnizationEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    IPage<AcOrgnizationEntity> listByPage(IPage<AcOrgnizationEntity> page, AcOrgnizationEntity entity);

    /**
     * 查询组织机构树
     *
     * @param var1
     * @return
     */
    public List<AcOrgnizationEntity> findOrgTree(Map<String, Object> var1);


    /**
     * 变更作业部标识
     * 0否  |    1是   |   2 不区分
     *
     * @param entity
     * @return
     */
    public Boolean updateOrgFlag(AcOrgnizationEntity entity);


    /**
     * 手动同步组织机构
     *
     * @return
     */
    Boolean doSynOrg();

    /**
     * 最近一次手动同步组织机构的时间
     *
     * @return
     */
    LocalDateTime getSynDate();


}
