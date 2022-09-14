package com.qingtian.lcpes.modules.mq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.util.StringUtil;
import com.google.common.base.CaseFormat;
import com.qingtian.lcpes.base.facade.BaseFacadeImpl;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.mq.bo.MsgStatCountBO;
import com.qingtian.lcpes.modules.mq.entity.MqMsgEntity;
import com.qingtian.lcpes.modules.mq.jmsg.NodeInfo;
import com.qingtian.lcpes.modules.mq.service.MqMsgService;
import com.qingtian.lcpes.modules.mq.util.XmlUtil;
import com.qingtian.lcpes.modules.mq.vo.MqMsgVO;
import com.qingtian.lcpes.modules.mq.vo.MsgStatCountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 消息表 前端控制器
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Controller
@RequestMapping("/mq-msg")
@Slf4j
public class MqMsgController extends BaseFacadeImpl<MqMsgService, MqMsgEntity, MqMsgVO> {
    @Autowired
    private MqMsgService mqMsgService;

    @PostMapping(value = "/listByPage")
    @ResponseBody
    public JsonResponse<PageDTO<MqMsgVO>> listByPage(@RequestBody JsonRequest<MqMsgVO> jsonRequest) {
        JsonResponse<PageDTO<MqMsgVO>> serviceResponse = new JsonResponse<>();
        try {
            MqMsgVO vo = jsonRequest.getReqBody();
            MqMsgEntity entity = BeanCopyUtils.copy(vo, super.entityClass);

            Page<MqMsgEntity> page = new Page<>(vo.getPageNum(), vo.getPageSize());
            if (!StringUtils.isEmpty(vo.getColumnName())) {
                OrderItem orderItem = new OrderItem();
                String col = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, vo.getColumnName());
                orderItem.setColumn(col);
                orderItem.setAsc(vo.getIsAsc());
                page.addOrder(orderItem);
            }
            else {
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn("SID");
                orderItem.setAsc(false);
                page.addOrder(orderItem);
            }

            IPage<MqMsgEntity> iPage = baseService.listByPage(page, entity);

            List<MqMsgVO> voList = BeanCopyUtils.copyList(iPage.getRecords(), MqMsgVO.class);
            PageDTO<MqMsgVO> pageDTO = new PageDTO<>(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
            pageDTO.setTotal(iPage.getTotal());
            pageDTO.setResultData(voList);

            serviceResponse.setRspBody(pageDTO);
        }
        catch (Exception e) {
            log.error("消息查询失败", e);
            serviceResponse.setRetCode("500");
            serviceResponse.setRetDesc("消息查询失败");
        }
        return serviceResponse;
    }

    @PostMapping(path = "/xml2json")
    @ResponseBody
    public JsonResponse<String> xml2json(@RequestBody JsonRequest<String> jsonRequest) {
        JsonResponse<String> serviceResponse = new JsonResponse<>();
        try {
            String xmlStr = jsonRequest.getReqBody();
            String jsonStr = XmlUtil.xml2Node(xmlStr);
            serviceResponse.setRspBody(jsonStr);
        }
        catch (Exception ex) {
            log.error("xml2json失败", ex);
            serviceResponse.setRetCode("500");
            serviceResponse.setRetDesc(ex.getMessage());
        }
        return serviceResponse;
    }

    @PostMapping(path = "/json2xml")
    @ResponseBody
    public JsonResponse<String> json2xml(@RequestBody JsonRequest<String> jsonRequest) {
        JsonResponse<String> serviceResponse = new JsonResponse<>();
        try {
            String jsonStr = jsonRequest.getReqBody();
            String xmlStr = XmlUtil.xml2Node(jsonStr);
            serviceResponse.setRspBody(xmlStr);
        }
        catch (Exception ex) {
            log.error("json2xml失败", ex);
            serviceResponse.setRetCode("500");
            serviceResponse.setRetDesc(ex.getMessage());
        }
        return serviceResponse;
    }

