package com.qingtian.lcpes.modules.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.util.StringUtil;
import com.qingtian.lcpes.base.cache.redis.RedisKeys;
import com.qingtian.lcpes.base.exception.QTBusinessException;
import com.qingtian.lcpes.base.facade.BaseServiceImpl;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.base.util.RedisUtils;
import com.qingtian.lcpes.modules.sys.entity.SysDictDataEntity;
import com.qingtian.lcpes.modules.sys.mapper.SysDictDataMapper;
import com.qingtian.lcpes.modules.sys.service.SysDictDataService;
import com.qingtian.lcpes.modules.sys.vo.SysDictDataVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 数据字典表 服务实现类
 * </p>
 *
 * @author zhangxq
 * @since 2022-08-30
 */
@Service
@Slf4j
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictDataMapper, SysDictDataEntity> implements SysDictDataService {

    @Autowired
    private SysDictDataMapper sysDictDataMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public IPage<SysDictDataEntity> listByPage(IPage<SysDictDataEntity> page, SysDictDataEntity entity) {
        return page.setRecords(getBasicMapper().listByPage(page, entity));
    }

    /**
     * 根据父级编码获取直接下级数据
     *
     * @param typeCode
     * @return
     */
    @Override
    public List<SysDictDataEntity> findChildsByTypeCode(String typeCode) {
        List<SysDictDataEntity> dataList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("typeCode", typeCode);
        params.put("typeLevel", 1);
        SysDictDataEntity bdd = this.findUniqueByParams(params);
        if (bdd != null) {
            params.clear();
            params.put("parentSid", bdd.getTypeSid());
            params.put("order", "TYPE_SEQ ASC");
            dataList = this.findByParams(params);
        }
        return dataList;
    }

    private List<SysDictDataEntity> findByParams(Map<String, Object> params) {
        return sysDictDataMapper.find(params);
    }

    private SysDictDataEntity findUniqueByParams(Map<String, Object> params) {
        try {
            log.info("findUniqueByParams:" + params.toString());
            List<SysDictDataEntity> list = sysDictDataMapper.find(params);
            if (list == null || list.size() == 0) {
                return null;
            }
            else if (list.size() == 1) {
                return list.get(0);
            }
            else {
                throw new QTBusinessException("MultiDataFoundException");
            }
        }
        catch (QTBusinessException e) {
            log.error("", e);
            throw e;
        }
        catch (Exception e) {
            log.error("", e);
            throw new QTBusinessException("DataAccessException");
        }
    }


    @Override
    public List<SysDictDataEntity> findByList(String typeCode) {
        return sysDictDataMapper.findByList(typeCode);
    }

    /**
     * 保存数据(根据操作类型，分别做插入、修改、删除操作)
     *
     * @param dataList
     */
    @Override
    public void saveData(List<SysDictDataEntity> dataList) {
        for (SysDictDataEntity dictData : dataList) {
            execRecursive(dictData);
        }
    }

    /**
     * 递归处理节点
     *
     * @param dictData
     */
    private void execRecursive(SysDictDataEntity dictData) {
        if (StringUtil.isEmpty(dictData.getOpt())) {
            if (dictData.getChildren() != null && dictData.getChildren().size() > 0) {
                for (int i = 0; i < dictData.getChildren().size(); i++) {
                    execRecursive(dictData.getChildren().get(i));
                }
            }
            else {
                return;
            }
        }

        if (StringUtil.isEmpty(dictData.getOpt())) {
            return;
        }

        // I 为插入
        if (dictData.getOpt().trim().equalsIgnoreCase("I")) {
            if (dictData.getParentSid() == null && dictData.getTypeLevel() == 1) {
                dictData.setParentSid(0L); // 顶级树根
            }
            else if (dictData.getParentSid() == null && dictData.getTypeLevel() != 1) {
                // 没有 parent id
                throw new QTBusinessException("PARENT_ID_EMPTY");
            }
            insertRecursive(dictData);
        }
        // U 为更新
        else if (dictData.getOpt().trim().equalsIgnoreCase("U")) {
            if (dictData.getTypeSid() == null) {
                throw new QTBusinessException("ID_KEY_EMPTY");
            }
            else {
                dictData.setUpdatedDt(LocalDateTime.now());
                checkDataExist(dictData);
                this.updateById(dictData);
                if (dictData.getChildren() != null && dictData.getChildren().size() > 0) {
                    for (SysDictDataEntity bdd : dictData.getChildren()) {
                        execRecursive(bdd);
                    }
                }
            }
        }
        // D 为删除
        else if (dictData.getTypeSid() != null && dictData.getOpt().trim().equalsIgnoreCase("D")) {
            deleteRecursive(dictData);
        }
    }

    /**
     * 检查同一层级下，编码是否重复
     *
     * @param dictData
     * @return
     */
    private void checkDataExist(SysDictDataEntity dictData) {
        if (dictData.getParentSid() == null) {
            throw new QTBusinessException("PARENT_ID_EMPTY");
        }
        else {
            Map<String, Object> params = new HashMap<>();
            params.put("parentSid", dictData.getParentSid());
            params.put("typeCode", dictData.getTypeCode());
            SysDictDataEntity dd = this.findUniqueByParams(params);
            if (dictData.getTypeSid() == null) { // 新增
                if (dd != null) {
                    log.info(dictData.toString());
                    throw new QTBusinessException("数据已存在！typeSid：" + dd.getTypeSid());
                }
            }
            else { // 修改
                if (dd != null && !Objects.equals(dd.getTypeSid(), dictData.getTypeSid())) {
                    throw new QTBusinessException("TypeCodeExistException");
                }
            }
        }
    }

    /**
     * 递归保存节点
     *
     * @param dictData
     */
    private void insertRecursive(SysDictDataEntity dictData) {
        dictData.setTypeSid(null);
        dictData.setCreatedDt(LocalDateTime.now());
        checkDataExist(dictData);
        sysDictDataMapper.insert(dictData);
        if (dictData.getChildren() != null && dictData.getChildren().size() > 0) {
            for (SysDictDataEntity bdd : dictData.getChildren()) {
                bdd.setParentSid(dictData.getTypeSid());
                insertRecursive(bdd);
            }
        }
    }

    /**
     * 递归删除节点
     *
     * @param dictData
     */
    private void deleteRecursive(SysDictDataEntity dictData) {
        if (dictData.getTypeSid() != null) {
            if (dictData.getChildren() != null && dictData.getChildren().size() > 0) {
                for (SysDictDataEntity bdd : dictData.getChildren()) {
                    deleteRecursive(bdd);
                }
            }
            sysDictDataMapper.deleteById(dictData.getTypeSid());
        }
        else {
            throw new QTBusinessException("ID_KEY_EMPTY");
        }
    }

    /**
     * 查询基础数据树
     */
    @Override
    public List<SysDictDataEntity> findBaseDictDataTree() {
        List<SysDictDataEntity> treeList = new ArrayList<>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("order", "TYPE_LEVEL ASC, TYPE_SEQ ASC");
        List<SysDictDataEntity> dataList = sysDictDataMapper.findAllForTree(params);

        for (SysDictDataEntity dictData : dataList) {
            if (!dictData.isLeafFlag()) {
                dictData.setLeafFlag(true);
                recursiveDataTree(dictData, dataList);
                treeList.add(dictData);
            }
        }
        return treeList;
    }

    @Override
    public List<SysDictDataVO> findBaseDictDataMap() {
        String result = (String) redisUtils.get(RedisKeys.DICT_DATA_KEY);
        List<SysDictDataVO> baseDictData = JSON.parseArray(result, SysDictDataVO.class);
        if (CollectionUtils.isNotEmpty(baseDictData)) {
            log.info("===============================================读取redis---------------------------------------");
            return baseDictData;
        }
        List<SysDictDataEntity> treeList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("order", "TYPE_LEVEL ASC, TYPE_SEQ ASC");
        List<SysDictDataEntity> dataList = sysDictDataMapper.findAllForTree(params);

        Map<Long, SysDictDataEntity> dataMap = new HashMap<>();
        for (SysDictDataEntity dictData : dataList) {
            dataMap.put(dictData.getTypeSid(), dictData);
        }

        Map<Long, SysDictDataEntity> map = new HashMap<>();
        for (SysDictDataEntity dictData : dataList) {
            if (dictData.getParentSid() == null) {
                continue;
            }
            if (dictData.getParentSid().intValue() == 0) {
                // 一级
                if (map.get(dictData.getTypeSid()) == null) {
                    map.put(dictData.getTypeSid(), dictData);
                }
            }
            else {
                // 子级
                if (map.get(dictData.getParentSid()) == null) {
                    // 先得到父级
                    if (dataMap.get(dictData.getParentSid()) != null) {
                        map.put(dataMap.get(dictData.getParentSid()).getTypeSid(), dataMap.get(dictData.getParentSid()));
                    }
                }

                // 将子级数据加到父级对象中
                if (map.get(dictData.getParentSid()) != null) {
                    map.get(dictData.getParentSid()).getChildren().add(dictData);
                }
            }
        }

        treeList.addAll(map.values());
        List<SysDictDataVO> treeVOList = new ArrayList<>();
        for (SysDictDataEntity bdd : treeList) {
            treeVOList.add(this.copyModel2VORecursive(bdd, null));
        }

        redisUtils.set(RedisKeys.DICT_DATA_KEY, JSON.toJSONString(treeVOList), 1000);
        log.info("===============================================存储redis---------------------------------------");

        return treeVOList;
    }

    /**
     * 查询基础数据树
     */
    @Override
    public List<SysDictDataEntity> findBaseDictDataByPage(Map<String, Object> params) {
        params.put("parentSid", 0);
        params.put("order", "TYPE_LEVEL ASC, TYPE_SEQ ASC");
        List<SysDictDataEntity> dataList = this.findByParams(params);
        return dataList;
    }

    /**
     * 通过递归将数据库中的数据转换为树形结构
     *
     * @param parentNode
     * @param dataList
     * @return
     */
    public void recursiveDataTree(SysDictDataEntity parentNode, List<SysDictDataEntity> dataList) {
        Iterator<SysDictDataEntity> iterator = dataList.iterator();
        while (iterator.hasNext()) {
            SysDictDataEntity childNode = iterator.next();
            if (childNode.getParentSid().intValue() == parentNode.getTypeSid().intValue() && !childNode.isLeafFlag()) {
                parentNode.getChildren().add(childNode);
                childNode.setLeafFlag(true);
                recursiveDataTree(childNode, dataList);
            }
        }
    }

    public SysDictDataEntity changeMapToEntity(Map<String, Object> params) {
        return JSON.parseObject(JSON.toJSONString(params), SysDictDataEntity.class);
    }

    /**
     * 递归拷贝子属性(model->vo)
     *
     * @param dictDataVO
     * @param baseDictData
     * @return
     */
    public SysDictDataVO copyModel2VORecursive(SysDictDataEntity baseDictData, SysDictDataVO dictDataVO) {
        if (dictDataVO == null) {
            dictDataVO = BeanCopyUtils.copy(baseDictData, SysDictDataVO.class);
        }
        if (baseDictData.getChildren() != null && baseDictData.getChildren().size() > 0) {
            List<SysDictDataVO> DictDataVOList = new ArrayList<>();
            for (SysDictDataEntity bdd : baseDictData.getChildren()) {
                SysDictDataVO vo = BeanCopyUtils.copy(bdd, SysDictDataVO.class);
                DictDataVOList.add(vo);
                copyModel2VORecursive(bdd, vo);
            }
            dictDataVO.setChildren(DictDataVOList);
        }
        return dictDataVO;
    }

}
