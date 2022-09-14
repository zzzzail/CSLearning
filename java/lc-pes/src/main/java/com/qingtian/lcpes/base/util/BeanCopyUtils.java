package com.qingtian.lcpes.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class BeanCopyUtils {
    private static final Logger log = LoggerFactory.getLogger(BeanCopyUtils.class);


    public static <T> T copy(Object src, Class<T> clazz) {
        T t = null;
        try {
            if (src != null) {
                t = clazz.newInstance();
                BeanUtils.copyProperties(src, t);
            }
        }
        catch (Exception var4) {
            log.error("copy object error", var4);
        }

        return t;
    }

    public static <T> List<T> copyList(List<?> src, Class<T> clazz) {
        if (src == null) {
            return new ArrayList<>();
        }
        else {
            List<T> list = new ArrayList<>();
            for (Object o : src) {
                T t = copy(o, clazz);
                list.add(t);
            }

            return list;
        }
    }

    public static void copy(Object src, Object target) {
        if (src != null && target != null) {
            try {
                BeanUtils.copyProperties(src, target);
            }
            catch (Exception var3) {
                log.error("copy object error", var3);
            }
        }
    }

    public static Map<String, Object> getObjectMapProperties(Object src) {
        if (src == null) {
            return null;
        }
        else {
            Map<String, Object> map = new HashMap<>();

            try {
                Class<?> clazz = src.getClass();
                Set<String> props = new HashSet<>();

                for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
                    Field[] fs = clazz.getDeclaredFields();

                    for (Field f : fs) {
                        props.add(f.getName());
                    }
                }

                for (String key : props) {
                    map.put(key, ReflectUtils.forceGetProperty(src, key));
                }

                return map;
            }
            catch (Exception e) {
                log.error("get ObjectMap Properties error", e);
                return map;
            }
        }
    }

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    public static <T, V> List<V> copyPropertieses(List<T> sourceList, List<V> targetList, Class<?> clasz) {
        if (sourceList != null && sourceList.size() >= 1) {
            for (T t : sourceList) {
                V instance = null;

                try {
                    instance = (V) clasz.newInstance();
                }
                catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                copyProperties(t, instance);
                targetList.add(instance);
            }

            return targetList;
        }
        else {
            return targetList;
        }
    }

    public static <T> Set<T> copySet(Collection<?> src, Class<T> clazz) {
        if (src == null) {
            return new HashSet<>();
        }
        else {
            Set<T> list = new HashSet<>();
            for (Object o : src) {
                T t = copy(o, clazz);
                list.add(t);
            }
            return list;
        }
    }
}
