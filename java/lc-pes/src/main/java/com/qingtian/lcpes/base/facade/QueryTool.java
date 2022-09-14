package com.qingtian.lcpes.base.facade;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingtian.lcpes.base.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class QueryTool {

    /**
     * 根据 @QueryCondition 查询条件注解，初始化查询
     *
     * @param entityClazz  实体类
     * @param queryWrapper 查询对象
     * @param vo           视图对象
     * @param <T>          实体类
     * @param <V>          视图类
     */
    public static <T, V> void initQueryWrapper(Class<?> entityClazz, QueryWrapper<T> queryWrapper, V vo) {
        try {
            Field[] fields = vo.getClass().getDeclaredFields();
            int n = fields.length;

            for (Field declaredField : fields) {
                declaredField.setAccessible(true);
                Object fieldValue = declaredField.get(vo);
                QueryCondition queryCondition = declaredField.getAnnotation(QueryCondition.class);
                if (fieldValue != null) {
                    if (queryCondition == null) {
                        queryWrapper.eq(StringUtils.isNotBlank(fieldValue.toString()), getTableName(entityClazz) + "." + getTableFieldName(entityClazz, declaredField), fieldValue.toString());
                    }
                    else if (queryCondition.isCondition()) {
                        Method method = queryCondition.value().getMethod();
                        method.invoke(queryWrapper, StringUtils.isNotBlank(fieldValue.toString()), getTableName(entityClazz) + "." + getTableFieldName(entityClazz, declaredField), fieldValue.toString());
                    }
                }
            }
            paddingDefaultOrderQuery(queryWrapper, (BaseVO) vo);
        }
        catch (Exception e) {
            throw new RuntimeException("填充默认的SQL条件出错", e);
        }
    }

    private static void paddingDefaultOrderQuery(QueryWrapper queryWrapper, BaseVO baseVO) {
        queryWrapper.orderBy(baseVO != null && StringUtils.isNotBlank(baseVO.getColumnName()), baseVO.getIsAsc() == null ? false : baseVO.getIsAsc(), StringUtils.camelToUnderline(baseVO.getColumnName()), new Object[0]);
    }

    private static String getTableName(Class baseClazz) {
        TableName tableName = (TableName) baseClazz.getDeclaredAnnotation(TableName.class);
        return tableName != null && StringUtils.isNotBlank(tableName.value()) ? tableName.value() : StringUtils.camelToUnderline(baseClazz.getClass().getName());
    }

    private static String getTableFieldName(Class baseClazz, Field field) {
        Field baseField = null;

        try {
            baseField = baseClazz.getDeclaredField(field.getName());
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (baseField == null) {
            baseField = field;
        }

        TableId tableId = baseField.getAnnotation(TableId.class);
        if (tableId != null && StringUtils.isNotBlank(tableId.value())) {
            return tableId.value();
        }
        else {
            TableField tableField = baseField.getAnnotation(TableField.class);
            return tableField != null && StringUtils.isNotBlank(tableField.value()) ? tableField.value() : StringUtils.camelToUnderline(baseField.getName());
        }
    }

}
