package demo;

import java.lang.annotation.*;

@Documented // 指明这个注解可以被文档化
/* 指出注解的在什么生存周期可见
   RUNTIME：运行期间；
   SOURCE：源码期间；
   CLASS：默认只在class期间可见 */
@Retention(RetentionPolicy.RUNTIME)
/*
   指出这个注解可以被放在哪个位置
   TYPE：类型上，包括class、interface、abstrat class
   FIELD：类中的属性上
   METHOD：类中的方法上
   PARAMETER：方法中的参数上
   CONSTRUCTOR：构造类上
   LOCAL_VARIABLE：本地参数上
   ANNOTATION_TYPE：声明的注解上
   TYPE_PARAMETER：类型参数上
   TYPE_USE：类型使用上
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD,})
public @interface MyAnnotation {
}
