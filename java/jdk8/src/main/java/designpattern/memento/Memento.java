package designpattern.memento;

import java.util.List;

/**
 * 备忘录接口
 *
 * @author zhangxq
 * @since 2022/9/14
 */
public interface Memento<T> {
    
    /**
     * 获取所有的备忘录
     *
     * @return 备忘录列表
     */
    List<T> mementos();
    
    /**
     * 保存一个阶段的数据
     *
     * @param t 数据
     * @return 保存成功与否，true 为保存成功，false 为保存失败
     */
    boolean save(T t);
    
    /**
     * 提取某个阶段的数据
     *
     * @param phase 阶段数
     * @return 备忘数据，若不存在则为 null
     */
    T retrieve(int phase);
    
    /**
     * 删除某个备忘数据
     *
     * @param t 备忘数据
     * @return 删除成功与否，true 为删除成功，false 为删除失败
     */
    boolean remove(T t);
    
    /**
     * 根据阶段数删除某个备忘数据
     *
     * @param phase 阶段数
     * @return 删除成功与否，true 为删除成功，false 为删除失败
     */
    boolean remove(int phase);
}
