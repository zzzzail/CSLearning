package com.qingtian.lcpes.modules.mq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingtian.lcpes.base.exception.QTBusinessException;
import com.qingtian.lcpes.base.facade.BaseFacadeImpl;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.mq.entity.MqWbqueLogEntity;
import com.qingtian.lcpes.modules.mq.service.MqWbqueLogService;
import com.qingtian.lcpes.modules.mq.vo.MqWbqueLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 运单消息队列日志表 前端控制器
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Controller
@RequestMapping("/dlx/mq-wbque-log")
@Slf4j
public class MqWbqueLogController extends BaseFacadeImpl<MqWbqueLogService, MqWbqueLogEntity, MqWbqueLogVO> {
    @Autowired
    private MqWbqueLogService mqWbqueLogService;

    /**
     * 自定义分页查询
     *
     * @param jsonRequest
     * @return
     */
    @PostMapping(value = "/listByPage")
    @ResponseBody
    public JsonResponse<PageDTO<MqWbqueLogVO>> listByPage(@RequestBody JsonRequest<MqWbqueLogVO> jsonRequest) {
        JsonResponse<PageDTO<MqWbqueLogVO>> jsonResponse = new JsonResponse<>();
        try {
            MqWbqueLogVO vo = jsonRequest.getReqBody();
            MqWbqueLogEntity entity = BeanCopyUtils.copy(vo, super.entityClass);
            IPage<MqWbqueLogEntity> iPage = mqWbqueLogService.listByPage(new Page<MqWbqueLogEntity>(vo.getPageNum(), vo.getPageSize()), entity);

            List<MqWbqueLogVO> voList = BeanCopyUtils.copyList(iPage.getRecords(), MqWbqueLogVO.class);
            PageDTO<MqWbqueLogVO> pageDTO = new PageDTO<>(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
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

    @PostMapping("/getByMessageId")
    @ResponseBody
    public JsonResponse<MqWbqueLogVO> getByMessageId(@RequestBody JsonRequest<String> jsonRequest) {
        JsonResponse<MqWbqueLogVO> serviceResponse = new JsonResponse<>();
        try {
            String messageId = jsonRequest.getReqBody();
            MqWbqueLogEntity entity = mqWbqueLogService.getByMessageId(messageId);
            MqWbqueLogVO vo = BeanCopyUtils.copy(entity, this.voClass);
            serviceResponse.setRspBody(vo);
        }
        catch (QTBusinessException qtbe) {
            log.error("业务异常", qtbe);
            serviceResponse.setRetCode(qtbe.getCode());
            serviceResponse.setRetDesc(qtbe.getMessage());
        }
        catch (Exception e) {
            log.error("查询失败", e);
            serviceResponse.setRetCode("500");
            serviceResponse.setRetDesc("查询失败");
        }
        return serviceResponse;
    }

    @PostMapping("/updateById")
    @ResponseBody
    @Override
    public JsonResponse<Boolean> updateById(@RequestBody JsonRequest<MqWbqueLogVO> jsonRequest) {
        JsonResponse<Boolean> serviceResponse = new JsonResponse();
        try {
            MqWbqueLogVO vo = jsonRequest.getReqBody();
            MqWbqueLogEntity entity = mqWbqueLogService.getById(vo.getSid());
            if (entity == null) {
                serviceResponse.setRetCode("500");
                serviceResponse.setRetDesc("数据不存在！sid=" + vo.getSid());
                return serviceResponse;
            }

            String oldMsg = entity.getMsg();
            String newMsg = vo.getMsg();
            BeanCopyUtils.copy(vo, entity);
            if (oldMsg != null && !oldMsg.equals(newMsg)) {
                // rehash 操作，更新 messageId
                String newMessageId = mqWbqueLogService.jsonDataHash(entity.getMsg());
                entity.setMessageId(newMessageId);
            }
            // 尝试更新 messageTypeId
            if (StringUtils.isEmpty(entity.getMsgTypeId())) {
                String messageTypeId = mqWbqueLogService.parseMessageTypeId(entity.getMsg());
                entity.setMsgTypeId(messageTypeId);
            }
            Boolean result = mqWbqueLogService.updateById(entity);
            serviceResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error("错误", e);
            serviceResponse.setRetCode("500");
            serviceResponse.setRetDesc("修改失败");
        }
        return serviceResponse;
    }

}
