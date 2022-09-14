package com.qingtian.lcpes.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingtian.lcpes.base.exception.QTBusinessException;
import com.qingtian.lcpes.base.facade.BaseFacadeImpl;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.sys.entity.CommonNoticeEntity;
import com.qingtian.lcpes.modules.sys.service.CommonNoticeService;
import com.qingtian.lcpes.modules.sys.vo.CommonNoticeVO;
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
 * 公告管理表 前端控制器
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Controller
@RequestMapping("/common-notice")
@Slf4j
public class CommonNoticeController extends BaseFacadeImpl<CommonNoticeService, CommonNoticeEntity, CommonNoticeVO> {
    @Autowired
    private CommonNoticeService commonNoticeService;

    @PostMapping(value = "/listByPage")
    @ResponseBody
    public JsonResponse<PageDTO<CommonNoticeVO>> listByPage(@RequestBody JsonRequest<CommonNoticeVO> jsonRequest) {
        JsonResponse<PageDTO<CommonNoticeVO>> serviceResponse = new JsonResponse<>();
        try {
            CommonNoticeVO vo = jsonRequest.getReqBody();
            // CommonNoticeEntity entity = BeanCopyUtil.copy(vo, super.entityClass);
            LocalDateTime specifyTime = vo.getSpecifyTime();
            Integer specifyRule = vo.getSpecifyRule();
            if (specifyTime != null && specifyRule != null) {
                if (specifyRule == 1) {
                    // 之前则设置结束时间
                    vo.setEndTime(specifyTime);
                }
                else if (specifyRule == 2) {
                    // 之后则设置开始时间
                    vo.setStartTime(specifyTime);
                }
            }

            CommonNoticeEntity entity = BeanCopyUtils.copy(vo, CommonNoticeEntity.class);
            IPage<CommonNoticeEntity> iPage = commonNoticeService.listByPage(new Page<>(vo.getPageNum(), vo.getPageSize()), entity);

            List<CommonNoticeVO> voList = BeanCopyUtils.copyList(iPage.getRecords(), CommonNoticeVO.class);
            PageDTO<CommonNoticeVO> pageDTO = new PageDTO<>(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
            pageDTO.setTotal(iPage.getTotal());
            pageDTO.setResultData(voList);

            serviceResponse.setRspBody(pageDTO);
        }
        catch (Exception e) {
            log.error("查询失败", e);
            serviceResponse.setRetCode("500");
            serviceResponse.setRetDesc("查询失败");
        }
        return serviceResponse;
    }

    /**
     * 批量更改多个状态
     *
     * @return
     */
    @PostMapping("/updateBathReadFlag")
    @ResponseBody
    public JsonResponse<Boolean> updateBathReadFlag(@RequestBody JsonRequest<CommonNoticeVO> jsonRequest) {
        JsonResponse<Boolean> serviceResponse = new JsonResponse<>();
        try {
            CommonNoticeVO vo = jsonRequest.getReqBody();
            CommonNoticeEntity entity = BeanCopyUtils.copy(vo, CommonNoticeEntity.class);
            boolean updateBatchById = commonNoticeService.updateBathReadFlag(entity);
            serviceResponse.setRspBody(updateBatchById);
        }
        catch (QTBusinessException qtbe) {
            log.error("业务异常", qtbe);
            serviceResponse.setRetCode(qtbe.getCode());
            serviceResponse.setRetDesc(qtbe.getMessage());
        }
        catch (Exception e) {
            log.error("状态更新失败", e);
            serviceResponse.setRetCode("500");
            serviceResponse.setRetDesc("状态更新失败");
        }

        return serviceResponse;
    }
}
