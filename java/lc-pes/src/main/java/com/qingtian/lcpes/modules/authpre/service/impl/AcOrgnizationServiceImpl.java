package com.qingtian.lcpes.modules.authpre.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseServiceImpl;
import com.qingtian.lcpes.modules.authpre.entity.AcOrgnizationEntity;
import com.qingtian.lcpes.modules.authpre.mapper.AcOrgnizationMapper;
import com.qingtian.lcpes.modules.authpre.service.AcOrgnizationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author <a href="qingtian@shougang.com.cn">晴天</a>
 * @since 2022-05-17
 */
@Service
public class AcOrgnizationServiceImpl extends BaseServiceImpl<AcOrgnizationMapper, AcOrgnizationEntity> implements AcOrgnizationService {
    @Override
    public IPage<AcOrgnizationEntity> listByPage(IPage<AcOrgnizationEntity> page, AcOrgnizationEntity entity) {
        return page.setRecords(getBasicMapper().listByPage(page, entity));
    }

    @Override
    public List<AcOrgnizationEntity> findOrgTree(Map<String, Object> var1) {
        return getBasicMapper().findOrgTree(var1);
    }

    @Override
    public Boolean updateOrgFlag(AcOrgnizationEntity entity) {
        if (entity.getOrgFlag() != null && entity.getSid() != null) {
            return getBasicMapper().updateOrgFlag(entity);
        }
        else {
            return false;
        }
    }

    @Override
    public Boolean doSynOrg() {
        // TODO: 2022/5/31
        QueryWrapper<AcOrgnizationEntity> orgWrapper = new QueryWrapper<>();
        orgWrapper.eq("ORG_FULL_NAME", "京唐属地化");
        AcOrgnizationEntity entity = this.getOne(orgWrapper);
        entity.setUpdatedDt(LocalDateTime.now());
        return updateById(entity);
    }

    @Override
    public LocalDateTime getSynDate() {
        QueryWrapper<AcOrgnizationEntity> orgWrapper = new QueryWrapper<>();
        orgWrapper.eq("ORG_FULL_NAME", "京唐属地化");
        AcOrgnizationEntity entity = this.getOne(orgWrapper);
        return entity.getUpdatedDt();
    }
}
