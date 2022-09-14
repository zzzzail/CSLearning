package com.qingtian.lcpes.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class ReflectUtils {
    private static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

    public static Object getFieldValue(Object object, String propertyName) {
        try {
            if (object == null) {
                return null;
            }
            else if (object instanceof Map) {
                Map<String, Object> map = (Map) object;
                return map.get(propertyName);
            }
            else {
                Field field = getDeclaredField(object, propertyName);
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                Object result = null;

                try {
                    result = field.get(object);
                }
                catch (IllegalAccessException var6) {
                }

                field.setAccessible(accessible);
                return result;
            }
        }
        catch (Exception var7) {
            throw new RuntimeException("获取值失败", var7);
        }
    }

    public static void setFieldValue(Object object, String propertyName, Object newValue) {
        try {
            if (object != null) {
                if (object instanceof Map) {
                    Map<String, Object> map = (Map) object;
                    map.put(propertyName, newValue);
                }
                else {
                    Field field = getDeclaredField(object, propertyName);
                    boolean accessible = field.isAccessible();
                    field.setAccessible(true);

                    try {
                        field.set(object, newValue);
                    }
                    catch (IllegalAccessException var6) {
                    }

                    field.setAccessible(accessible);
                }
            }
        }
        catch (Exception var7) {
            throw new RuntimeException("设置值失败", var7);
        }
    }

    public static Field getDeclaredField(Object object, String propertyName) throws NoSuchFieldException {
        return getDeclaredField(object.getClass(), propertyName);
    }

    private static Field getDeclaredField(Class clazz, String propertyName) throws NoSuchFieldException {
        Class superClass = clazz;

        while (superClass != Object.class) {
            try {
                return superClass.getDeclaredField(propertyName);
            }
            catch (NoSuchFieldException var4) {
                superClass = superClass.getSuperclass();
            }
        }

        throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
    }

    public static Object invokePrivateMethod(Object object, String methodName, Object[] params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class[] types = new Class[params.length];

        for (int i = 0; i < params.length; ++i) {
            types[i] = params[i].getClass();
        }

        Method method = object.getClass().getDeclaredMethod(methodName, types);
        method.setAccessible(true);
        return method.invoke(object, params);
    }

    public static Object invokePrivateMethod(Object object, String methodName, Object param) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invokePrivateMethod(object, methodName, new Object[]{param});
    }

    public static String fieldsToString(Object obj) {
        int levels = 0;

        for (Class sourceClass = obj.getClass(); !sourceClass.getName().equals("java.lang.Object"); sourceClass = sourceClass.getSuperclass()) {
            ++levels;
        }

        --levels;
        return p_fieldsToString(obj, levels);
    }

    private static String p_fieldsToString(Object obj, int superLevels) {
        String[] results = new String[superLevels + 1];
        StringBuffer result = new StringBuffer();

        try {
            Class sourceClass = obj.getClass();

            int level;
            for (level = 0; level <= superLevels; ++level) {
                Field[] fields = sourceClass.getDeclaredFields();

                for (int i = 0; i < fields.length; ++i) {
                    Field f = fields[i];
                    int modifier = f.getModifiers();
                    if (!Modifier.isStatic(modifier)) {
                        f.setAccessible(true);
                        String name = f.getName();
                        if (name.indexOf("m_") == 0) {
                            name = name.substring(2);
                        }

                        Object tempObject = f.get(obj);
                        result.append(name);
                        result.append("[");
                        result.append(tempObject);
                        result.append("]");
                        if (i < fields.length - 1) {
                            result.append(",");
                        }

                        result.append(" ");
                    }
                }

                results[superLevels - level] = result.toString();
                result.setLength(0);
                sourceClass = sourceClass.getSuperclass();
            }

            for (level = 0; level <= superLevels; ++level) {
                result.append(results[level]);
            }
        }
        catch (Exception var12) {
            logger.error("ReflectUtil: Problem reflecting fields", var12);
        }

        return result.toString();
    }

    public static Object forceGetProperty(Object src, String key) {
        Object o = new Object();

        try {
            Field declaredField = src.getClass().getDeclaredField(key);
            declaredField.setAccessible(true);
            o = declaredField.get(src);
        }
        catch (Exception var4) {
            var4.printStackTrace();
        }

        return o;
    }
}
