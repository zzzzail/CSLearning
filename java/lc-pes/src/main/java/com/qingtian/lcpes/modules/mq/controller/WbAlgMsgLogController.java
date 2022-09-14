package com.qingtian.lcpes.modules.mq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingtian.lcpes.base.facade.BaseFacadeImpl;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.mq.entity.WbAlgMsgLogEntity;
import com.qingtian.lcpes.modules.mq.service.WbAlgMsgLogService;
import com.qingtian.lcpes.modules.mq.vo.WbAlgMsgLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 算法接口日志表 前端控制器
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Controller
@RequestMapping("/wb-alg-msg-log")
@Slf4j
public class WbAlgMsgLogController extends BaseFacadeImpl<WbAlgMsgLogService, WbAlgMsgLogEntity, WbAlgMsgLogVO> {
    @Autowired
    private WbAlgMsgLogService wbAlgMsgLogService;

    /**
     * 自定义分页查询
     *
     * @param jsonRequest
     * @return
     */
    @PostMapping(value = "/listByPage")
    @ResponseBody
    public JsonResponse<PageDTO<WbAlgMsgLogVO>> listByPage(@RequestBody JsonRequest<WbAlgMsgLogVO> jsonRequest) {
        JsonResponse<PageDTO<WbAlgMsgLogVO>> jsonResponse = new JsonResponse<>();
        try {
            WbAlgMsgLogVO vo = jsonRequest.getReqBody();
            WbAlgMsgLogEntity entity = BeanCopyUtils.copy(vo, super.entityClass);
            IPage<WbAlgMsgLogEntity> iPage = wbAlgMsgLogService.listByPage(new Page<WbAlgMsgLogEntity>(vo.getPageNum(), vo.getPageSize()), entity);

            List<WbAlgMsgLogVO> voList = BeanCopyUtils.copyList(iPage.getRecords(), WbAlgMsgLogVO.class);
            PageDTO<WbAlgMsgLogVO> pageDTO = new PageDTO<>(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
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
     *
     * @param jsonRequest
     * @return
     */
    @PostMapping(value = "/redo")
    @ResponseBody
    public JsonResponse<Boolean> redo(@RequestBody JsonRequest<WbAlgMsgLogVO> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            WbAlgMsgLogVO vo = jsonRequest.getReqBody();
            if(vo.getSid() == null){
                jsonResponse.setRetCode("500");
                return jsonResponse;
            }
            WbAlgMsgLogEntity entity = wbAlgMsgLogService.getById(vo.getSid());
            if(entity == null){
                jsonResponse.setRetCode("500");
                return jsonResponse;
            }
            Boolean res = false;
            jsonResponse.setRspBody(res);
        } catch (Exception e) {
            log.error("操作失败", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc(e.getMessage());
        }
        return jsonResponse;
    }

    /**
     * 自定义分页查询
     * @param jsonRequest
     * @return
     */
    @PostMapping(value = "/pageByParams")
    @ResponseBody
    public JsonResponse<PageDTO<WbAlgMsgLogVO>> pageByParams(@RequestBody JsonRequest<Map<String,Object>> jsonRequest) {
        JsonResponse<PageDTO<WbAlgMsgLogVO>> jsonResponse = new JsonResponse<>();
        try {
            Map<String, Object> params = jsonRequest.getReqBody();
            IPage<WbAlgMsgLogVO> iPage = wbAlgMsgLogService.pageByParams(params);
            PageDTO<WbAlgMsgLogVO> pageDTO = new PageDTO<>(
                    Long.valueOf(iPage.getCurrent()).intValue(),
                    Long.valueOf(iPage.getSize()).intValue());
            pageDTO.setTotal(iPage.getTotal());
            pageDTO.setResultData(iPage.getRecords());
            jsonResponse.setRspBody(pageDTO);
        } catch (Exception e) {
            log.error("查询失败", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc(e.getMessage());
        }
        return jsonResponse;
    }
}
