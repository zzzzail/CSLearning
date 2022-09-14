package com.qingtian.lcpes.base.page;

import cn.hutool.core.util.ReflectUtil;
import com.qingtian.lcpes.base.util.BeanCopyUtils;

import java.util.List;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class PageDTOUtils {

    public static void startPage(Object params) {
        int pageNum = (Integer) ReflectUtil.getFieldValue(params, "pageNum");
        int pageSize = (Integer) ReflectUtil.getFieldValue(params, "pageSize");
        PageDTO<?> pageDTO = new PageDTO<>(pageNum, pageSize);
        PageThreadLocal.init(pageDTO);
    }

    public static void startPage(int pageNum, int pageSize) {
        PageDTO<?> pageDTO = new PageDTO<>(pageNum, pageSize);
        PageThreadLocal.init(pageDTO);
    }

    public static void endPage() {
        PageThreadLocal.remove();
    }

    public static PageDTO<?> getCurrent() {
        return PageThreadLocal.get();
    }

    public static <T> PageDTO<T> transform(List<?> list, Class<T> clazz) {
        if (getCurrent() == null) {
            throw new RuntimeException("请对在 startPage和 endPage之间的dao查询结果进行转换");
        }
        else {
            PageDTO<T> pageDTO = new PageDTO<>(getCurrent().getPageNum(), getCurrent().getPageSize());

            try {
                pageDTO.setTotal(getCurrent().getTotal());
                List<T> resultData = BeanCopyUtils.copyList(getCurrent().getResultData(), clazz);
                pageDTO.setResultData(resultData);
                endPage();
            }
            catch (Exception var7) {
                var7.printStackTrace();
            }
            finally {
                endPage();
            }

            return pageDTO;
        }
    }

    public static <T> PageDTO<T> transform(List<T> list) {
        if (getCurrent() == null) {
            throw new RuntimeException("请对在 startPage和 endPage之间的dao查询结果进行转换");
        }
        else {
            PageDTO<T> pageDTO = new PageDTO<>(getCurrent().getPageNum(), getCurrent().getPageSize());

            try {
                pageDTO.setTotal(getCurrent().getTotal());
                pageDTO.setResultData(list);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                endPage();
            }

            return pageDTO;
        }
    }
}
