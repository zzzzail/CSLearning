package com.qingtian.lcpes.modules.mq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.util.StringUtil;
import com.qingtian.lcpes.base.facade.BaseFacadeImpl;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.mq.bo.MsgStatCountBO;
import com.qingtian.lcpes.modules.mq.entity.MqMsgHEntity;
import com.qingtian.lcpes.modules.mq.service.MqMsgHService;
import com.qingtian.lcpes.modules.mq.vo.MqMsgHVO;
import com.qingtian.lcpes.modules.mq.vo.MsgStatCountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 消息历史表 前端控制器
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Controller
@RequestMapping("/mq-msg-h")
@Slf4j
public class MqMsgHController extends BaseFacadeImpl<MqMsgHService, MqMsgHEntity, MqMsgHVO> {
    @Autowired
    private MqMsgHService mqMsgHService;

    /**
     * 自定义分页查询
     *
     * @param jsonRequest
     * @return
     */
    @PostMapping(value = "/listByPage")
    @ResponseBody
    public JsonResponse<PageDTO<MqMsgHVO>> listByPage(@RequestBody JsonRequest<MqMsgHVO> jsonRequest) {
        JsonResponse<PageDTO<MqMsgHVO>> jsonResponse = new JsonResponse<>();
        try {
            MqMsgHVO vo = jsonRequest.getReqBody();
            MqMsgHEntity entity = BeanCopyUtils.copy(vo, super.entityClass);
            IPage<MqMsgHEntity> iPage = mqMsgHService.listByPage(new Page<MqMsgHEntity>(vo.getPageNum(), vo.getPageSize()), entity);

            List<MqMsgHVO> voList = BeanCopyUtils.copyList(iPage.getRecords(), MqMsgHVO.class);
            PageDTO<MqMsgHVO> pageDTO = new PageDTO<>(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
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
            List<MsgStatCountVO> res = mqMsgHService.countByBO(bo);
            response.setRspBody(res);
        }
        catch (Exception ex){
            log.error("统计失败", ex);
            response.setRetCode("500");
            response.setRetDesc("统计失败");
        }
        return response;
    }
}
