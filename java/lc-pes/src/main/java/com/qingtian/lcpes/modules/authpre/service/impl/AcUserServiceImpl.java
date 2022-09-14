package com.qingtian.lcpes.modules.authpre.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qingtian.lcpes.base.exception.QTBusinessException;
import com.qingtian.lcpes.base.facade.BaseServiceImpl;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.modules.authpre.entity.AcGroupEntity;
import com.qingtian.lcpes.modules.authpre.entity.AcOrgnizationEntity;
import com.qingtian.lcpes.modules.authpre.entity.AcUserEntity;
import com.qingtian.lcpes.modules.authpre.entity.AuthUserEntity;
import com.qingtian.lcpes.modules.authpre.mapper.AcUserMapper;
import com.qingtian.lcpes.modules.authpre.mapper.AuthGroupMapper;
import com.qingtian.lcpes.modules.authpre.mapper.AuthOrgnizationMapper;
import com.qingtian.lcpes.modules.authpre.mapper.AuthUserMapper;
import com.qingtian.lcpes.modules.authpre.service.AcGroupService;
import com.qingtian.lcpes.modules.authpre.service.AcOrgnizationService;
import com.qingtian.lcpes.modules.authpre.service.AcUserService;
import com.qingtian.lcpes.modules.authpre.service.AuthpreService;
import com.qingtian.lcpes.modules.authpre.vo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author <a href="qingtian@shougang.com.cn">晴天</a>
 * @since 2022-05-17
 */
@Service
@Slf4j
public class AcUserServiceImpl extends BaseServiceImpl<AcUserMapper, AcUserEntity> implements AcUserService {

    @Autowired
    private AuthpreService authpreService;
    @Autowired
    private AcGroupService acGroupService;
    @Autowired
    private AcOrgnizationService acOrgnizationService;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private AcUserMapper acUserMapper;
    @Autowired
    private AuthOrgnizationMapper authOrgnizationMapper;
    @Autowired
    private AuthGroupMapper authGroupMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public IPage<AcUserEntity> listByPage(IPage<AcUserEntity> page, AcUserEntity entity) {
        return page.setRecords(getBasicMapper().listByPage(page, entity));
    }

    @Override
    public Integer getAsynCode(String loginId) {

        LoginUser redisUser = authpreService.getUserInfo(loginId);
        AcUserEntity itmpUser = getUserFromItmpDB(loginId);

        if (itmpUser == null) {
            log.info("no userinfo find");
            return 2;
        }
        else if (itmpUser.getOrgId().equalsIgnoreCase(redisUser.getOrgCode())
                && itmpUser.getUserPostId().equalsIgnoreCase(redisUser.getPositionCode())) {
            log.info("have accuracy userinfo, nothing to do");
            return 1;
        }
        else {
            log.info("userinfo need to update");
            return 3;
        }
    }

    @Override
    public void asynUserInfo(String loginId) {
        switch (getAsynCode(loginId)) {
            case 2:
                saveUserToItmpDB(loginId);
                break;
            case 3:
                updateUserToItmpDB(loginId);
                break;
            default:
                break;
        }
    }


    /**
     * 2 保存用户信息
     *
     * @param loginId
     */
    void saveUserToItmpDB(String loginId) {

        log.info("no user exists in itmp oracle , let me save form auth oracle");
        // 用户
        AuthUserEntity authUser = getUserFromAuthDB(loginId);
        AuthUserEntity authUserEx = getUserExFromAuthDB(loginId);

        AcUserEntity itmpUser = BeanCopyUtils.copy(authUser, AcUserEntity.class);

        //岗位ID、组织机构ID字段重新赋值
        itmpUser.setUserPostId(authUserEx.getUserPostId());
        itmpUser.setOrgId(authUserEx.getOrgId());
        this.save(itmpUser);

        savePostAndOrg(itmpUser.getUserPostId(), itmpUser.getOrgId());
    }

    /**
     * 3 修改用户信息
     *
     * @param loginId
     */
    void updateUserToItmpDB(String loginId) {
        QueryWrapper<AcUserEntity> userWrapper = new QueryWrapper<>();
        userWrapper.eq("LOGIN_ID", loginId);
        AcUserEntity itmpUserOld = this.getOne(userWrapper);
        this.removeById(itmpUserOld);

        saveUserToItmpDB(loginId);
    }


    void savePostAndOrg(String postId, String orgId) {
        //岗位
        QueryWrapper<AcGroupEntity> postWrapper = new QueryWrapper<>();
        postWrapper.eq("GROUP_ID", postId);
        AcGroupEntity itmpPost = this.acGroupService.getOne(postWrapper);
        if (itmpPost == null) {
            itmpPost = this.getPostFromAuthDB(postId);
            log.info("save itmp post");
            this.acGroupService.save(itmpPost);
        }

        //组织机构
        QueryWrapper<AcOrgnizationEntity> orgWrapper = new QueryWrapper<>();
        orgWrapper.eq("ORG_ID", orgId);
        AcOrgnizationEntity itmpOrg = this.acOrgnizationService.getOne(orgWrapper);
        if (itmpOrg == null) {
            itmpOrg = this.getOrgFromAuthDB(orgId);
            log.info("save itmp org");
            this.acOrgnizationService.save(itmpOrg);
        }
    }

