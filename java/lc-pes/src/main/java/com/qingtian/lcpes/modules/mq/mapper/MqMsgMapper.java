package com.qingtian.lcpes.modules.mq.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qingtian.lcpes.base.facade.BasicMapper;
import com.qingtian.lcpes.modules.mq.bo.MsgStatCountBO;
import com.qingtian.lcpes.modules.mq.entity.MqMsgEntity;
import com.qingtian.lcpes.modules.mq.vo.MsgStatCountVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 消息表 Mapper
 * 自定义Mapper方法
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Mapper
public interface MqMsgMapper extends BasicMapper<MqMsgEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    public List<MqMsgEntity> listByPage(IPage<MqMsgEntity> page, @Param(Constants.ENTITY) MqMsgEntity entity);

    /**
     * 消息统计
     *
     * @param bo
     * @return
     */
    List<MsgStatCountVO> countByBO(@Param("bo") MsgStatCountBO bo);

    /**
     * 统计消息数量
     *
     * @return
     */
    Long countMsgNumByBO(@Param("bo") MsgStatCountBO bo);
}
