package com.qingtian.lcpes.base.facade;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class BaseServiceImpl<M extends BasicMapper<T>, T> implements BaseService<T> {
    protected Log log = LogFactory.getLog(this.getClass());
    @Autowired
    protected M basicMapper;
    protected Class<T> entityClass = this.currentModelClass();
    protected Class<T> mapperClass = this.currentMapperClass();

    public M getBasicMapper() {
        return this.basicMapper;
    }

    public Class<T> getEntityClass() {
        return this.entityClass;
    }

    /** @deprecated  */
    @Deprecated
    protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    protected Class<T> currentMapperClass() {
        return (Class) this.getResolvableType().as(BaseServiceImpl.class).getGeneric(new int[]{0}).getType();
    }

    protected Class<T> currentModelClass() {
        return (Class) this.getResolvableType().as(BaseServiceImpl.class).getGeneric(new int[]{1}).getType();
    }

    protected ResolvableType getResolvableType() {
        return ResolvableType.forClass(ClassUtils.getUserClass(this.getClass()));
    }

    /** @deprecated  */
    @Deprecated
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(this.entityClass);
    }

    /** @deprecated  */
    @Deprecated
    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(this.entityClass));
    }

    /** @deprecated  */
    @Deprecated
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(this.entityClass).getSqlStatement(sqlMethod.getMethod());
    }

    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        String sqlStatement = this.getSqlStatement(SqlMethod.INSERT_ONE);
        return this.executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            sqlSession.insert(sqlStatement, entity);
        });
    }

    protected String getSqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.getSqlStatement(this.mapperClass, sqlMethod);
    }

    public boolean saveOrUpdate(T entity) {
        if (null == entity) {
            return false;
        }
        else {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
            Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
            return !StringUtils.checkValNull(idVal) && !Objects.isNull(this.getById((Serializable) idVal)) ? this.updateById(entity) : this.save(entity);
        }
    }

    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
        return SqlHelper.saveOrUpdateBatch(this.entityClass, this.mapperClass, this.log, entityList, batchSize, (sqlSession, entity) -> {
            Object idVal = ReflectionKit.getFieldValue(entity, keyProperty);
            return StringUtils.checkValNull(idVal) || CollectionUtils.isEmpty(sqlSession.selectList(this.getSqlStatement(SqlMethod.SELECT_BY_ID), entity));
        }, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap();
            param.put("et", entity);
            sqlSession.update(this.getSqlStatement(SqlMethod.UPDATE_BY_ID), param);
        });
    }

    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        String sqlStatement = this.getSqlStatement(SqlMethod.UPDATE_BY_ID);
        return this.executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap();
            param.put("et", entity);
            sqlSession.update(sqlStatement, param);
        });
    }

    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        return throwEx ? this.basicMapper.selectOne(queryWrapper) : SqlHelper.getObject(this.log, this.basicMapper.selectList(queryWrapper));
    }

    public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
        return SqlHelper.getObject(this.log, this.basicMapper.selectMaps(queryWrapper));
    }

    public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return SqlHelper.getObject(this.log, this.listObjs(queryWrapper, mapper));
    }

    /** @deprecated  */
    @Deprecated
    protected boolean executeBatch(Consumer<SqlSession> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, consumer);
    }

    protected <E> boolean executeBatch(Collection<E> list, int batchSize, BiConsumer<SqlSession, E> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, list, batchSize, consumer);
    }

    protected <E> boolean executeBatch(Collection<E> list, BiConsumer<SqlSession, E> consumer) {
        return this.executeBatch(list, 1000, consumer);
    }
}