package com.qingtian.lcpes.modules.mq.converter;


import com.github.pagehelper.util.StringUtil;
import com.qingtian.lcpes.modules.mq.entity.MsgStatEntity;
import com.qingtian.lcpes.modules.mq.enums.MsgStatPlatformTypeEnum;
import com.qingtian.lcpes.modules.mq.vo.MsgStatCountVO;
import com.qingtian.lcpes.modules.mq.vo.MsgStatVO;
import com.qingtian.lcpes.modules.mq.vo.WbAlgMsgLogStatVO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangxq
 * @date 2022/8/25
 */
public class MsgStatConverter {

    public static MsgStatEntity msgStatCountVO2MsgStatEntity(MsgStatCountVO vo) {
        MsgStatEntity res = new MsgStatEntity();
        res.setPlatformType(MsgStatPlatformTypeEnum.MSG.getCode());
        res.setStatisticsType(vo.getMsgSender() + "-" + vo.getMsgReceiver());
        Integer status = vo.getMsgState() == 2 ? 0 : 1;
        res.setMessageStatus(status);
        res.setDateNum(vo.getDateNum());
        res.setNum(vo.getCount());
        return res;
    }

    public static MsgStatVO msgStatCountVO2MsgStatVO(MsgStatCountVO vo) {
        MsgStatVO res = new MsgStatVO();
        res.setPlatformType(MsgStatPlatformTypeEnum.MSG.getCode());
        res.setStatisticsType(vo.getMsgSender() + "-" + vo.getMsgReceiver());
        Integer status = vo.getMsgState() == 2 ? 0 : 1;
        res.setMessageStatus(status);
        res.setDateNum(vo.getDateNum());
        res.setNum(vo.getCount());
        return res;
    }

    public static MsgStatEntity wbAlgMsgLogStatVO2MsgStatEntity(WbAlgMsgLogStatVO vo) {
        MsgStatEntity res = new MsgStatEntity();
        res.setPlatformType(MsgStatPlatformTypeEnum.ALG.getCode());
        res.setStatisticsType(MsgStatVO.MSG_ALL_STR);
        res.setMessageStatus(vo.getMsgStatus() == 1 ? 1 : 0);
        res.setDateNum(vo.getDateNum());
        res.setNum(vo.getCount());
        return res;
    }

    public static MsgStatVO wbAlgMsgLogStatVO2MsgStatVO(WbAlgMsgLogStatVO vo) {
        MsgStatVO res = new MsgStatVO();
        res.setPlatformType(MsgStatPlatformTypeEnum.ALG.getCode());
        res.setStatisticsType(MsgStatVO.MSG_ALL_STR);
        res.setMessageStatus(vo.getMsgStatus() == 1 ? 1 : 0);
        res.setDateNum(vo.getDateNum());
        res.setNum(vo.getCount());
        return res;
    }


    public static List<MsgStatVO> msgStatCountVOs2MsgStatVOs(List<MsgStatCountVO> list) {
        List<MsgStatVO> res = new LinkedList<>();
        if (list == null || list.size() == 0) return res;
        for (MsgStatCountVO vo : list) {
            if (vo.getMsgState() == null) continue;
            if (vo.getMsgState() != 2 && vo.getMsgState() != 3) continue;
            if (StringUtil.isEmpty(vo.getMsgSender()) || StringUtil.isEmpty(vo.getMsgReceiver())) continue;
            if (vo.getCount() == 0) continue;

            res.add(msgStatCountVO2MsgStatVO(vo));
        }
        return res;
    }

    public static List<MsgStatVO> wbAlgMsgLogStatVOs2MsgStatVOs(List<WbAlgMsgLogStatVO> list) {
        List<MsgStatVO> res = new ArrayList<>(list.size());
        for (WbAlgMsgLogStatVO vo : list) {
            res.add(wbAlgMsgLogStatVO2MsgStatVO(vo));
        }
        return res;
    }

    public static List<MsgStatEntity> msgStatCountVOs2MsgStatEntities(List<MsgStatCountVO> list) {
        List<MsgStatEntity> res = new ArrayList<>(list.size());
        if (list == null || list.size() == 0) return res;
        for (MsgStatCountVO vo : list) {
            if (vo.getMsgState() == null) continue;
            if (vo.getMsgState() != 2 && vo.getMsgState() != 3) continue;
            if (StringUtil.isEmpty(vo.getMsgSender()) || StringUtil.isEmpty(vo.getMsgReceiver())) continue;
            if (vo.getCount() == 0) continue;

            res.add(msgStatCountVO2MsgStatEntity(vo));
        }
        return res;
    }

    public static List<MsgStatEntity> wbAlgMsgLogStatVOs2MsgStatEntities(List<WbAlgMsgLogStatVO> list) {
        List<MsgStatEntity> res = new ArrayList<>(list.size());
        for (WbAlgMsgLogStatVO vo : list) {
            res.add(wbAlgMsgLogStatVO2MsgStatEntity(vo));
        }
        return res;
    }

}
