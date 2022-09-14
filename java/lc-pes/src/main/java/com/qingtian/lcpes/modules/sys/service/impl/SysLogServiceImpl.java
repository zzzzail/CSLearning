package com.qingtian.lcpes.modules.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseServiceImpl;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.util.IPUtils;
import com.qingtian.lcpes.modules.sys.entity.SysLogEntity;
import com.qingtian.lcpes.modules.sys.mapper.SysLogMapper;
import com.qingtian.lcpes.modules.sys.service.SysLogService;
import com.qingtian.lcpes.modules.sys.vo.SysLogVO;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLogEntity> implements SysLogService {
    @Override
    public IPage<SysLogEntity> listByPage(IPage<SysLogEntity> page, SysLogEntity entity) {
        return page.setRecords(getBasicMapper().listByPage(page, entity));
    }

    @Override
    public void saveLoginLog(String loginId, Integer flag) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString() + "?" + request.getQueryString();
        String ip = IPUtils.getIp(request);

        long startTime = System.currentTimeMillis();
        // 记录登录日志
        SysLogVO sysLogVO = new SysLogVO();
        sysLogVO.setUserId(loginId);
        sysLogVO.setLogType("1"); // 日志类型
        sysLogVO.setLogContent("权限集成");
        sysLogVO.setOperateType("登录");
        sysLogVO.setRequestUrl(url);
        sysLogVO.setIp(ip);
        sysLogVO.setCreatedDt(LocalDateTime.now()); // 设置创建时间
        // 控制台显示
        sysLogVO.setResultMsg(1 == flag ? "成功" : "失败");
        sysLogVO.setCostTime(BigDecimal.valueOf(System.currentTimeMillis() - startTime)); // 设置耗时

        // 保存日志
        SysLogEntity sysLogEntity = new SysLogEntity();
        BeanCopyUtils.copy(sysLogVO, sysLogEntity);
        this.save(sysLogEntity);
    }

    @Override
    public void saveLogoutLog(String loginId, Integer flag) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString() + "?" + request.getQueryString();
        String ip = IPUtils.getIp(request);

        // 记录登出日志
        SysLogVO sysLogVO = new SysLogVO();
        // 任务开始创建时间
        long startTime = System.currentTimeMillis();
        sysLogVO.setUserId(loginId);
        sysLogVO.setLogType("1"); // 日志类型
        sysLogVO.setLogContent("权限集成");
        sysLogVO.setOperateType("登出");
        sysLogVO.setRequestUrl(url);
        sysLogVO.setIp(ip);
        sysLogVO.setCreatedDt(LocalDateTime.now()); // 设置创建时间
        sysLogVO.setResultMsg(1 == flag ? "成功" : "失败");
        sysLogVO.setCostTime(BigDecimal.valueOf(System.currentTimeMillis() - startTime)); // 设置耗时

        // 保存日志
        SysLogEntity sysLogEntity = new SysLogEntity();
        BeanCopyUtils.copy(sysLogVO, sysLogEntity);
        this.save(sysLogEntity);
    }
}
