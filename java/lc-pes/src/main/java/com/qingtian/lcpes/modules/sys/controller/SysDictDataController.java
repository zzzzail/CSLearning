package com.qingtian.lcpes.modules.sys.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingtian.lcpes.base.cache.redis.RedisKeys;
import com.qingtian.lcpes.base.exception.QTBusinessException;
import com.qingtian.lcpes.base.facade.BaseFacadeImpl;
import com.qingtian.lcpes.base.page.PageDTO;
import com.qingtian.lcpes.base.page.PageDTOUtils;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.util.RedisUtils;
import com.qingtian.lcpes.base.web.JsonRequest;
import com.qingtian.lcpes.base.web.JsonResponse;
import com.qingtian.lcpes.modules.sys.entity.SysDictDataEntity;
import com.qingtian.lcpes.modules.sys.service.SysDictDataService;
import com.qingtian.lcpes.modules.sys.vo.SysDictDataVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 数据字典表 前端控制器
 * </p>
 *
 * @author zhangxq
 * @since 2022-08-30
 */
@Controller
@RequestMapping("/sys-dict-data")
@Slf4j
public class SysDictDataController extends BaseFacadeImpl<SysDictDataService, SysDictDataEntity, SysDictDataVO> {

    @Autowired
    private SysDictDataService sysDictDataService;
    @Autowired
    private RedisUtils redisUtils;

    private static final AtomicInteger CACHE_USAGE_COUNTER = new AtomicInteger(0);

