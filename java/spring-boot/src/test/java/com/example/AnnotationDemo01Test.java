package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zail
 * @date 2022/6/25
 */
@SpringBootTest(classes = Application.class)
class AnnotationDemo01Test {
    
    @Autowired
    private AnnotationDemo01 annotationDemo01;
    
    @Test
    public void testBeanNameService() {
        annotationDemo01.beanNameService();
    }

}