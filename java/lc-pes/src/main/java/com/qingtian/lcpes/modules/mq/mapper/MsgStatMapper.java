package com.qingtian.lcpes.modules.mq.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qingtian.lcpes.base.facade.BasicMapper;
import com.qingtian.lcpes.modules.mq.entity.MsgStatEntity;
import com.qingtian.lcpes.modules.mq.vo.MsgStatVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 消息统计表 Mapper
 * 自定义Mapper方法
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Mapper
public interface MsgStatMapper extends BasicMapper<MsgStatEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    public List<MsgStatEntity> listByPage(IPage<MsgStatEntity> page, @Param(Constants.ENTITY) MsgStatEntity entity);

    /**
     * 根据 4 个参数查找存在的数据
     *
     * @param list
     * @return
     */
    List<MsgStatEntity> listBy4Params(@Param("list") List<MsgStatVO> list);

    /**
     * 统计
     *
     * @param platformType  平台类型
     * @param messageStatus 消息状态
     * @param dateNumBegin  时间开始数
     * @param dateNumEnd    时间结束数
     * @return
     */
    Long sumNumByPlatformType(@Param("platformType") String platformType,
                              @Param("messageStatus") Integer messageStatus,
                              @Param("dateNumBegin") Long dateNumBegin,
                              @Param("dateNumEnd") Long dateNumEnd);
}
