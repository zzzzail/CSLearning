package com.qingtian.lcpes.base;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {

    public static final String ENV_DEV = "dev";
    public static final String ENV_LOCAL = "local";
    public static final String ENV_TEST = "test";
    public static final String ENV_PROD = "prod";

    private static String ENV;
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        ApplicationContextHolder.clearHolder();
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static String getEnvironment() {
        if (StringUtils.isEmpty(ENV)) {
            ENV = getActiveProfile();
        }

        return ENV;
    }

    public static void setEnvironment(String env) {
        ENV = env;
    }

    public static void eventPublish(Object event) {
        applicationContext.publishEvent(event);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }

    public static String getActiveProfile() {
        // 获取 spring.active.profiles 配置的文件后缀
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }

    public static boolean isDemoMode() {
        return isTestMode() || isDevMode();
    }

    public static boolean isDevMode() {
        return ENV_DEV.equalsIgnoreCase(getActiveProfile());
    }

    public static boolean isTestMode() {
        return ENV_TEST.equalsIgnoreCase(getActiveProfile());
    }

    public static String getProperty(String name, String defaultValue) {
        return applicationContext.getEnvironment().getProperty(name, defaultValue);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annType) {
        return applicationContext.getBeansWithAnnotation(annType);
    }

    public static BeanDefinitionRegistry getBeanDefinitionRegistry() {
        Assert.notNull(applicationContext, "ApplicationContext 获取失败!");
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        return (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
    }

    public static void clearHolder() {
        applicationContext = null;
    }

}