    /**
     * 自定义分页查询
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/listByPage")
    @ResponseBody
    public JsonResponse<PageDTO<SysDictDataVO>> listByPage(@RequestBody JsonRequest<SysDictDataVO> jsonRequest) {
        JsonResponse<PageDTO<SysDictDataVO>> jsonResponse = new JsonResponse<>();
        try {
            SysDictDataVO vo = jsonRequest.getReqBody();
            SysDictDataEntity entity = BeanCopyUtils.copy(vo, super.entityClass);
            IPage<SysDictDataEntity> iPage = sysDictDataService.listByPage(new Page<SysDictDataEntity>(vo.getPageNum(), vo.getPageSize()), entity);

            List<SysDictDataVO> voList = BeanCopyUtils.copyList(iPage.getRecords(), SysDictDataVO.class);
            PageDTO<SysDictDataVO> pageDTO = new PageDTO<>(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
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
     * 返回小代码map, 基于当前实际数据只有两层, 因此只处理了主子两层
     *
     * @return
     */
    @PostMapping(value = {"/findDictDataMap", "/findBaseDictDataMap"})
    @ResponseBody
    public JsonResponse<Map<String, Map<String, String>>> findDictDataMap() {
        JsonResponse<Map<String, Map<String, String>>> serviceResponse = new JsonResponse<>();
        try {
            Map<String, Map<String, String>> result = new HashMap<>();
            List<SysDictDataEntity> dataList = null;
            try {
                // 从 redis 中取
                if (CACHE_USAGE_COUNTER.get() < 10) {
                    CACHE_USAGE_COUNTER.addAndGet(1);
                    List<Object> jsonArr = redisUtils.getCacheList("findAll");
                    dataList = (List<SysDictDataEntity>) jsonArr.get(0);
                }
                else {
                    CACHE_USAGE_COUNTER.set(0);
                }
            }
            catch (Exception e) {
                redisUtils.del("findAll");
            }

            if (dataList == null || dataList.size() == 0) {
                dataList = sysDictDataService.list();
                redisUtils.lSet("findAll", dataList, 1000);
            }
            for (SysDictDataEntity dd : dataList) {
                if (dd.getParentSid() != null && dd.getParentSid().longValue() == 0L) {
                    Map<String, String> sub = new HashMap<>();
                    for (SysDictDataEntity data : dataList) {
                        if (data.getParentSid() != null && data.getParentSid().longValue() == dd.getTypeSid()) {
                            if (data.getTypeCode() != null && !data.getTypeCode().trim().equals("")) {
                                sub.put(data.getTypeCode(), data.getTypeName());
                            }
                        }
                    }
                    result.put(dd.getTypeCode(), sub);
                }
            }
            serviceResponse.setRspBody(result);
        }
        catch (QTBusinessException e) {
            log.error(e.getMessage());
            serviceResponse.setRetCode(e.getCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
            serviceResponse.setRetCode("500");
        }
        return serviceResponse;
    }

    @PostMapping(value = "/findTypeByCodeAndValue")
    @ResponseBody
    public JsonResponse<SysDictDataVO> findTypeByCodeAndValue(@RequestParam("typeCode") String typeCode, @RequestParam("typeValue") String typeValue) {
        JsonResponse<SysDictDataVO> serviceResponse = new JsonResponse<>();
        try {
            List<SysDictDataEntity> typeList = sysDictDataService.findChildsByTypeCode(typeCode);
            if (typeList != null) {
                for (SysDictDataEntity dd : typeList) {
                    if (dd.getTypeCode() != null && dd.getTypeCode().equals(typeValue)) {
                        SysDictDataVO typeVO = BeanCopyUtils.copy(dd, SysDictDataVO.class);
                        serviceResponse.setRspBody(typeVO);
                        break;
                    }
                }
            }
        }
        catch (QTBusinessException e) {
            log.error("", e);
            // serviceResponse.setException(e);
            serviceResponse.setRetCode(e.getCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
//            serviceResponse.setException(new QTBusinessException("500", new Object[] {}));
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        return serviceResponse;
    }

    @PostMapping(value = "/findChildsByTypeCode")
    @ResponseBody
    public JsonResponse<List<SysDictDataVO>> findChildsByTypeCode(@RequestParam("typeCode") String typeCode) {
        JsonResponse<List<SysDictDataVO>> serviceResponse = new JsonResponse<>();
        try {
            List<SysDictDataEntity> typeList = sysDictDataService.findChildsByTypeCode(typeCode);
            List<SysDictDataVO> typeVOList = BeanCopyUtils.copyList(typeList, SysDictDataVO.class);
            serviceResponse.setRspBody(typeVOList);
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        return serviceResponse;
    }

    @PostMapping(value = "/findBaseDictDataTree")
    @ResponseBody
    public JsonResponse<List<SysDictDataVO>> findBaseDictDataTree() {
        JsonResponse<List<SysDictDataVO>> serviceResponse = new JsonResponse<>();
        try {
            List<SysDictDataEntity> treeList = sysDictDataService.findBaseDictDataTree();
            List<SysDictDataVO> treeVOList = new ArrayList<>();
            for (SysDictDataEntity bdd : treeList) {
                treeVOList.add(sysDictDataService.copyModel2VORecursive(bdd, null));
            }
            serviceResponse.setRspBody(treeVOList);
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        return serviceResponse;
    }


    @PostMapping(value = "/findBaseDictDataByPage")
    @ResponseBody
    public JsonResponse<PageDTO<SysDictDataVO>> findBaseDictDataByPage(@RequestBody JsonRequest<Map<String, Object>> jsonRequest) {
        JsonResponse<PageDTO<SysDictDataVO>> serviceResponse = new JsonResponse<>();
        try {
            Map<String, Object> params = jsonRequest.getReqBody();
            int pageNum = 1;
            int pageSize = 10;
            if (params.containsKey("pageNum")) {
                if (params.get("pageNum") != null && !params.get("pageNum").toString().trim().equals("")) {
                    pageNum = Integer.parseInt(params.get("pageNum") + "");
                }
            }
            if (params.containsKey("pageSize")) {
                if (params.get("pageSize") != null && !params.get("pageSize").toString().trim().equals("")) {
                    pageSize = Integer.parseInt(params.get("pageSize") + "");
                }
            }
            log.debug(params.get("typeName") + "");

            params.put("parentSid", 0);
            params.put("order", "TYPE_LEVEL ASC, TYPE_SEQ ASC");
            SysDictDataEntity entity = JSON.parseObject(JSON.toJSONString(params), SysDictDataEntity.class);
            IPage<SysDictDataEntity> iPage = sysDictDataService.listByPage(new Page<>(pageNum, pageSize), entity);

            List<SysDictDataEntity> levelOneList = iPage.getRecords();
            List<SysDictDataVO> levelOneVOList = BeanCopyUtils.copyList(levelOneList, SysDictDataVO.class);
            PageDTO<SysDictDataVO> pageDTO = new PageDTO<>(Long.valueOf(iPage.getCurrent()).intValue(), Long.valueOf(iPage.getSize()).intValue());
            pageDTO.setTotal(iPage.getTotal());
            pageDTO.setResultData(levelOneVOList);
            copyModel2VORecursiveAndFindSub(levelOneVOList);
            serviceResponse.setRspBody(pageDTO);
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
//            serviceResponse.setRetCode(e.getErrorCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
//            serviceResponse.setException(new QTBusinessException("500", new Object[] {}));
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        finally {
            PageDTOUtils.endPage();
        }
        return serviceResponse;
    }

    /**
     * 保存数据(根据操作类型，分别做插入、修改、删除操作)
     */
    @PostMapping(value = "/saveData")
    @ResponseBody
    public JsonResponse<String> saveData(@RequestBody JsonRequest<List<SysDictDataVO>> jsonRequest) {
        JsonResponse<String> serviceResponse = new JsonResponse<>();
        try {
            List<SysDictDataVO> voList = jsonRequest.getReqBody();
            List<SysDictDataEntity> dataList = new ArrayList<>();
            for (SysDictDataVO vo : voList) {
                dataList.add(copyVO2ModelRecursive(vo, null));
            }
            sysDictDataService.saveData(dataList);
            serviceResponse.setRspBody("操作成功!");
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
            serviceResponse.setRetCode("错误");
            serviceResponse.setRetDesc(e.getMessage());
        }
        return serviceResponse;
    }

    private void copyModel2VORecursiveAndFindSub(List<SysDictDataVO> list) {
        for (SysDictDataVO vo : list) {
            LambdaQueryWrapper<SysDictDataEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysDictDataEntity::getParentSid, vo.getTypeSid());
            List<SysDictDataEntity> modelList = sysDictDataService.list(queryWrapper);
            List<SysDictDataVO> voList = BeanCopyUtils.copyList(modelList, SysDictDataVO.class);
            vo.setChildren(voList);
            if (voList.size() > 0) {
                copyModel2VORecursiveAndFindSub(voList);
            }
        }
    }

    /**
     * 递归拷贝子属性(vo->model)
     *
     * @param dictDataVO
     * @param baseDictData
     * @return
     */
    private SysDictDataEntity copyVO2ModelRecursive(SysDictDataVO dictDataVO, SysDictDataEntity baseDictData) {
        if (baseDictData == null) {
            baseDictData = BeanCopyUtils.copy(dictDataVO, SysDictDataEntity.class);
        }

        if (dictDataVO.getChildren() != null && dictDataVO.getChildren().size() > 0) {
            List<SysDictDataEntity> baseDictDataList = new ArrayList<>();
            for (SysDictDataVO vo : dictDataVO.getChildren()) {
                SysDictDataEntity model = BeanCopyUtils.copy(vo, SysDictDataEntity.class);
                baseDictDataList.add(model);
                copyVO2ModelRecursive(vo, model);
            }
            baseDictData.setChildren(baseDictDataList);
        }
        return baseDictData;
    }

    /**
     * 查找TypeCode子项对应的TypeName
     *
     * @param typeCode  值集类型
     * @param typeValue 值集子项编码
     * @return 值集子项名称
     */
    @PostMapping(value = "/findTypeNameByCodeAndValue")
    @ResponseBody
    public JsonResponse<String> findTypeNameByCodeAndValue(@RequestParam("typeCode") String typeCode, @RequestParam("typeValue") String typeValue) {
        JsonResponse<String> serviceResponse = new JsonResponse<>();
        try {
            List<SysDictDataEntity> typeList = sysDictDataService.findChildsByTypeCode(typeCode);
            String typeName = null;
            for (SysDictDataEntity dd : typeList) {
                if (dd.getTypeCode() != null && dd.getTypeCode().equals(typeValue)) {
                    typeName = dd.getTypeName();
                    break;
                }
            }
            serviceResponse.setRspBody(typeName);
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
//            serviceResponse.setRetCode(e.getErrorCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
//            serviceResponse.setException(new QTBusinessException("500", new Object[] {}));
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        return serviceResponse;
    }

    /**
     * 查找TypeCode子项对应的typeValue
     *
     * @param typeCode 值集类型
     * @param typeName 值集子项名称
     * @return 值集子项名称
     */
    @PostMapping(value = "/findTypeCodeByCodeAndName")
    @ResponseBody
    public JsonResponse<String> findTypeCodeByCodeAndName(@RequestParam("typeCode") String typeCode, @RequestParam("typeName") String typeName) {
        JsonResponse<String> serviceResponse = new JsonResponse<>();
        try {
            List<SysDictDataEntity> typeList = sysDictDataService.findChildsByTypeCode(typeCode);
            String typeValue = null;
            for (SysDictDataEntity dd : typeList) {
                if (dd.getTypeName() != null && dd.getTypeName().equals(typeName)) {
                    typeValue = dd.getTypeCode();
                    break;
                }
            }
            serviceResponse.setRspBody(typeValue);
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
//            serviceResponse.setRetCode(e.getErrorCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
//            serviceResponse.setException(new QTBusinessException("500", new Object[] {}));
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        return serviceResponse;
    }

    /**
     * 查找TypeCode子项对应的backup1
     *
     * @param typeCode     值集类型
     * @param typeItemCode 值集子项Code
     * @return 值集子项名称
     */
    @PostMapping(value = "/findBackup1ByCodeAndName")
    @ResponseBody
    public JsonResponse<String> findBackup1ByCodeAndName(@RequestParam("typeCode") String typeCode, @RequestParam("typeItemCode") String typeItemCode) {
        JsonResponse<String> serviceResponse = new JsonResponse<>();
        try {
            List<SysDictDataEntity> typeList = sysDictDataService.findChildsByTypeCode(typeCode);
            String typeValue = null;
            for (SysDictDataEntity dd : typeList) {
                if (dd.getTypeCode() != null && dd.getTypeCode().equals(typeItemCode)) {
                    typeValue = dd.getBackup1();
                    break;
                }
            }
            serviceResponse.setRspBody(typeValue);
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
//            serviceResponse.setRetCode(e.getErrorCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
//            serviceResponse.setException(new QTBusinessException("500", new Object[] {}));
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        return serviceResponse;
    }

    @PostMapping(value = "/findTypeByCodeAndBackup1")
    @ResponseBody
    public JsonResponse<SysDictDataVO> findTypeByCodeAndBackup1(@RequestParam("typeCode") String typeCode, @RequestParam("backup1") String backup1) {
        JsonResponse<SysDictDataVO> serviceResponse = new JsonResponse<>();
        try {
            List<SysDictDataEntity> typeList = sysDictDataService.findChildsByTypeCode(typeCode);
            if (typeList != null) {
                if (!StringUtils.isEmpty(backup1)) {
                    for (SysDictDataEntity dd : typeList) {
                        if (dd.getTypeCode() != null && backup1.equals(dd.getBackup1())) {
                            SysDictDataVO typeVO = BeanCopyUtils.copy(dd, SysDictDataVO.class);
                            serviceResponse.setRspBody(typeVO);
                            break;
                        }
                    }
                }
            }
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
//            serviceResponse.setRetCode(e.getErrorCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
//            serviceResponse.setException(new QTBusinessException("500", new Object[] {}));
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        return serviceResponse;
    }

    @PostMapping(value = "/findTypeByCodeAndRemark")
    @ResponseBody
    public JsonResponse<SysDictDataVO> findTypeByCodeAndRemark(@RequestParam("typeCode") String typeCode, @RequestParam("remark") String remark) {
        JsonResponse<SysDictDataVO> serviceResponse = new JsonResponse<>();
        try {
            List<SysDictDataEntity> typeList = sysDictDataService.findChildsByTypeCode(typeCode);
            if (typeList != null) {
                for (SysDictDataEntity dd : typeList) {
                    if (dd.getRemark() != null && dd.getRemark().equals(remark)) {
                        SysDictDataVO typeVO = BeanCopyUtils.copy(dd, SysDictDataVO.class);
                        serviceResponse.setRspBody(typeVO);
                        break;
                    }
                }
            }
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
//            serviceResponse.setRetCode(e.getErrorCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
//            serviceResponse.setException(new QTBusinessException("500", new Object[] {}));
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        return serviceResponse;
    }

    @PostMapping(value = "/findTypeByBackup12")
    @ResponseBody
    public JsonResponse<List<SysDictDataVO>> findTypeByBackup12(@RequestParam("typeCode") String typeCode, @RequestParam("backup1") String backup1, @RequestParam("backup2") String backup2) {
        JsonResponse<List<SysDictDataVO>> serviceResponse = new JsonResponse<>();
        try {
            List<SysDictDataEntity> typeList = sysDictDataService.findChildsByTypeCode(typeCode);
            List<SysDictDataVO> voList = new ArrayList<>();
            if (typeList != null) {
                for (SysDictDataEntity dd : typeList) {
                    if (dd.getBackup1() != null && dd.getBackup1().indexOf(backup1) >= 0 && dd.getBackup2() != null && dd.getBackup2().equals(backup2)) {
                        SysDictDataVO typeVO = BeanCopyUtils.copy(dd, SysDictDataVO.class);
                        voList.add(typeVO);
                        serviceResponse.setRspBody(voList);
                    }
                }
            }
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
            // serviceResponse.setRetCode(e.getErrorCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
            // serviceResponse.setException(new QTBusinessException("500", new Object[] {}));
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        return serviceResponse;
    }

    @PostMapping(value = "/findTypeByBackup1")
    @ResponseBody
    public JsonResponse<List<SysDictDataVO>> findTypeByBackup1(@RequestParam("typeCode") String typeCode, @RequestParam("backup1") String backup1) {
        JsonResponse<List<SysDictDataVO>> serviceResponse = new JsonResponse<>();
        try {
            List<SysDictDataEntity> typeList = sysDictDataService.findChildsByTypeCode(typeCode);
            List<SysDictDataVO> voList = new ArrayList<>();
            if (typeList != null) {
                for (SysDictDataEntity dd : typeList) {
                    if (dd.getBackup1() != null && dd.getBackup1().indexOf(backup1) >= 0) {
                        SysDictDataVO typeVO = BeanCopyUtils.copy(dd, SysDictDataVO.class);
                        voList.add(typeVO);
                        serviceResponse.setRspBody(voList);
                    }
                }
            }
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
            // serviceResponse.setRetCode(e.getErrorCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
            // serviceResponse.setException(new QTBusinessException("500", new Object[] {}));
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        return serviceResponse;
    }

    /**
     * 刷新redis
     *
     * @param vo
     * @return
     */
    @PostMapping(value = "/dumpRedis")
    @ResponseBody
    public JsonResponse<String> dumpRedis(@RequestBody JsonRequest<SysDictDataVO> jsonRequest) {
        JsonResponse<String> serviceResponse = new JsonResponse<>();
        try {
            SysDictDataVO vo = jsonRequest.getReqBody();
            //判断小代码 redis是否存在
            boolean baseDictData1 = redisUtils.hasKey(RedisKeys.DICT_DATA_KEY);
            if (baseDictData1) {
                log.info("===============================================存在 删除redis---------------------------------------");
                boolean baseDictData = redisUtils.deleteObject(RedisKeys.DICT_DATA_KEY);
                if (baseDictData) {
                    // 更新
                    sysDictDataService.findBaseDictDataMap();
                    serviceResponse.setRspBody("刷新成功");
                }
            }
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
            // serviceResponse.setRetCode(e.getErrorCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
            // serviceResponse.setException(new QTBusinessException("500", new Object[] {}));
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        return serviceResponse;
    }

    @PostMapping(value = "/findBaseDictDataByTypeCodePage")
    @ResponseBody
    public JsonResponse<PageDTO<SysDictDataVO>> findBaseDictDataByTypeCodePage(@RequestBody JsonRequest<Map<String, Object>> jsonRequest) {
        JsonResponse<PageDTO<SysDictDataVO>> serviceResponse = new JsonResponse<>();
        try {
            Map<String, Object> params = jsonRequest.getReqBody();
            int pageNum = 1;
            int pageSize = 10;
            if (params.containsKey("pageNum")) {
                if (params.get("pageNum") != null && !params.get("pageNum").toString().trim().equals("")) {
                    pageNum = (Integer) params.get("pageNum");
                }
            }
            if (params.containsKey("pageSize")) {
                if (params.get("pageSize") != null && !params.get("pageSize").toString().trim().equals("")) {
                    pageSize = (Integer) params.get("pageSize");
                }
            }

            PageDTOUtils.startPage(pageNum, pageSize);
            List<SysDictDataEntity> levelOneList = sysDictDataService.findBaseDictDataByPage(params);
            List<SysDictDataVO> levelOneVOList = BeanCopyUtils.copyList(levelOneList, SysDictDataVO.class);
            PageDTO<SysDictDataVO> pages = PageDTOUtils.transform(levelOneVOList);
            PageDTOUtils.endPage();

            serviceResponse.setRspBody(pages);
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
            // serviceResponse.setRetCode(e.getErrorCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
            // serviceResponse.setException(new QTBusinessException("500", new Object[] {}));
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        finally {
            PageDTOUtils.endPage();
        }
        return serviceResponse;
    }

    /**
     * 查找TypeCode子项对应的所有typeName
     *
     * @param typeCode 值集类型
     * @return 子项集合
     */
    @PostMapping(value = "/findByList")
    @ResponseBody
    public JsonResponse<List<SysDictDataVO>> findByList(@RequestParam String typeCode) {
        JsonResponse<List<SysDictDataVO>> serviceResponse = new JsonResponse<>();
        try {
            List<SysDictDataEntity> typeList = sysDictDataService.findByList(typeCode);
            List<SysDictDataVO> dictDataVOS = BeanCopyUtils.copyList(typeList, SysDictDataVO.class);
            serviceResponse.setRspBody(dictDataVOS);
        }
        catch (QTBusinessException e) {
            log.error("", e);
            serviceResponse.setRetCode(e.getCode());
            // serviceResponse.setRetCode(e.getErrorCode());
            serviceResponse.setRetDesc(e.getMessage());
        }
        catch (Exception e) {
            log.error("", e);
            // serviceResponse.setException(new QTBusinessException("500", new Object[] {}));
            serviceResponse.setRetCode("500");
            log.error(e.getMessage());
        }
        return serviceResponse;
    }
}
