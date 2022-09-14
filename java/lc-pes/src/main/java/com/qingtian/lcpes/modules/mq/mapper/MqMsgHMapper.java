package com.qingtian.lcpes.modules.mq.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qingtian.lcpes.base.facade.BasicMapper;
import com.qingtian.lcpes.modules.mq.bo.MsgStatCountBO;
import com.qingtian.lcpes.modules.mq.entity.MqMsgHEntity;
import com.qingtian.lcpes.modules.mq.vo.MsgStatCountVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 消息历史表 Mapper
 * 自定义Mapper方法
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Mapper
public interface MqMsgHMapper extends BasicMapper<MqMsgHEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    public List<MqMsgHEntity> listByPage(IPage<MqMsgHEntity> page, @Param(Constants.ENTITY) MqMsgHEntity entity);

    List<MsgStatCountVO> countByBO(MsgStatCountBO bo);

    Long countMsgNumByBO(MsgStatCountBO bo);

}
