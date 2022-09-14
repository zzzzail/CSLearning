package com.qingtian.lcpes.base.page;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class PageThreadLocal {
    static ThreadLocal<PageDTO<?>> threadlocal = new ThreadLocal<>();

    public PageThreadLocal() {
    }

    public static void init(PageDTO<?> page) {
        threadlocal.set(page);
    }

    public static void remove() {
        threadlocal.remove();
    }

    public static PageDTO<?> get() {
        return threadlocal.get();
    }
}