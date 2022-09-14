package com.qingtian.lcpes.modules.authpre.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.util.StringUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.authpre.entity.AcOrgnizationEntity;
import com.qingtian.lcpes.modules.authpre.service.AcOrgnizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织机构
 */
@RestController
@RequestMapping("/auth/sadp-ac-orgnization")
@Slf4j
public class AcOrgnizationController {
    @Autowired
    private AcOrgnizationService acOrgnizationService;

    /**
     * 添加
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Boolean> add(@RequestBody JsonRequest<AcOrgnizationEntity> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            AcOrgnizationEntity entity = jsonRequest.getReqBody();
            Boolean result = acOrgnizationService.save(entity);
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error("添加异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("添加失败");
        }
        return jsonResponse;
    }

    /**
     * 通过id删除
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/removeById", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Boolean> removeById(@RequestBody JsonRequest<Serializable> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            Serializable id = jsonRequest.getReqBody();
            Boolean result = acOrgnizationService.removeById(id);
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error("删除异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("删除失败");
        }
        return jsonResponse;
    }

    /**
     * 通过ids 批量删除
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/removeByIds", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Boolean> removeByIds(@RequestBody JsonRequest<List<Serializable>> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            List<Serializable> ids = jsonRequest.getReqBody();
            Boolean result = acOrgnizationService.removeByIds(ids);
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error("批量删除异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("批量删除失败");
        }
        return jsonResponse;
    }

    /**
     * 修改
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Boolean> updateById(@RequestBody JsonRequest<AcOrgnizationEntity> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            AcOrgnizationEntity entity = jsonRequest.getReqBody();
            Boolean result = acOrgnizationService.updateById(entity);
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error("修改异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("修改失败");
        }
        return jsonResponse;
    }

    /**
     * 修改作业部标识
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/updateOrgFlag", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Boolean> updateOrgFlag(@RequestBody JsonRequest<AcOrgnizationEntity> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            AcOrgnizationEntity entity = jsonRequest.getReqBody();
            Boolean result = acOrgnizationService.updateOrgFlag(entity);
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error("修改异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("修改失败");
        }
        return jsonResponse;
    }

    /**
     * 手动同步
     *
     * @return
     */
    @RequestMapping(value = "/doSynOrg", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Boolean> doSynOrg() {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();

        try {
            Boolean result = acOrgnizationService.doSynOrg();
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error("同步异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("同步异常");
        }
        return jsonResponse;
    }

    /**
     * 手动同步时间
     *
     * @return
     */
    @RequestMapping(value = "/getSynDate", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<LocalDateTime> getSynDate() {
        JsonResponse<LocalDateTime> jsonResponse = new JsonResponse<>();

        try {
            LocalDateTime result = acOrgnizationService.getSynDate();
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error("获取同步时间异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("获取同步时间异常");
        }
        return jsonResponse;
    }

    /**
     * 通过id查询
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<AcOrgnizationEntity> getById(@RequestBody JsonRequest<Serializable> jsonRequest) {
        JsonResponse<AcOrgnizationEntity> jsonResponse = new JsonResponse<>();
        try {
            Serializable id = jsonRequest.getReqBody();
            AcOrgnizationEntity result = acOrgnizationService.getById(id);
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error("通过id查询异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("通过id查询异常");
        }
        return jsonResponse;
    }

    /**
     * 通过ids指查询
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/listByIds", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<AcOrgnizationEntity>> listByIds(@RequestBody JsonRequest<List<Serializable>> jsonRequest) {
        JsonResponse<List<AcOrgnizationEntity>> jsonResponse = new JsonResponse<>();
        try {
            List<Serializable> ids = jsonRequest.getReqBody();
            List<AcOrgnizationEntity> acOrgnizationEntities = acOrgnizationService.listByIds(ids);
            jsonResponse.setRspBody(acOrgnizationEntities);
        }
        catch (Exception e) {
            log.error("通过ids指查询异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("通过ids指查询失败");
        }
        return jsonResponse;
    }

    /**
     * 查询全部记录
     *
     * @return
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public JsonResponse<List<AcOrgnizationEntity>> listAll() {
        JsonResponse<List<AcOrgnizationEntity>> jsonResponse = new JsonResponse<>();
        try {
            List<AcOrgnizationEntity> list = acOrgnizationService.list();
            jsonResponse.setRspBody(list);
        }
        catch (Exception e) {
            log.error("查询全部记录异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("查询全部记录失败");
        }
        return jsonResponse;
    }

    /**
     * 查询组织机构树
     *
     * @return
     */
    @RequestMapping(value = "/findOrgTree")
    @ResponseBody
    public JsonResponse<List<AcOrgnizationEntity>> findOrgTree(@RequestBody JsonRequest<AcOrgnizationEntity> jsonRequest) {
        JsonResponse<List<AcOrgnizationEntity>> jsonResponse = new JsonResponse<>();
        try {
            AcOrgnizationEntity entity = jsonRequest.getReqBody();
            Map<String, Object> params = new HashMap<>();

            if (!StringUtils.isBlank(entity.getOrgFullName())) {
                params.put("orgFullName", entity.getOrgFullName());
            }
            else if (!StringUtils.isBlank(entity.getOrgId())) {
                params.put("orgId", entity.getOrgId());
            }
            else {
                if (entity.getParentSid() == null) {
                    //京唐属地化sid
                    params.put("parentSid", 28000);
                }
                else {
                    params.put("parentSid", entity.getParentSid());
                }
            }
            List<AcOrgnizationEntity> list = acOrgnizationService.findOrgTree(params);
            jsonResponse.setRspBody(list);
        }
        catch (Exception e) {
            log.error("查询记录异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("查询记录失败");
        }
        return jsonResponse;
    }


    /**
     * 自定义分页查询
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<PageDTO<AcOrgnizationEntity>> listByPage(@RequestBody JsonRequest<AcOrgnizationEntity> jsonRequest) {
        JsonResponse<PageDTO<AcOrgnizationEntity>> jsonResponse = new JsonResponse<>();

        try {
            AcOrgnizationEntity entity = jsonRequest.getReqBody();
            IPage<AcOrgnizationEntity> iPage = acOrgnizationService.listByPage(new Page<>(entity.getPageNum(), entity.getPageSize()), entity);

            List<AcOrgnizationEntity> orgList = BeanCopyUtils.copyList(iPage.getRecords(), AcOrgnizationEntity.class);
            PageDTO<AcOrgnizationEntity> pageDTO = new PageDTO(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
            pageDTO.setTotal(iPage.getTotal());
            pageDTO.setResultData(orgList);
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