    /**
     * 从授权库获取用户信息
     */
    //@DS("auth")
    public AuthUserEntity getUserFromAuthDB(String loginId) {
        QueryWrapper<AuthUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("LOGIN_ID", loginId);
        AuthUserEntity authUserEntity = authUserMapper.selectOne(queryWrapper);
        if (authUserEntity == null) {
            return null;
        }
        log.info("auth oracle user:" + authUserEntity);
        return authUserEntity;
    }

    /**
     * 从授权库获取岗位ID、组织机构ID
     */
    //@DS("auth")
    public AuthUserEntity getUserExFromAuthDB(String loginId) {
        return authUserMapper.getUserEx(loginId);
    }

    /**
     * 从物流库获取用户信息
     */
    public AcUserEntity getUserFromItmpDB(String loginId) {
        QueryWrapper<AcUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("LOGIN_ID", loginId);
        AcUserEntity acUserEntity = this.getOne(queryWrapper);
        if (acUserEntity == null) {
            return null;
        }
        log.info("itmp oracle user:" + acUserEntity);
        return acUserEntity;
    }

    /**
     * 从授权库获取岗位信息
     */
    //@DS("auth")
    public AcGroupEntity getPostFromAuthDB(String postId) {
        QueryWrapper<AcGroupEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("GROUP_ID", postId);
        AcGroupEntity acGroupEntity = authGroupMapper.selectOne(queryWrapper);
        if (acGroupEntity == null) {
            return null;
        }
        log.info("auth oracle post:" + acGroupEntity);
        return acGroupEntity;
    }

    /**
     * 从授权库获取组织机构信息
     */
    //@DS("auth")
    public AcOrgnizationEntity getOrgFromAuthDB(String orgId) {
        QueryWrapper<AcOrgnizationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ORG_ID", orgId);
        AcOrgnizationEntity acOrgnizationEntity = authOrgnizationMapper.selectOne(queryWrapper);
        if (acOrgnizationEntity == null) {
            return null;
        }
        log.info("auth oracle org:" + acOrgnizationEntity);
        return acOrgnizationEntity;
    }


    @Override
    public Boolean doSynUser() {
        QueryWrapper<AcUserEntity> userWrapper = new QueryWrapper<>();
        userWrapper.eq("LOGIN_ID", "10011839");
        AcUserEntity entity = this.getOne(userWrapper);
        entity.setUpdatedDt(LocalDateTime.now());
        return updateById(entity);
    }

    @Override
    public LocalDateTime getSynDate() {
        QueryWrapper<AcUserEntity> userWrapper = new QueryWrapper<>();
        userWrapper.eq("LOGIN_ID", "10011839");
        AcUserEntity entity = this.getOne(userWrapper);
        return entity.getUpdatedDt();
    }

    @Override
    public AcUserEntity getAcUserByLoginId(String loginId) {
        QueryWrapper<AcUserEntity> userWrapper = new QueryWrapper<>();
        userWrapper.eq("LOGIN_ID", loginId);
        return this.getOne(userWrapper);
    }


    @Override
    public String getBizWarehouseSids(String loginId) {
        return acUserMapper.getBizWarehouseSids(loginId);
    }


    @Override
    public String getBizLocationSids(String loginId) {
        return acUserMapper.getBizLocationSids(loginId);
    }

    @Override
    public String getBizWorkItemSids(String loginId) {
        return acUserMapper.getBizWorkItemSids(loginId);
    }

    @Override
    public String getBizFleetSids(String loginId) {
        return acUserMapper.getBizFleetSids(loginId);
    }

    @Override
    public String getBizPOrgSids(String loginId) {
        return acUserMapper.getBizPOrgSids(loginId);
    }

    @Override
    public String getBizTaskTypes(String loginId) {
        return acUserMapper.getBizTaskTypes(loginId);
    }

    @Override
    public Boolean setBizToRedis(String loginId) {
        Boolean result;
        try {
            /*
            //作业部
            doBizRedis("user:biz:porg:" + loginId, getBizPOrgSids(loginId));

            //仓库
            doBizRedis("user:biz:warehouse:" + loginId, getBizWarehouseSids(loginId));

            //地点
            doBizRedis("user:biz:location:" + loginId, getBizLocationSids(loginId));

            //作业项目
            doBizRedis("user:biz:workitem:" + loginId, getBizWorkItemSids(loginId));

            //车队
            doBizRedis("user:biz:fleet:" + loginId, getBizFleetSids(loginId));

            //需求提报类型
            doBizRedis("user:biz:tasktype:" + loginId, getBizTaskTypes(loginId));
            */
            result = true;
        }
        catch (QTBusinessException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    void doBizRedis(String key, String value) {
        if (StringUtils.isNotBlank(value)) {
            this.stringRedisTemplate.opsForValue().set(key, value);
        }
        else {
            this.stringRedisTemplate.delete(key);
        }
    }

}
