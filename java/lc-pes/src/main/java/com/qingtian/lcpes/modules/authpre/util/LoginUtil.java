package com.qingtian.lcpes.modules.authpre.util;

import com.qingtian.lcpes.base.ApplicationContextHolder;
import com.qingtian.lcpes.modules.authpre.vo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author J
 * @date 2022/5/10 10:09
 */
@Slf4j
public class LoginUtil {
    private static final ThreadLocal<LoginUser> threadUser = new ThreadLocal<>();

    private static final StringRedisTemplate stringRedisTemplate = ApplicationContextHolder.getBean(StringRedisTemplate.class);

    public LoginUtil() {
    }

    public static void init(LoginUser user) {
        threadUser.set(user);
    }

    public static void remove() {
        threadUser.remove();
    }


    public static Long getUserSid() {
        LoginUser loginUser = threadUser.get();
        Long userSid = null;
        if (loginUser != null) {
            userSid = loginUser.getUserSid();
        }
        return userSid;
    }

    public static String getLoginId() {
        LoginUser loginUser = threadUser.get();
        String loginId = null;
        if (loginUser != null) {
            loginId = loginUser.getLoginId();
        }
        return loginId;
    }

    public static String getUserName() {
        LoginUser loginUser = threadUser.get();
        String userName = null;
        if (loginUser != null) {
            userName = loginUser.getUsername();
        }
        return userName;
    }

    public static String getOrgCode() {
        LoginUser loginUser = threadUser.get();
        String orgCode = null;
        if (loginUser != null) {
            orgCode = loginUser.getOrgCode();
        }
        return orgCode;
    }

    public static String getOrgName() {
        LoginUser loginUser = threadUser.get();
        String orgName = null;
        if (loginUser != null) {
            orgName = loginUser.getOrgName();
        }
        return orgName;
    }

    public static String getPositionCode() {
        LoginUser loginUser = threadUser.get();
        String positionCode = null;
        if (loginUser != null) {
            positionCode = loginUser.getPositionCode();
        }
        return positionCode;
    }

    public static String getPositionName() {
        LoginUser loginUser = threadUser.get();
        String positionName = null;
        if (loginUser != null) {
            positionName = loginUser.getPositionName();
        }
        return positionName;
    }

    public static String getBizWarehouseSidsRedis(String loginId) {
        String bizWarehouseSids = null;
        loginId = loginId == null ? LoginUtil.getLoginId() : loginId;
        if (stringRedisTemplate.hasKey("user:biz:warehouse:" + loginId)) {
            bizWarehouseSids = stringRedisTemplate.opsForValue().get("user:biz:warehouse:" + loginId);
        }
        return bizWarehouseSids;
    }


    public static String getBizLocationSidsRedis(String loginId) {
        String bizLocationSids = null;
        loginId = loginId == null ? LoginUtil.getLoginId() : loginId;
        if (stringRedisTemplate.hasKey("user:biz:location:" + loginId)) {
            bizLocationSids = stringRedisTemplate.opsForValue().get("user:biz:location:" + loginId);
        }
        return bizLocationSids;
    }

    public static String getBizWorkItemSidsRedis(String loginId) {
        String bizWorkItemSids = null;
        loginId = loginId == null ? LoginUtil.getLoginId() : loginId;
        if (stringRedisTemplate.hasKey("user:biz:workitem:" + loginId)) {
            bizWorkItemSids = stringRedisTemplate.opsForValue().get("user:biz:workitem:" + loginId);
        }
        return bizWorkItemSids;
    }

    public static String getBizFleetSidsRedis(String loginId) {
        String bizfleetSids = null;
        loginId = loginId == null ? LoginUtil.getLoginId() : loginId;
        if (stringRedisTemplate.hasKey("user:biz:fleet:" + loginId)) {
            bizfleetSids = stringRedisTemplate.opsForValue().get("user:biz:fleet:" + loginId);
        }
        return bizfleetSids;
    }

    public static String getBizPOrgSidsRedis(String loginId) {
        String bizPOrgSids = null;
        loginId = loginId == null ? LoginUtil.getLoginId() : loginId;
        if (stringRedisTemplate.hasKey("user:biz:porg:" + loginId)) {
            bizPOrgSids = stringRedisTemplate.opsForValue().get("user:biz:porg:" + loginId);
        }
        return bizPOrgSids;
    }

    public static String getBizTaskTypesRedis(String loginId) {
        String bizTaskTypes = null;
        loginId = loginId == null ? LoginUtil.getLoginId() : loginId;
        if (stringRedisTemplate.hasKey("user:biz:tasktype:" + loginId)) {
            bizTaskTypes = stringRedisTemplate.opsForValue().get("user:biz:tasktype:" + loginId);
        }
        return bizTaskTypes;
    }


}
