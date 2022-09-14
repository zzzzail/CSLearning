package com.qingtian.lcpes.modules.mq.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qingtian.lcpes.base.facade.BasicMapper;
import com.qingtian.lcpes.modules.mq.entity.MqWbqueLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 运单消息队列日志表 Mapper
 * 自定义Mapper方法
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Mapper
public interface MqWbqueLogMapper extends BasicMapper<MqWbqueLogEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    public List<MqWbqueLogEntity> listByPage(IPage<MqWbqueLogEntity> page, @Param(Constants.ENTITY) MqWbqueLogEntity entity);

    /**
     * 按照 messageId 查找数据
     *
     * @param messageId
     * @return
     */
    MqWbqueLogEntity findOneByMessageId(@Param("messageId") String messageId);

    /**
     * 取消删除
     *
     * @param sid
     */
    void unremove(@Param("sid") Long sid);
}
