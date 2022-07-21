package com.bfxy.rapid.rpc.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.bfxy.rapid.rpc.spring.register.RapidComponentScanRegistrar;

@Target(ElementType.TYPE)
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Import(RapidComponentScanRegistrar.class)
public @interface RapidComponentScan {

    String[] value() default {};


    String[] basePackages() default {};


    Class<?>[] basePackageClasses() default {};
    
}
