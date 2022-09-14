package com.qingtian.lcpes.base.db.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author zhangxq
 * @since 2022/9/6
 */
@Component
@Slf4j
public class MyBatisPlusAutoFillMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createdDt", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updatedDt", LocalDateTime.now());
    }
}
