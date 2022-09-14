package com.qingtian.lcpes.modules.mq.jmsg;

import com.qingtian.lcpes.base.util.DateUtils;
import com.qingtian.lcpes.base.util.JsonUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author even
 * @date 2022/5/24
 */
public class IXBUSMsgBuilder {
    private final NodeInfo nodeInfo;
    private final boolean isCircle;
    private static final String MAIN_NAME = "BODY";
    private static final String CIRCLE_NAME = "CIRCLE";
    private static final int MAIN_INDEX = 0;
    private static final int CIRCLE_INDEX = 1;

    public IXBUSMsgBuilder(String messageTypeId, String messageId){
        this(messageTypeId,messageId,"sender",false);
    }
    public IXBUSMsgBuilder(String messageTypeId, String messageId,String sender){
        this(messageTypeId,messageId,sender,false);
    }

    public IXBUSMsgBuilder(String messageTypeId, String messageId,String sender,boolean isCircle) {
        this(messageTypeId,messageId,sender,isCircle,null);
    }

    public IXBUSMsgBuilder(String messageTypeId, String messageId,String sender,boolean isCircle,String svcName) {
        Date date = new Date();
        String sendDate = DateUtils.DateToStryyyyMMdd(date);
        String sendTime = DateUtils.getTimeShort().replace(":","");
        this.isCircle = isCircle;
        nodeInfo = new NodeInfo();
        //消息头
        MsgHeaderDto header = new MsgHeaderDto();
        header.setMessageTypeId(messageTypeId);
        header.setMessageId(messageId);
        header.setSender(sender);
        header.setReceiver("");
        header.setFlag("");
        header.setFlag("");
        header.setSendDate(sendDate);
        header.setSendTime(sendTime);
        if (svcName != null){
            header.setSvcName(svcName);
        }else {
            header.setSvcName("");
        }

        nodeInfo.setMessageHeader(header);
        //消息主体
        ArrayList<TablesDto> tables = new ArrayList<>(1);
        TablesDto body = new TablesDto();
        body.setName(MAIN_NAME);
        body.setColumns(new ArrayList<>());
        body.setRows(new ArrayList<>());
        body.getRows().add(new HashMap<>());
        tables.add(body);
        if(isCircle) {
            //消息循环体
            TablesDto circle = new TablesDto();
            circle.setName(CIRCLE_NAME);
            circle.setColumns(new ArrayList<>());
            circle.setRows(new ArrayList<>());
            tables.add(circle);
        }
        nodeInfo.setTables(tables);
    }

    public NodeInfo getNodeInfo(){
        return nodeInfo;
    }

    public String json(){
        return JsonUtil.toJson(nodeInfo);
    }

    /**
     * @param name     - 字段名
     * @param caption  - 字段描述
     * @param dataType - 字段类型
     * @param val      - 字段值
     * @return
     */
    public IXBUSMsgBuilder body(String name,
                              String caption,
                              String dataType,
                              Object val) {
        return addData(MAIN_INDEX,name,caption,dataType,val);
    }

    public IXBUSMsgBuilder body(String name,Object val){
        return body(name,name,"S",val);
    }

    /**
     * 增加新的字表数据
     * @return
     */
    public IXBUSMsgBuilder newBody(){
        if (this.nodeInfo.getTables().get(MAIN_INDEX).getRows().size() == 1
                && this.nodeInfo.getTables().get(MAIN_INDEX).getRows().get(0).isEmpty()) {
            return this;
        }

        this.nodeInfo.getTables().get(MAIN_INDEX).getRows().add(new HashMap<>());
        return this;
    }


    public IXBUSMsgBuilder circle(String name,
                                     String caption,
                                     String dataType,
                                     Object val) {
        return addData(CIRCLE_INDEX,name,caption,dataType,val);
    }

    public IXBUSMsgBuilder circle(String name,Object val){
        return circle(name,name,"S",val);
    }

    /**
     * 增加新的字表数据
     * @return
     */
    public IXBUSMsgBuilder newCircle(){
        if(!this.isCircle){
            throw new IllegalStateException("isCircle == false");
        }
        this.nodeInfo.getTables().get(CIRCLE_INDEX).getRows().add(new HashMap<>());
        return this;
    }

    /**
     * @param tableIdx   - 索引号
     * @param name      - 字段名
     * @param caption    - 字段描述
     * @param dataType - 字段类型
     * @param val         - 字段值
     * @return
     */
    private IXBUSMsgBuilder addData(int tableIdx, String name, String caption, String dataType, Object val) {
        TablesDto table = this.nodeInfo.getTables().get(tableIdx);
        if(!hasColumn(table.getColumns(),name)){
            table.getColumns().add(newColumn(name,caption,dataType));
        }
        if(table.getRows().isEmpty()){
            table.getRows().add(new HashMap<>());
        }
        table.getRows().get(table.getRows().size() - 1).put(name, val);
        return this;
    }

    private ColumnsInfo newColumn(String name,String caption,String dataType){
        ColumnsInfo info = new ColumnsInfo();
        info.setName(name);
        info.setCaption(caption);
        info.setDataType(dataType);
        return info;
    }

    private boolean hasColumn(List<ColumnsInfo> columnsInfos, String name){
        if(columnsInfos == null || columnsInfos.isEmpty()){
            return false;
        }
        for(int i=0;i<columnsInfos.size();i++){
            if(name.equals(columnsInfos.get(i).getName())){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        //单表消息
        IXBUSMsgBuilder singleBuilder = new IXBUSMsgBuilder("Test1","1111","ITMPGIS01");
        String json1 = singleBuilder.body("F1","111")
                .body("F2","字段2","I",23)
                .body("F3","2022-05-23")
                .json();
        System.out.println(json1);
        //主子表消息
        IXBUSMsgBuilder multiBuilder = new IXBUSMsgBuilder("Test2","2222","ITMPGIS01",true,null);
        String json2 = multiBuilder.body("F1","111")
                .body("F2","字段2","I",23)
                .body("F3","2022-05-23")
                .newCircle()
                .circle("S1","子表字段1","S","S1111")
                .circle("S2","子表字段2","S","2022-05-23 12:22:12")
                .newCircle()
                .circle("S1","子表字段1","S","S222")
                .circle("S2","子表字段2","S","2022-05-24 12:22:12")
                .newCircle()
                .circle("S1","子表字段1","S","S333")
                .circle("S2","子表字段2","S","2022-05-26 12:22:12")
                .json();
        System.out.println(json2);
    }
}
