package com.qingtian.lcpes.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qingtian.lcpes.base.facade.BasicMapper;
import com.qingtian.lcpes.modules.sys.entity.SysDictDataEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典表 Mapper
 * 自定义Mapper方法
 * </p>
 *
 * @author zhangxq
 * @since 2022-08-30
 */
@Mapper
public interface SysDictDataMapper extends BasicMapper<SysDictDataEntity> {

    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysDictDataEntity> listByPage(IPage<SysDictDataEntity> page,
                                              @Param(Constants.ENTITY) SysDictDataEntity entity);

    List<SysDictDataEntity> findAllForTree(Map<String, Object> params);

    /**
     * 查询这个主数据下的最大的 子数据序号
     * @param
     * @return
     */
    Integer selectMaxTypeSeqByParentSid(Long parentSid);

    List<SysDictDataEntity> findByList(@Param("typeCode") String typeCode);

    List<SysDictDataEntity> find(Map<String, Object> params);
}
