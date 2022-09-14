package com.qingtian.lcpes.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingtian.lcpes.base.facade.BaseFacadeImpl;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.sys.entity.SysLogEntity;
import com.qingtian.lcpes.modules.sys.service.SysLogService;
import com.qingtian.lcpes.modules.sys.vo.SysLogVO;
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
 * 系统日志表 前端控制器
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Controller
@RequestMapping("/sys-log")
@Slf4j
public class SysLogController extends BaseFacadeImpl<SysLogService, SysLogEntity, SysLogVO> {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 自定义分页查询
     *
     * @param jsonRequest
     * @return
     */
    @PostMapping(value = "/listByPage")
    @ResponseBody
    public JsonResponse<PageDTO<SysLogVO>> listByPage(@RequestBody JsonRequest<SysLogVO> jsonRequest) {
        JsonResponse<PageDTO<SysLogVO>> jsonResponse = new JsonResponse<>();
        try {
            SysLogVO vo = jsonRequest.getReqBody();
            SysLogEntity entity = BeanCopyUtils.copy(vo, super.entityClass);
            IPage<SysLogEntity> iPage = sysLogService.listByPage(new Page<SysLogEntity>(vo.getPageNum(), vo.getPageSize()), entity);

            List<SysLogVO> voList = BeanCopyUtils.copyList(iPage.getRecords(), SysLogVO.class);
            PageDTO<SysLogVO> pageDTO = new PageDTO<>(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
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
}
