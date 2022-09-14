package com.qingtian.lcpes.modules.mq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingtian.lcpes.base.facade.BaseService;
import com.qingtian.lcpes.modules.mq.bo.MsgStatBO;
import com.qingtian.lcpes.modules.mq.entity.MsgStatEntity;
import com.qingtian.lcpes.modules.mq.vo.MsgStatVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 消息统计表 服务类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
public interface MsgStatService extends BaseService<MsgStatEntity> {
    /**
     * 自定义分页查询
     *
     * @param page
     * @param entity
     * @return
     */
    IPage<MsgStatEntity> listByPage(IPage<MsgStatEntity> page, MsgStatEntity entity);

    /**
     * 根据 BO 查找数据
     *
     * @param bo
     * @return
     */
    List<MsgStatVO> listByBO(MsgStatBO bo);

    /**
     * 增加数量
     *
     * @param entity
     * @return
     */
    MsgStatEntity add(MsgStatEntity entity);

    /**
     * 增加数量
     *
     * @param platformType   平台类型
     * @param statisticsType 统计类型
     * @param messageStatus  消息状态
     * @param dateNum        时间数
     * @param addNum         增加数
     * @return
     */
    MsgStatEntity add(String platformType, String statisticsType, Integer messageStatus, Long dateNum, Long addNum);

    /**
     * 更新数量
     *
     * @param entity
     * @return
     */
    MsgStatEntity updateNum(MsgStatEntity entity);

    /**
     * 统计总数
     *
     * @param platformType  平台类型
     * @param messageStatus 消息状态
     * @param timeBegin     时间开始
     * @param timeEnd       时间结束
     * @return
     */
    Long sumNumBy(String platformType, Integer messageStatus, LocalDateTime timeBegin, LocalDateTime timeEnd);

    /**
     * 批量创建或更新操作
     *
     * @param list VO 列表
     * @return VO 列表
     */
    List<MsgStatVO> saveOrUpdateAll(List<MsgStatVO> list);
}