    /**
     * 消息接收，落库 + 消息处理
     */
    @PostMapping(path = "/zkReceive")
    @ResponseBody
    public JsonResponse<Object> zkRecieve(@RequestBody JsonRequest<String> jsonRequest) {
        JsonResponse<Object> serviceResponse = new JsonResponse<>();
        try {
            String xmlStr = jsonRequest.getReqBody();
            String jsonStr = XmlUtil.xml2Node(xmlStr);
            MqMsgEntity msgEntity = mqMsgService.doRecieveMsg(jsonStr);
            if (msgEntity == null) {
                serviceResponse.setRetCode("501");
                serviceResponse.setRetDesc("消息接收失败");
            }
            else {
                MqMsgVO msgVO = BeanCopyUtils.copy(msgEntity, MqMsgVO.class);
                serviceResponse.setRspBody(msgVO);
            }
        }
        catch (Exception ex) {
            log.error("消息接收失败", ex);
            serviceResponse.setRetCode("500");
            serviceResponse.setRetDesc(ex.getMessage());
        }
        return serviceResponse;
    }

    /**
     * 消息接收，落库 + 消息处理
     */
    @PostMapping(path = "/msgReceive")
    @ResponseBody
    public JsonResponse<Object> msgRecieve(@RequestBody JsonRequest<String> jsonRequest) {
        JsonResponse<Object> serviceResponse = new JsonResponse<>();
        try {
            String jsonStr = jsonRequest.getReqBody();
            MqMsgEntity msgEntity = mqMsgService.doRecieveMsg(jsonStr);
            if (msgEntity == null) {
                serviceResponse.setRetCode("501");
                serviceResponse.setRetDesc("消息接收失败");
            }
            else {
                MqMsgVO msgVO = BeanCopyUtils.copy(msgEntity, MqMsgVO.class);
                serviceResponse.setRspBody(msgVO);
            }
        }
        catch (Exception ex) {
            log.error("消息接收失败", ex);
            serviceResponse.setRetCode("500");
            serviceResponse.setRetDesc("消息接收失败");
        }
        return serviceResponse;
    }

    /**
     * 消息发送， 落库 +  外发
     */
    @PostMapping("/msgSend")
    @ResponseBody
    public JsonResponse<String> msgSend(@RequestBody JsonRequest<NodeInfo> jsonRequest) {
        JsonResponse<String> response = new JsonResponse<>();
        try {
            NodeInfo nodeInfo = jsonRequest.getReqBody();
            MqMsgEntity msgEntity = mqMsgService.doSendMsg(nodeInfo);
            if (msgEntity == null) {
                response.setRetCode("501");
                response.setRetDesc("消息发送失败");
            }
            else {
                response.setRspBody(msgEntity.getMessageId());
            }
        }
        catch (Exception ex) {
            log.error("消息发送失败", ex);
            response.setRetCode("500");
            response.setRetDesc("消息发送失败");
        }
        return response;
    }

    @PostMapping("/msgSendRecv")
    @ResponseBody
    public JsonResponse<MqMsgVO> msgSendRecv(@RequestBody JsonRequest<String> jsonRequest) {
        JsonResponse<MqMsgVO> response = new JsonResponse<>();
        try {
            String jsonStr = jsonRequest.getReqBody();
            MqMsgEntity msgEntity = mqMsgService.manualSendRecvMsg(jsonStr);
            if (msgEntity == null) {
                response.setRetCode("501");
                response.setRetDesc("手动消息处理失败");
            }
            else {
                MqMsgVO msgVO = BeanCopyUtils.copy(msgEntity, MqMsgVO.class);
                response.setRspBody(msgVO);
            }
        }
        catch (Exception ex) {
            log.error("手动消息处理失败", ex);
            response.setRetCode("500");
            response.setRetDesc("手动消息处理失败");
        }
        return response;
    }

    /**
     * 消息处理，仅处理接收 或 外发逻辑，不包含落库，根据消息Id从db查询消息进行处理
     */
    @PostMapping("/msgProcess")
    @ResponseBody
    public JsonResponse<Boolean> msgProcess(@RequestBody JsonRequest<List<Serializable>> jsonRequest) {
        JsonResponse<Boolean> response = new JsonResponse<>();
        try {
            List<Serializable> msgIds = jsonRequest.getReqBody();
            msgIds.forEach(x -> {
                MqMsgEntity msgEntity = mqMsgService.getById(x);
                mqMsgService.processMsg(msgEntity);
            });
            response.setRspBody(true);
        }
        catch (Exception ex) {
            log.error("消息处理失败", ex);
            response.setRetCode("500");
            response.setRetDesc("消息发送失败");
            response.setRspBody(false);
        }
        return response;
    }

