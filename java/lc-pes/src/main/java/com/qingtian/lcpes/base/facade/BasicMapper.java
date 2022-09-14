package com.qingtian.lcpes.base.facade;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public interface BasicMapper<T> extends Mapper<T> {
    int insert(T entity);

    int deleteById(Serializable id);

    int deleteByMap(@Param("cm") Map<String, Object> map);

    int delete(@Param("ew") Wrapper<T> wrapper);

    int deleteBatchIds(@Param("coll") Collection<? extends Serializable> ids);

    int updateById(@Param("et") T entity);

    int update(@Param("et") T entity, @Param("ew") Wrapper<T> wrapper);

    T selectById(Serializable id);

    List<T> selectBatchIds(@Param("coll") Collection<? extends Serializable> ids);

    List<T> selectByMap(@Param("cm") Map<String, Object> map);

    T selectOne(@Param("ew") Wrapper<T> wrapper);

    Integer selectCount(@Param("ew") Wrapper<T> wrapper);

    List<T> selectList(@Param("ew") Wrapper<T> wrapper);

    List<Map<String, Object>> selectMaps(@Param("ew") Wrapper<T> wrapper);

    List<Object> selectObjs(@Param("ew") Wrapper<T> wrapper);

    <E extends IPage<T>> E selectPage(E page, @Param("ew") Wrapper<T> wrapper);

    <E extends IPage<Map<String, Object>>> E selectMapsPage(E page, @Param("ew") Wrapper<T> wrapper);

    <E extends IPage<T>> E find(E page, Map<String, Object> map);
}
