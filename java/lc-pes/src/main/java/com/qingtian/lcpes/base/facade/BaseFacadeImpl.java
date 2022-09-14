package com.qingtian.lcpes.base.facade;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.page.PageDTOUtils;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class BaseFacadeImpl<S extends BaseService<T>, T, V> {
    protected Log log = LogFactory.getLog(this.getClass());
    protected Class<T> entityClass = this.currentEntityClass();
    protected Class<V> voClass = this.currentVoClass();
    @Autowired
    protected S baseService;

    public S getBaseService() {
        return this.baseService;
    }

    protected Class<T> currentEntityClass() {
        return (Class) this.getResolvableType().as(BaseFacadeImpl.class).getGeneric(1).getType();
    }

    protected Class<V> currentVoClass() {
        return (Class) this.getResolvableType().as(BaseFacadeImpl.class).getGeneric(2).getType();
    }

    protected ResolvableType getResolvableType() {
        return ResolvableType.forClass(ClassUtils.getUserClass(this.getClass()));
    }

    public Class createVo() {
        try {
            ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class clazz = (Class) ptype.getActualTypeArguments()[2];
            V o = (V) clazz.newInstance();
            return o.getClass();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    @ResponseBody
    public JsonResponse<Boolean> add(@RequestBody JsonRequest<V> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            V vo = jsonRequest.getReqBody();
            T entity = BeanCopyUtils.copy(vo, this.entityClass);
            Boolean result = this.baseService.save(entity);
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error(e);
            jsonResponse.setRspBody(false);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("添加失败");
        }

        return jsonResponse;
    }

    @RequestMapping(value = {"/addByBatch"}, method = {RequestMethod.POST})
    @ResponseBody
    public JsonResponse<Boolean> addByBatch(@RequestBody JsonRequest<List<V>> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();
        try {
            List<V> vos = jsonRequest.getReqBody();
            List<T> entities = BeanCopyUtils.copyList(vos, this.entityClass);
            Boolean result = this.baseService.saveBatch(entities);
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error(e);
            jsonResponse.setRspBody(false);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("添加失败");
        }

        return jsonResponse;
    }

    @RequestMapping(value = {"/removeById"}, method = {RequestMethod.POST})
    @ResponseBody
    public JsonResponse<Boolean> removeById(@RequestBody JsonRequest<Serializable> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<>();

        try {
            Serializable id = jsonRequest.getReqBody();
            Boolean result = this.baseService.removeById(id);
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error(e);
            jsonResponse.setRspBody(false);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("删除失败");
        }

        return jsonResponse;
    }

    @RequestMapping(value = {"/removeByIds"}, method = {RequestMethod.POST})
    @ResponseBody
    public JsonResponse<Boolean> removeByIds(@RequestBody JsonRequest<List<Serializable>> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse();

        try {
            List<Serializable> ids = jsonRequest.getReqBody();
            Boolean result = this.baseService.removeByIds(ids);
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error(e);
            jsonResponse.setRspBody(false);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("删除失败");
        }

        return jsonResponse;
    }

    @RequestMapping(value = {"/updateById"}, method = {RequestMethod.POST})
    @ResponseBody
    public JsonResponse<Boolean> updateById(@RequestBody JsonRequest<V> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse();

        try {
            V vo = jsonRequest.getReqBody();
            T entity = BeanCopyUtils.copy(vo, this.entityClass);
            Boolean result = this.baseService.updateById(entity);
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error(e);
            jsonResponse.setRspBody(false);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("修改失败");
        }

        return jsonResponse;
    }

    @RequestMapping(value = {"/updateByBatch"}, method = {RequestMethod.POST})
    @ResponseBody
    public JsonResponse<Boolean> updateByBatch(@RequestBody JsonRequest<List<V>> jsonRequest) {
        JsonResponse<Boolean> jsonResponse = new JsonResponse();

        try {
            List<V> vos = jsonRequest.getReqBody();
            List<T> entities = BeanCopyUtils.copyList(vos, this.entityClass);
            Boolean result = this.baseService.updateBatchById(entities);
            jsonResponse.setRspBody(result);
        }
        catch (Exception e) {
            log.error(e);
            jsonResponse.setRspBody(false);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("修改失败");
        }

        return jsonResponse;
    }

    @RequestMapping(value = {"/getById"}, method = {RequestMethod.POST})
    @ResponseBody
    public JsonResponse<V> getById(@RequestBody JsonRequest<Serializable> jsonRequest) {
        JsonResponse<V> jsonResponse = new JsonResponse();

        try {
            Serializable id = jsonRequest.getReqBody();
            T entity = this.baseService.getById(id);
            V vo = BeanCopyUtils.copy(entity, this.voClass);
            jsonResponse.setRspBody(vo);
        }
        catch (Exception e) {
            log.error(e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("查询失败");
        }

        return jsonResponse;
    }

    @RequestMapping(value = {"/listByIds"}, method = {RequestMethod.POST})
    @ResponseBody
    public JsonResponse<List<V>> listByIds(@RequestBody JsonRequest<List<Serializable>> jsonRequest) {
        JsonResponse<List<V>> jsonResponse = new JsonResponse<>();

        try {
            List<Serializable> idList = jsonRequest.getReqBody();
            List<T> list = this.baseService.listByIds(idList);
            List<V> voList = BeanCopyUtils.copyList(list, this.voClass);
            jsonResponse.setRspBody(voList);
        }
        catch (Exception e) {
            log.error(e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("查询失败");
        }

        return jsonResponse;
    }

    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @ResponseBody
    public JsonResponse<List<V>> list(@RequestBody JsonRequest<V> jsonRequest) {
        JsonResponse<List<V>> jsonResponse = new JsonResponse<>();

        try {
            V vo = jsonRequest.getReqBody();
            QueryWrapper<T> queryWrapper = new QueryWrapper<>();
            T entity = BeanCopyUtils.copy(vo, this.entityClass);
            QueryTool.initQueryWrapper(entity.getClass(), queryWrapper, vo);
            List<T> entityList = this.baseService.list(queryWrapper);
            List<V> voList = BeanCopyUtils.copyList(entityList, this.voClass);
            jsonResponse.setRspBody(voList);
        }
        catch (Exception e) {
            this.log.error(e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("查询失败");
        }

        return jsonResponse;
    }

    @RequestMapping({"/listAll"})
    @ResponseBody
    public JsonResponse<List<V>> findAll() {
        JsonResponse<List<V>> jsonResponse = new JsonResponse<>();

        try {
            List<T> entityList = this.baseService.list(Wrappers.emptyWrapper());
            List<V> voList = BeanCopyUtils.copyList(entityList, this.voClass);
            jsonResponse.setRspBody(voList);
        }
        catch (Exception e) {
            this.log.error(e);
            jsonResponse.setRetCode("500");
            jsonResponse.setRetDesc("查询失败");
        }

        return jsonResponse;
    }

    @RequestMapping(value = {"/findByPage"}, method = {RequestMethod.POST})
    @ResponseBody
    public JsonResponse<PageDTO<V>> findByPage(@RequestBody JsonRequest<V> jsonRequest) {
        JsonResponse<PageDTO<V>> jsonResponse = new JsonResponse<>();

        try {
            V vo = jsonRequest.getReqBody();
            QueryWrapper<T> queryWrapper = new QueryWrapper<>();
            T entity = BeanCopyUtils.copy(vo, this.entityClass);
            QueryTool.initQueryWrapper(entity.getClass(), queryWrapper, vo);
            BaseVO baseVO = (BaseVO) vo;
            IPage<T> page = baseService.page(new Page<>(baseVO.getPageNum(), baseVO.getPageSize()), queryWrapper);
            List<T> entityList = page.getRecords();
            List<V> voList = BeanCopyUtils.copyList(entityList, this.voClass);
            PageDTO<V> pages = new PageDTO<>(Long.valueOf(page.getCurrent()).intValue(), Long.valueOf(page.getSize()).intValue());
            pages.setTotal(page.getTotal());
            pages.setResultData(voList);
            jsonResponse.setRspBody(pages);
        }
        catch (Exception e) {
            log.error(e);
            jsonResponse.setRetCode("500");
        }
        finally {
            PageDTOUtils.endPage();
        }

        return jsonResponse;
    }
}