    @PostMapping("/msgArchive")
    @ResponseBody
    public JsonResponse<String> msgArchive(@RequestBody JsonRequest<List<Serializable>> jsonRequest) {
        JsonResponse<String> response = new JsonResponse<>();
        try {
            List<Serializable> msgIds = jsonRequest.getReqBody();
            List<MqMsgEntity> entityList = mqMsgService.listByIds(msgIds);
            mqMsgService.archiveMsg(entityList);
        }
        catch (Exception ex) {
            log.error("消息归档失败", ex);
            response.setRetCode("500");
            response.setRetDesc("消息归档失败");
        }
        return response;
    }

    @PostMapping("/countByBO")
    @ResponseBody
    public JsonResponse<List<MsgStatCountVO>> countByBO(@RequestBody JsonRequest<MsgStatCountBO> jsonRequest) {
        JsonResponse<List<MsgStatCountVO>> response = new JsonResponse<>();
        try {
            MsgStatCountBO bo = jsonRequest.getReqBody();
            if (bo.getStartTime() == null || bo.getEndTime() == null) {
                response.setRetCode("500");
                response.setRetDesc("未输入开始时间或结束时间！");
                return response;
            }
            if (StringUtil.isEmpty(bo.getDateNumFormatPattern())) {
                bo.setDateNumFormatPattern(MsgStatCountBO.HOUR_DNF_PATTERN);
            }
            List<MsgStatCountVO> res = mqMsgService.countByBO(bo);
            response.setRspBody(res);
        }
        catch (Exception ex) {
            log.error("统计失败", ex);
            response.setRetCode("500");
            response.setRetDesc("统计失败");
        }
        return response;
    }

    @PostMapping("/countMsgAndMsgHByBO")
    @ResponseBody
    public JsonResponse<List<MsgStatCountVO>> countMsgAndMsgHByBO(@RequestBody JsonRequest<MsgStatCountBO> jsonRequest) {
        JsonResponse<List<MsgStatCountVO>> response = new JsonResponse<>();
        try {
            MsgStatCountBO bo = jsonRequest.getReqBody();
            if (bo.getStartTime() == null || bo.getEndTime() == null) {
                response.setRetCode("500");
                response.setRetDesc("未输入开始时间或结束时间！");
                return response;
            }
            if (StringUtil.isEmpty(bo.getDateNumFormatPattern())) {
                bo.setDateNumFormatPattern(MsgStatCountBO.HOUR_DNF_PATTERN);
            }
            List<MsgStatCountVO> res = mqMsgService.countMsgAndMsgHByBO(bo);
            response.setRspBody(res);
        }
        catch (Exception ex) {
            log.error("统计失败", ex);
            response.setRetCode("500");
            response.setRetDesc("统计失败");
        }
        return response;
    }

    @PostMapping("/countMsgNumByBO")
    @ResponseBody
    public JsonResponse<Long> countMsgNumByBO(@RequestBody JsonRequest<MsgStatCountBO> jsonRequest) {
        JsonResponse<Long> response = new JsonResponse<>();
        try {
            MsgStatCountBO bo = jsonRequest.getReqBody();
            if (bo.getStartTime() == null || bo.getEndTime() == null) {
                response.setRetCode("500");
                response.setRetDesc("未输入开始时间或结束时间！");
                return response;
            }

            Long res = mqMsgService.countMsgNumByBO(bo);
            response.setRspBody(res);
        }
        catch (Exception ex) {
            log.error("统计失败", ex);
            response.setRetCode("500");
            response.setRetDesc("统计失败");
        }
        return response;
    }

    @PostMapping("/countMsgNumInMsgAndMsgHByBO")
    @ResponseBody
    public JsonResponse<Long> countMsgNumInMsgAndMsgHByBO(@RequestBody JsonRequest<MsgStatCountBO> jsonRequest) {
        JsonResponse<Long> response = new JsonResponse<>();
        try {
            MsgStatCountBO bo = jsonRequest.getReqBody();
            if (bo.getStartTime() == null || bo.getEndTime() == null) {
                response.setRetCode("500");
                response.setRetDesc("未输入开始时间或结束时间！");
                return response;
            }

            Long res = mqMsgService.countMsgNumByBOWithMsgH(bo);
            response.setRspBody(res);
        }
        catch (Exception ex) {
            log.error("统计失败", ex);
            response.setRetCode("500");
            response.setRetDesc("统计失败");
        }
        return response;
    }
}
