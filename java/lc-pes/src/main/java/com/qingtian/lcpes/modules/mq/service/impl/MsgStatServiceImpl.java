package com.qingtian.lcpes.modules.mq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.util.StringUtil;
import com.qingtian.lcpes.base.facade.BaseServiceImpl;
import com.qingtian.lcpes.base.util.BeanCopyUtils;
import com.qingtian.lcpes.modules.mq.bo.MsgStatBO;
import com.qingtian.lcpes.modules.mq.entity.MsgStatEntity;
import com.qingtian.lcpes.modules.mq.mapper.MsgStatMapper;
import com.qingtian.lcpes.modules.mq.service.MsgStatService;
import com.qingtian.lcpes.modules.mq.vo.MsgStatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 消息统计表 服务实现类
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Service
public class MsgStatServiceImpl extends BaseServiceImpl<MsgStatMapper, MsgStatEntity> implements MsgStatService {

    @Autowired
    private MsgStatMapper msgStatMapper;

    @Override
    public IPage<MsgStatEntity> listByPage(IPage<MsgStatEntity> page, MsgStatEntity entity) {
        return page.setRecords(getBasicMapper().listByPage(page, entity));
    }

    @Override
    public List<MsgStatVO> listByBO(MsgStatBO bo) {
        if (bo.getStartTime() != null && bo.getDateNumStart() == null) {
            bo.setDateNumStart(Long.valueOf(bo.getStartTime().format(DateTimeFormatter.ofPattern(MsgStatBO.HOUR_DNF_PATTERN_4J))));
        }
        if (bo.getEndTime() != null && bo.getDateNumEnd() == null) {
            bo.setDateNumEnd(Long.valueOf(bo.getEndTime().format(DateTimeFormatter.ofPattern(MsgStatBO.HOUR_DNF_PATTERN_4J))));
        }

        LambdaQueryWrapper<MsgStatEntity> qw = new LambdaQueryWrapper<>();
        if (StringUtil.isNotEmpty(bo.getPlatformType())) {
            qw.eq(MsgStatEntity::getPlatformType, bo.getPlatformType());
        }
        if (bo.getMessageStatus() != null) {
            qw.eq(MsgStatEntity::getMessageStatus, bo.getMessageStatus());
        }
        if (bo.getDateNumStart()!= null && bo.getDateNumEnd() != null) {
            qw.between(MsgStatEntity::getDateNum, bo.getDateNumStart(), bo.getDateNumEnd());
        }
        List<MsgStatEntity> list = this.list(qw);
        return BeanCopyUtils.copyList(list, MsgStatVO.class);
    }

    @Override
    public MsgStatEntity add(MsgStatEntity entity) {
        return add(entity.getPlatformType(),
                entity.getStatisticsType(),
                entity.getMessageStatus(),
                entity.getDateNum(),
                entity.getNum());
    }

    @Override
    public MsgStatEntity add(String platformType, String statisticsType, Integer messageStatus, Long dateNum, Long addNum) {
        QueryWrapper<MsgStatEntity> qw = new QueryWrapper<>();
        qw.lambda()
                .eq(MsgStatEntity::getPlatformType, platformType)
                .eq(MsgStatEntity::getStatisticsType, statisticsType)
                .eq(MsgStatEntity::getMessageStatus, messageStatus)
                .eq(MsgStatEntity::getDateNum, dateNum);
        MsgStatEntity entity = this.getOne(qw);
        if (entity == null) {
            entity = new MsgStatEntity();
            entity.setPlatformType(platformType);
            entity.setStatisticsType(statisticsType);
            entity.setMessageStatus(messageStatus);
            entity.setDateNum(dateNum);
            entity.setNum(addNum);
            this.save(entity);
        }
        else {
            entity.setNum(entity.getNum() + addNum);
            this.saveOrUpdate(entity);
        }
        return entity;
    }

