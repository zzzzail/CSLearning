package com.qingtian.lcpes.modules.authpre.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.authpre.entity.AcGroupEntity;
import com.qingtian.lcpes.modules.authpre.service.AcGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 岗位
 */
@RestController
@RequestMapping("/auth/sadp_ac_group")
@Slf4j
public class AcGroupController {
    @Autowired
    private AcGroupService acGroupService;

    /**
     * 添加
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Boolean> add(@RequestBody JsonRequest<AcGroupEntity> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            //获取需求提报数据权限，值对应数据字典
            //String tasktypes = LoginUtil.getBizTaskTypesRedis("admin");
            AcGroupEntity entity = jsonRequest.getReqBody();
            Boolean result = acGroupService.save(entity);
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
            Boolean result = acGroupService.removeById(id);
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
            Boolean result = acGroupService.removeByIds(ids);
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
    public JsonResponse<Boolean> updateById(@RequestBody JsonRequest<AcGroupEntity> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            AcGroupEntity entity = jsonRequest.getReqBody();
            Boolean result = acGroupService.updateById(entity);
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
     * 通过id查询
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<AcGroupEntity> getById(@RequestBody JsonRequest<Serializable> jsonRequest) {
        JsonResponse<AcGroupEntity> jsonResponse = new JsonResponse<>();
        try {
            Serializable id = jsonRequest.getReqBody();
            AcGroupEntity result = acGroupService.getById(id);
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
    public JsonResponse<List<AcGroupEntity>> listByIds(@RequestBody JsonRequest<List<Serializable>> jsonRequest) {
        JsonResponse<List<AcGroupEntity>> jsonResponse = new JsonResponse<>();
        try {
            List<Serializable> ids = jsonRequest.getReqBody();
            List<AcGroupEntity> entitys = acGroupService.listByIds(ids);
            jsonResponse.setRspBody(entitys);
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
    public JsonResponse<List<AcGroupEntity>> listAll() {
        JsonResponse<List<AcGroupEntity>> jsonResponse = new JsonResponse<>();
        try {
            List<AcGroupEntity> list = acGroupService.list();
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
     * 自定义分页查询
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<PageDTO<AcGroupEntity>> listByPage(@RequestBody JsonRequest<AcGroupEntity> jsonRequest) {
        JsonResponse<PageDTO<AcGroupEntity>> jsonResponse = new JsonResponse<>();

        try {
            AcGroupEntity entity = jsonRequest.getReqBody();
            IPage<AcGroupEntity> iPage = acGroupService.listByPage(new Page<>(entity.getPageNum(), entity.getPageSize()), entity);

            List<AcGroupEntity> groupList = BeanCopyUtils.copyList(iPage.getRecords(), AcGroupEntity.class);
            PageDTO<AcGroupEntity> pageDTO = new PageDTO(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
            pageDTO.setTotal(iPage.getTotal());
            pageDTO.setResultData(groupList);
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
