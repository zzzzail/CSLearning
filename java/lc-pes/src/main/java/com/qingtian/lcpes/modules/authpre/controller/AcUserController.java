package com.qingtian.lcpes.modules.authpre.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.authpre.entity.AcUserEntity;
import com.qingtian.lcpes.modules.authpre.service.AcUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户
 */
@RestController
@RequestMapping("/auth/sadp-ac-user")
@Slf4j
public class AcUserController {
    @Autowired
    private AcUserService acUserService;

    /**
     * 添加
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Boolean> add(@RequestBody JsonRequest<AcUserEntity> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            AcUserEntity entity = jsonRequest.getReqBody();
            Boolean result = acUserService.save(entity);
            jsonResponse.setRspBody(result);
        } catch (Exception e) {
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
            Boolean result = acUserService.removeById(id);
            jsonResponse.setRspBody(result);
        } catch (Exception e) {
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
            Boolean result = acUserService.removeByIds(ids);
            jsonResponse.setRspBody(result);
        } catch (Exception e) {
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
    public JsonResponse<Boolean> updateById(@RequestBody JsonRequest<AcUserEntity> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            AcUserEntity entity = jsonRequest.getReqBody();
            Boolean result = acUserService.updateById(entity);
            jsonResponse.setRspBody(result);
        } catch (Exception e) {
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
    public JsonResponse<AcUserEntity> getById(@RequestBody JsonRequest<Serializable> jsonRequest) {
        JsonResponse<AcUserEntity> jsonResponse = new JsonResponse<>();
        try {
            Serializable id = jsonRequest.getReqBody();
            AcUserEntity result = acUserService.getById(id);
            jsonResponse.setRspBody(result);
        } catch (Exception e) {
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
    public JsonResponse<List<AcUserEntity>> listByIds(@RequestBody JsonRequest<List<Serializable>> jsonRequest) {
        JsonResponse<List<AcUserEntity>> jsonResponse = new JsonResponse<>();
        try {
            List<Serializable> ids = jsonRequest.getReqBody();
            List<AcUserEntity> entitys = acUserService.listByIds(ids);
            jsonResponse.setRspBody(entitys);
        } catch (Exception e) {
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
    public JsonResponse<List<AcUserEntity>> listAll() {
        JsonResponse<List<AcUserEntity>> jsonResponse = new JsonResponse<>();
        try {
            List<AcUserEntity> list = acUserService.list();
            jsonResponse.setRspBody(list);
        } catch (Exception e) {
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
    public JsonResponse<PageDTO<AcUserEntity>> listByPage(@RequestBody JsonRequest<AcUserEntity> jsonRequest) {
        JsonResponse<PageDTO<AcUserEntity>> jsonResponse = new JsonResponse<>();

        try {
            AcUserEntity entity = jsonRequest.getReqBody();
            //测试数据权限
            // entity.setTestBizWarehouseSids(LoginUtil.getBizWarehouseSidsRedis(null));
            //log.info("测试数据权限:" + LoginUtil.getBizWarehouseSidsRedis(null));

            IPage<AcUserEntity> iPage = acUserService.listByPage(new Page<>(entity.getPageNum(), entity.getPageSize()), entity);

            List<AcUserEntity> userList = BeanCopyUtils.copyList(iPage.getRecords(), AcUserEntity.class);
            PageDTO<AcUserEntity> pageDTO = new PageDTO(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
            pageDTO.setTotal(iPage.getTotal());
            pageDTO.setResultData(userList);
            jsonResponse.setRspBody(pageDTO);
        } catch (Exception e) {
            log.error("查询失败", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("查询失败");
        }
        return jsonResponse;
    }


    /**
     * 手动同步
     * @return
     */
    @RequestMapping(value = "/doSynUser", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Boolean> doSynUser() {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();

        try {
            Boolean result = acUserService.doSynUser();
            jsonResponse.setRspBody(result);
        } catch (Exception e) {
            log.error("同步异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("同步异常");
        }
        return jsonResponse;
    }

    /**
     * 手动同步时间
     * @return
     */
    @RequestMapping(value = "/getSynDate", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<LocalDateTime> getSynDate() {
        JsonResponse<LocalDateTime> jsonResponse = new JsonResponse<>();

        try {
            LocalDateTime result = acUserService.getSynDate();
            jsonResponse.setRspBody(result);
        } catch (Exception e) {
            log.error("获取同步时间异常", e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("获取同步时间异常");
        }
        return jsonResponse;
    }
}
