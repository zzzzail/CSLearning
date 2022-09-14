package com.qingtian.lcpes.modules.mq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingtian.lcpes.base.facade.BaseFacadeImpl;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.mq.entity.MqConfigEntity;
import com.qingtian.lcpes.modules.mq.service.MqConfigService;
import com.qingtian.lcpes.modules.mq.vo.MqConfigVO;
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
 * 消息配置表 前端控制器
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Controller
@RequestMapping("/mq-config")
@Slf4j
public class MqConfigController extends BaseFacadeImpl<MqConfigService, MqConfigEntity, MqConfigVO> {
    @Autowired
    private MqConfigService mqConfigService;

    /**
     * 自定义分页查询
     *
     * @param jsonRequest
     * @return
     */
    @PostMapping(value = "/listByPage")
    @ResponseBody
    public JsonResponse<PageDTO<MqConfigVO>> listByPage(@RequestBody JsonRequest<MqConfigVO> jsonRequest) {
        JsonResponse<PageDTO<MqConfigVO>> jsonResponse = new JsonResponse<>();
        try {
            MqConfigVO vo = jsonRequest.getReqBody();
            MqConfigEntity entity = BeanCopyUtils.copy(vo, super.entityClass);
            IPage<MqConfigEntity> iPage = mqConfigService.listByPage(new Page<MqConfigEntity>(vo.getPageNum(), vo.getPageSize()), entity);

            List<MqConfigVO> voList = BeanCopyUtils.copyList(iPage.getRecords(), MqConfigVO.class);
            PageDTO<MqConfigVO> pageDTO = new PageDTO<>(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
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

    @PostMapping("/getConfigByMsgId")
    @ResponseBody
    public JsonResponse<MqConfigVO> getConfigByMsgId(@RequestBody JsonRequest<MqConfigVO> jsonRequest) {
        JsonResponse<MqConfigVO> serviceResponse = new JsonResponse<>();
        try {
            MqConfigVO vo = jsonRequest.getReqBody();
            MqConfigEntity config = mqConfigService.getConfigByMsgId(vo.getMsgId());
            BeanCopyUtils.copy(config, vo);
            serviceResponse.setRspBody(vo);
        } catch (Exception e) {
            log.error("查询失败", e);
            serviceResponse.setRetCode("500");
            serviceResponse.setRetDesc("查询失败");
        }
        return serviceResponse;
    }
}
