package com.qingtian.lcpes.modules.mq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.util.StringUtil;
import com.qingtian.lcpes.base.exception.QTBusinessException;
import com.qingtian.lcpes.base.facade.BaseFacadeImpl;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.util.LocalDateTimeUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.mq.bo.MsgStatBO;
import com.qingtian.lcpes.modules.mq.bo.MsgStatCountBO;
import com.qingtian.lcpes.modules.mq.bo.WbAlgMsgLogBO;
import com.qingtian.lcpes.modules.mq.converter.MsgStatConverter;
import com.qingtian.lcpes.modules.mq.entity.MsgStatEntity;
import com.qingtian.lcpes.modules.mq.enums.MsgStatPlatformTypeEnum;
import com.qingtian.lcpes.modules.mq.enums.MsgStateEnum;
import com.qingtian.lcpes.modules.mq.service.MsgStatService;
import com.qingtian.lcpes.modules.mq.vo.MsgStatCountVO;
import com.qingtian.lcpes.modules.mq.vo.MsgStatSummaryStatisticsVO;
import com.qingtian.lcpes.modules.mq.vo.MsgStatVO;
import com.qingtian.lcpes.modules.mq.vo.WbAlgMsgLogStatVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 消息统计表 前端控制器
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Controller
@RequestMapping("/msg/msg-stat")
@Slf4j
public class MsgStatController extends BaseFacadeImpl<MsgStatService, MsgStatEntity, MsgStatVO> {
    @Autowired
    private MsgStatService msgStatService;

