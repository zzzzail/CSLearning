package com.qingtian.lcpes.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseService;
import com.qingtian.lcpes.modules.sys.entity.SysDictDataEntity;
import com.qingtian.lcpes.modules.sys.vo.SysDictDataVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典表 服务类
 * </p>
 *
 * @author zhangxq
 * @since 2022-08-30
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    IPage<SysDictDataEntity> listByPage(IPage<SysDictDataEntity> page, SysDictDataEntity entity);

    List<SysDictDataEntity> findBaseDictDataByPage(Map<String, Object> params);

    List<SysDictDataEntity> findBaseDictDataTree();

    List<SysDictDataVO> findBaseDictDataMap();

    void saveData(List<SysDictDataEntity> dataList);

    List<SysDictDataEntity> findChildsByTypeCode(String typeCode);

    List<SysDictDataEntity> findByList(String typeCode);

    SysDictDataEntity changeMapToEntity(Map<String, Object> params);

    /**
     * 递归拷贝子属性(model->vo)
     *
     * @param dictDataVO
     * @param baseDictData
     * @return
     */
    SysDictDataVO copyModel2VORecursive(SysDictDataEntity baseDictData, SysDictDataVO dictDataVO);
}