    @Override
    public MsgStatEntity updateNum(MsgStatEntity entity) {
        MsgStatEntity exist = null;
        if (entity.getSid() != null) {
            exist = getById(entity.getSid());
        }
        else {
            QueryWrapper<MsgStatEntity> qw = new QueryWrapper<>();
            qw.lambda()
                    .eq(MsgStatEntity::getPlatformType, entity.getPlatformType())
                    .eq(MsgStatEntity::getStatisticsType, entity.getStatisticsType())
                    .eq(MsgStatEntity::getMessageStatus, entity.getMessageStatus())
                    .eq(MsgStatEntity::getDateNum, entity.getDateNum());
            exist = this.getOne(qw);
        }

        if (exist == null) {
            exist = new MsgStatEntity();
            exist.setPlatformType(entity.getPlatformType());
            exist.setStatisticsType(entity.getStatisticsType());
            exist.setMessageStatus(entity.getMessageStatus());
            exist.setDateNum(entity.getDateNum());
            exist.setNum(entity.getNum());
            this.save(exist);
        }
        else {
            exist.setNum(entity.getNum());
            this.saveOrUpdate(exist);
        }
        return exist;
    }

    @Override
    public Long sumNumBy(String platformType, Integer messageStatus, LocalDateTime timeBegin, LocalDateTime timeEnd) {
        Long dateNumBegin = null;
        Long dateNumEnd = null;
        if (timeBegin != null) {
            dateNumBegin = Long.valueOf(timeBegin.format(DateTimeFormatter.ofPattern(MsgStatBO.HOUR_DNF_PATTERN_4J)));
        }
        if (timeEnd!= null) {
            dateNumEnd = Long.valueOf(timeEnd.format(DateTimeFormatter.ofPattern(MsgStatBO.HOUR_DNF_PATTERN_4J)));
        }
        return msgStatMapper.sumNumByPlatformType(platformType, messageStatus, dateNumBegin, dateNumEnd);
    }

    @Override
    public List<MsgStatVO> saveOrUpdateAll(List<MsgStatVO> list) {
        if (list == null || list.size() == 0) return list;
        // 有 sid 的
        List<MsgStatVO> hasSid = new LinkedList<>();
        // 没有 sid 的
        List<MsgStatVO> noSid = new LinkedList<>();
        for (MsgStatVO vo : list) {
            if (vo.getSid() != null) hasSid.add(vo);
            else noSid.add(vo);
        }
        // 有 sid 的直接保存
        List<MsgStatEntity> hasSidEntity = BeanCopyUtils.copyList(hasSid, MsgStatEntity.class);
        if (hasSidEntity.size() > 0) { this.saveOrUpdateBatch(hasSidEntity); }

        // 如果没有数据就不继续往下走了
        if (noSid.size() == 0) return list;

        Map<String, MsgStatEntity> map = new HashMap<>();
        for (MsgStatVO vo : noSid) {
            String key = vo.getPlatformType() + "-" + vo.getStatisticsType() + "-" + vo.getMessageStatus() + "-" + vo.getDateNum();
            MsgStatEntity e = new MsgStatEntity();
            BeanCopyUtils.copy(vo, e);
            map.put(key, e);
        }
        // 根据 平台分类、统计类型、消息状态、时间数 查找
        List<MsgStatEntity> exists = msgStatMapper.listBy4Params(noSid);
        for (MsgStatEntity e : exists) {
            String key = e.getPlatformType() + "-" + e.getStatisticsType() + "-" + e.getMessageStatus() + "-" + e.getDateNum();
            if (map.containsKey(key)) {
                // 更新 num
                e.setNum(map.get(key).getNum());
                map.remove(key);
            }
        }
        if (exists.size() > 0) { this.updateBatchById(exists); }
        // 需要插入的数据
        List<MsgStatEntity> needSave = new LinkedList<>();
        for (MsgStatVO vo : noSid) {
            String key = vo.getPlatformType() + "-" + vo.getStatisticsType() + "-" + vo.getMessageStatus() + "-" + vo.getDateNum();
            if (map.containsKey(key)) {
                MsgStatEntity e = new MsgStatEntity();
                BeanCopyUtils.copy(vo, e);
                needSave.add(e);
            }
        }
        if (needSave.size() > 0) { this.saveBatch(needSave); }

        return list;
    }
}