    /**
     * 自定义分页查询
     *
     * @param jsonRequest
     * @return
     */
    @PostMapping(value = "/listByPage")
    @ResponseBody
    public JsonResponse<PageDTO<MsgStatVO>> listByPage(@RequestBody JsonRequest<MsgStatVO> jsonRequest) {
        JsonResponse<PageDTO<MsgStatVO>> jsonResponse = new JsonResponse<>();
        try {
            MsgStatVO vo = jsonRequest.getReqBody();
            MsgStatEntity entity = BeanCopyUtils.copy(vo, super.entityClass);
            IPage<MsgStatEntity> iPage = msgStatService.listByPage(new Page<>(vo.getPageNum(), vo.getPageSize()), entity);

            List<MsgStatVO> voList = BeanCopyUtils.copyList(iPage.getRecords(), MsgStatVO.class);
            PageDTO<MsgStatVO> pageDTO = new PageDTO<>(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
            pageDTO.setTotal(iPage.getTotal());
            pageDTO.setResultData(voList);

            jsonResponse.setRspBody(pageDTO);
        }
        catch (Exception e) {
            log.error("查询失败", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("查询失败");
        }
        return jsonResponse;
    }


    /**
     * 消息统计
     *
     * @param jsonRequest
     * @return
     */
    @PostMapping("/counts")
    @ResponseBody
    public JsonResponse<MsgStatSummaryStatisticsVO> counts(@RequestBody JsonRequest<MsgStatBO> jsonRequest) {
        JsonResponse<MsgStatSummaryStatisticsVO> jsonResponse = new JsonResponse<>();
        try {
            MsgStatBO bo = jsonRequest.getReqBody();
            if (StringUtil.isEmpty(bo.getPlatformType())) {
                bo.setPlatformType(MsgStatPlatformTypeEnum.MSG.getCode()); // 默认消息平台
            }

            MsgStatSummaryStatisticsVO vo = this.stat(bo);
            if (MsgStatPlatformTypeEnum.MSG.getCode().equals(bo.getPlatformType())) {
                String listStr = "[" + "{\"text\": \"消息总数量\", \"val\": \"" + vo.getSum() + "\"}," + "{\"text\": \"本周消息数\", \"val\": \"" + vo.getSumThisWeek() + "\"}," + "{\"text\": \"今日消息数\", \"val\": \"" + vo.getSumToday() + "\"}," + "{\"text\": \"今日消息发送成功率\", \"val\": \"" + vo.getSuccessRateToday() + "\"}," + "{\"text\": \"死信总数量\", \"val\": \"" + vo.getSumDl() + "\"}" + "]";
                vo.setRemark01(listStr);
            }
            else if (MsgStatPlatformTypeEnum.ALG.getCode().equals(bo.getPlatformType())) {
                String listStr = "[" + "{\"text\": \"消息总数量\", \"val\": \"" + vo.getSum() + "\"}," + "{\"text\": \"本周消息数\", \"val\": \"" + vo.getSumThisWeek() + "\"}," + "{\"text\": \"今日消息数\", \"val\": \"" + vo.getSumToday() + "\"}," + "{\"text\": \"今日消息发送成功率\", \"val\": \"" + vo.getSuccessRateToday() + "\"}" + "]";
                vo.setRemark01(listStr);
            }
            jsonResponse.setRspBody(vo);
        }
        catch (QTBusinessException qtbe) {
            log.error("业务异常", qtbe);
            jsonResponse.setRetCode(qtbe.getCode());
            jsonResponse.setRetDesc(qtbe.getMessage());
        }
        catch (Exception e) {
            log.error("查询失败", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("查询失败");
        }
        return jsonResponse;
    }

    public MsgStatSummaryStatisticsVO stat(MsgStatBO bo) {
        MsgStatSummaryStatisticsVO vo = new MsgStatSummaryStatisticsVO();
        // 今天新产生的消息数
        Long sumToday0 = 0L;
        Long sumToday1 = 0L;
        Long sumToday = 0L;
        if (MsgStatPlatformTypeEnum.MSG.getCode().equals(bo.getPlatformType())) {
            MsgStatCountBO searchBO = new MsgStatCountBO();
            searchBO.setStartTime(LocalDateTimeUtils.todayStartTime());
            searchBO.setEndTime(LocalDateTimeUtils.todayEndTime());
            searchBO.setMsgState(MsgStateEnum.FAIL.getCode());
            sumToday0 = null; // msgApi.countMsgNumInMsgAndMsgHByBO(searchBO).getRetContent();
            searchBO.setMsgState(MsgStateEnum.SUCCESS.getCode());
            sumToday1 = null; // msgApi.countMsgNumInMsgAndMsgHByBO(searchBO).getRetContent();
        }
        else if (MsgStatPlatformTypeEnum.ALG.getCode().equals(bo.getPlatformType())) {
            WbAlgMsgLogBO searchBO = new WbAlgMsgLogBO();
            searchBO.setStartTime(LocalDateTimeUtils.todayStartTime());
            searchBO.setEndTime(LocalDateTimeUtils.todayEndTime());
            searchBO.setMsgStatus(2);
            sumToday0 = null; // wbAlgMsgLogApi.countMsgNumByBO(searchBO).getRetContent();
            searchBO.setMsgStatus(1);
            sumToday1 = null; // wbAlgMsgLogApi.countMsgNumByBO(searchBO).getRetContent();
        }
        sumToday = sumToday0 + sumToday1;
        vo.setSumToday(sumToday);

        // 今日消息发送成功率
        if (sumToday1 != 0) vo.setSuccessRateToday(1F - (float) (sumToday0 / sumToday1));
        else vo.setSuccessRateToday(1F);

        // 总数
        Long sumAll = msgStatService.sumNumBy(bo.getPlatformType(), null, null, null);
        if (sumAll == null) sumAll = 0L;
        sumAll += sumToday;
        vo.setSum(sumAll);

        // 本周数量
        Long sumWeek = msgStatService.sumNumBy(bo.getPlatformType(), null, LocalDateTimeUtils.weekStartTime(), LocalDateTimeUtils.weekStartTime());
        if (sumWeek == null) sumWeek = 0L;
        sumWeek += sumToday;
        vo.setSumThisWeek(sumWeek);

        // 死信总数量
        int sumDl = 0; // mqWbqueLogService.count();
        vo.setSumDl((long) sumDl);
        return vo;
    }

    /**
     * 消息统计
     *
     * @param jsonRequest
     * @return
     */
    @PostMapping("/statistics")
    @ResponseBody
    public JsonResponse<List<MsgStatVO>> statistics(@RequestBody JsonRequest<MsgStatBO> jsonRequest) {
        JsonResponse<List<MsgStatVO>> jsonResponse = new JsonResponse<>();
        try {
            MsgStatBO bo = jsonRequest.getReqBody();
            if (StringUtil.isEmpty(bo.getPlatformType())) {
                bo.setPlatformType(MsgStatPlatformTypeEnum.MSG.getCode()); // 默认消息平台
            }
            // 默认时间为今天一天
            if (bo.getStartTime() == null) {
                bo.setStartTime(LocalDateTimeUtils.todayStartTime());
            }
            if (bo.getEndTime() == null) {
                bo.setEndTime(LocalDateTimeUtils.todayEndTime());
            }

            // 从消息统计表中查找
            List<MsgStatVO> list = this.listByBO(bo);
            jsonResponse.setRspBody(list);
        }
        catch (QTBusinessException qtbe) {
            log.error("业务异常", qtbe);
            jsonResponse.setRetCode(qtbe.getCode());
            jsonResponse.setRetDesc(qtbe.getMessage());
        }
        catch (Exception e) {
            log.error("查询失败", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("查询失败");
        }
        return jsonResponse;
    }

    public List<MsgStatVO> listByBO(MsgStatBO bo) {
        List<MsgStatVO> list = msgStatService.listByBO(bo);

        // 找出需要查找的今天时间 时间段
        LocalDateTime todayStartTime = LocalDateTimeUtils.todayStartTime();
        LocalDateTime todayEndTime = LocalDateTimeUtils.todayEndTime();
        // 如果时间段不符合标准
        if (bo.getStartTime().isAfter(todayEndTime) || bo.getEndTime().isBefore(todayStartTime)) {
            return null;
        }

        if (!bo.getStartTime().isBefore(todayStartTime)) {
            todayStartTime = bo.getStartTime();
        }
        if (bo.getEndTime().isBefore(todayEndTime)) {
            todayEndTime = bo.getEndTime();
        }

        // 消息平台的今日消息
        if (MsgStatPlatformTypeEnum.MSG.getCode().equals(bo.getPlatformType())) {
            MsgStatCountBO searchBO = new MsgStatCountBO();
            searchBO.setStartTime(todayStartTime);
            searchBO.setEndTime(todayEndTime);
            List<MsgStatCountVO> msgList = null; // msgApi.countByBO(searchBO).getRetContent();

            List<MsgStatVO> voList = MsgStatConverter.msgStatCountVOs2MsgStatVOs(msgList);
            list.addAll(voList);
        }
        // 算法平台的今日消息
        else if (MsgStatPlatformTypeEnum.ALG.getCode().equals(bo.getPlatformType())) {
            WbAlgMsgLogBO searchBO = new WbAlgMsgLogBO();
            searchBO.setStartTime(todayStartTime);
            searchBO.setEndTime(todayEndTime);
            List<WbAlgMsgLogStatVO> algMsgList = null; // wbAlgMsgLogApi.countByBO(searchBO).getRetContent();

            List<MsgStatVO> voList = MsgStatConverter.wbAlgMsgLogStatVOs2MsgStatVOs(algMsgList);
            list.addAll(voList);
        }
        return list;
    }

}
