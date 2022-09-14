package com.qingtian.lcpes.modules.mq.util;

import cn.hutool.core.text.UnicodeUtil;
import com.qingtian.lcpes.base.util.JsonUtil;
import com.qingtian.lcpes.modules.mq.jmsg.IXBUSMsgBuilder;
import com.qingtian.lcpes.modules.mq.jmsg.MsgHeaderDto;
import com.qingtian.lcpes.modules.mq.jmsg.NodeInfo;
import com.qingtian.lcpes.modules.mq.jmsg.TablesDto;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <?xml version="1.0" encoding="utf-8"?>
 * <PE_W_07 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation=" ">
 * <messageHeader>
 * <messageTypeId>PE_W_07</messageTypeId>
 * <messageId></messageId>
 * <correlationMessageTypeId></correlationMessageTypeId>
 * <correlationMessageId></correlationMessageId>
 * <sendDate></sendDate>
 * <sendTime></sendTime>
 * <sender></sender>
 * <receiver></receiver>
 * <length>-1</length>
 * <lineCode></lineCode>
 * <telegramId></telegramId>
 * <rc></rc>
 * <receivedAppId></receivedAppId>
 * <spare></spare>
 * </messageHeader>
 *
 * <TEL_HEAD></TEL_HEAD>
 * <PROC_CD>DJ1</PROC_CD>
 * <WK_QTY>20</WK_QTY>
 * <PLAN_NO>DJ1</PLAN_NO>
 * <TEL_TAIL></TEL_TAIL>
 * <WAREHOUSE_ID>C81</WAREHOUSE_ID>
 *
 * <CIRCLE>
 * <CIRCLE_ITEM>
 * <MTRL_NO>SE122416203800</MTRL_NO>
 * <SUPPLY_SEQ_NO>11802</SUPPLY_SEQ_NO>
 * </CIRCLE_ITEM>
 * <CIRCLE_ITEM>
 * <MTRL_NO>SF122414400200</MTRL_NO>
 * <SUPPLY_SEQ_NO>11801</SUPPLY_SEQ_NO>
 * </CIRCLE_ITEM>
 * </CIRCLE>
 *
 *
 *
 * </PE_W_07>
 * <p>
 * <p>
 * <?xml version="1.0" encoding="ISO-8859-1"?>
 * <W_PE_03 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation=" ">
 * <messageHeader>
 * <lineCode>HEAD</lineCode>
 * <length>00240</length>
 * <messageTypeId>W_PE_03</messageTypeId>
 * <rc>0001</rc>
 * <sendDate>02022422</sendDate>
 * <sendTime>00185900</sendTime>
 * <sender>DK1</sender>
 * <receiver>PES</receiver>
 * <spare>SEND</spare>
 * </messageHeader>
 * <WH_CD>41</WH_CD>
 * <CAR_NO>冀F00991</CAR_NO>
 * <WK_STA_DTM>20220422185900</WK_STA_DTM>
 * <WK_END_DTM>20220422185900</WK_END_DTM>
 * <OPER_USER_ID>TrackServer</OPER_USER_ID>
 * <WK_DTM>20220422185900</WK_DTM>
 * <MTRL_NO_01>H522443910300</MTRL_NO_01>
 * <MTRL_WGT_01>0</MTRL_WGT_01>
 * <LOAD_POS_01>04</LOAD_POS_01>
 * <SLOT_ID>41-B40-06</SLOT_ID>
 * </W_PE_03>
 */
public final class XmlUtil {

    static String MSG_HEADER = "messageHeader";
    static String MSG_TYPEID = "messageTypeId";
    static String MSG_ID = "messageId";
    static String MSG_CIRCLE = "CIRCLE";
    static String MSG_CIRCLE_ITEM = "CIRCLE_ITEM";
    static String MSG_WAREHOUSE_ID = "WAREHOUSE_ID";

    static String MSG_UNICODE = "#CAR_NO#YS_RESON#EMP_NAME#QD_STATE#";

    static List<String> seqList = Arrays.asList("WAREHOUSE_ID", "WAYDILL_NO", "CAR_NO", "EMP_NAME", "TEL", "QD_STATE", "QD_TIME", "TASK_NO", "DOOR_ID", "HALL_ID", "PARK_ID", "PRE_ARRIVE_DT", "ZX_FLAG");

    /**
     * xml转换成nodeinfo, 并截取messageTypeId前8位(去除后缀仓库编码)
     *
     * @param xml
     * @return
     */
    public static String xml2Node(String xml) throws Exception {
        Document document = DocumentHelper.parseText(xml);

        //获取 电文类型、Id信息、循环信息
        Element rootElement = document.getRootElement();
        Element msgTypeIdEle = (Element) rootElement.selectSingleNode(MSG_HEADER + "/" + MSG_TYPEID);
        Element msgIdEle = (Element) rootElement.selectSingleNode(MSG_HEADER + "/" + MSG_ID);
        Element circleEle = (Element) rootElement.selectSingleNode(MSG_CIRCLE);
        boolean isCircle = circleEle != null && circleEle.elements().size() > 0;
        if (msgTypeIdEle == null || StringUtils.isEmpty(msgTypeIdEle.getText())) {
            throw new Exception("messageTypeId信息为空");
        }
        String msgId = "";
        if (msgIdEle == null || StringUtils.isEmpty(msgIdEle.getText())) {
            msgId = DigestUtils.md5DigestAsHex(xml.getBytes());
        }
        else {
            msgId = msgIdEle.getText();
        }
        String msgTypeId = msgTypeIdEle.getText();//.substring(0, Math.min(8, msgTypeIdEle.getText().length()));
        IXBUSMsgBuilder builder = new IXBUSMsgBuilder(msgTypeId, msgId, "", isCircle);

        List<Element> elements = rootElement.elements();
        for (Element ele : elements) {
            if (MSG_HEADER.equals(ele.getName())) {//消息头
                continue;
            }
            else if (MSG_CIRCLE.equals(ele.getName())) {//CIRCLE
                List<Element> itemLst = ele.selectNodes(MSG_CIRCLE_ITEM);
                builder = populateCircle(builder, itemLst);
            }
            else {//BODY
                builder.body(ele.getName(), ele.getName(), "C", formatUnicodeStr(ele.getText()));
            }
        }

        return builder.json();
    }

    /**
     * nodeinfo转换成xml, 并修改messageTypeId + 仓库编码后缀(取messageHeader中svcName)
     *
     * @param nodeInfo
     * @return
     */
    public static String node2Xml(NodeInfo nodeInfo) throws Exception {
        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding("UTF-8");

        //add root element
        MsgHeaderDto messageHeader = nodeInfo.getMessageHeader();
        String messageTypeId = messageHeader.getMessageTypeId();
        Element rootElement = document.addElement(messageTypeId);
        rootElement.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        rootElement.addAttribute("xsi:schemaLocation", " ");

        //add messageHeader
        Element headerEle = rootElement.addElement(MSG_HEADER);
        headerEle.addElement(MSG_TYPEID).setText(messageTypeId);
        headerEle.addElement(MSG_ID).setText(messageHeader.getMessageId());
        headerEle.addElement("lineCode");
        headerEle.addElement("sendDate").setText(formatNullStr(messageHeader.getSendDate()));
        headerEle.addElement("sendTime").setText(formatNullStr(messageHeader.getSendTime()));
        headerEle.addElement("sender").setText(formatNullStr(messageHeader.getSender()));
        headerEle.addElement("receiver").setText(formatNullStr(messageHeader.getReceiver()));
        headerEle.addElement("length").setText("-1");
        headerEle.addElement("telegramId");
        headerEle.addElement("rc");
        headerEle.addElement("receivedAppId");
        headerEle.addElement("spare");

        //add body
        TablesDto body = nodeInfo.getTables().get(0);
        for (Map<String, Object> map : body.getRows()) {
            for (String str : seqList) {
                if (map.containsKey(str)) {
                    String text = toUnicode(str, formatNullStr(map.get(str)));
                    rootElement.addElement(str).setText(text);
                }
            }
//            //add body -> WAREHOUSE_ID, first
//            if (map.containsKey(MSG_WAREHOUSE_ID)){
//                rootElement.addElement(MSG_WAREHOUSE_ID).setText(formatNullStr(map.get(MSG_WAREHOUSE_ID)));
//            }
//            for (Map.Entry<String, Object> entry:map.entrySet()) {
//                if(MSG_WAREHOUSE_ID.equals(entry.getKey())) {
//                    continue;
//                }
//                String text = toUnicode( entry.getKey(), formatNullStr(entry.getValue()));
//                rootElement.addElement(entry.getKey()).setText(text);
//            }
        }

        //add circle
        if (nodeInfo.getTables().size() > 1) {
            TablesDto circle = nodeInfo.getTables().get(1);
            Element circleRoot = rootElement.addElement(MSG_CIRCLE);
            for (Map<String, Object> map : circle.getRows()) {
                Element circleEle = circleRoot.addElement(MSG_CIRCLE_ITEM);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String text = toUnicode(entry.getKey(), formatNullStr(entry.getValue()));
                    circleEle.addElement(entry.getKey()).setText(text);
                }
            }
        }

        return document.asXML();
    }

    private static IXBUSMsgBuilder populateCircle(IXBUSMsgBuilder builder, List<Element> items) {
        for (Element ele : items) {//<DOWNTIME></DOWNTIME>
            builder.newCircle();
            for (Element e : (List<Element>) ele.elements()) {
                builder.circle(e.getName(), e.getName(), "C", formatUnicodeStr(e.getText()));
            }
        }
        return builder;
    }

    private static String formatNullStr(Object val) {
        return val == null ? "" : val.toString();
    }

    private static String formatUnicodeStr(String val) {
        return StringUtils.isEmpty(val) ? "" : UnicodeUtil.toString(val.replace("\\\\", "\\"));
    }

    private static String toUnicode(String name, String val) {
        if (MSG_UNICODE.indexOf("#" + name + "#") < 0) {
            return val;
        }
        else {
            return UnicodeUtil.toUnicode(val, false);//ASCII也转
        }
    }

    public static void main(String[] args) throws Exception {

        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<W1125_01C58 xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\" \">\n" + "  <messageHeader>\n" + "    <messageTypeId>W1125_01</messageTypeId>\n" + "    <messageId>1111111111</messageId>\n" + "    <correlationMessageTypeId></correlationMessageTypeId>\n" + "    <correlationMessageId></correlationMessageId>\n" + "    <sendDate></sendDate>\n" + "    <sendTime></sendTime>\n" + "    <sender></sender>\n" + "    <receiver></receiver>\n" + "    <length>-1</length>\n" + "    <lineCode></lineCode>\n" + "    <telegramId></telegramId>\n" + "    <rc></rc>\n" + "    <receivedAppId></receivedAppId>\n" + "    <spare></spare>\n" + "  </messageHeader>\n" + "\n" + "  <TEL_HEAD></TEL_HEAD>\n" + "  <CAR_NO>\\\\u5180\\\\u0047\\u0048\\u0031\\u0032\\u0035\\u0038</CAR_NO>\n" + "  <WK_QTY>\\u5180</WK_QTY>\n" + "  <WAREHOUSE_ID>\\u5180</WAREHOUSE_ID>\n" + "  <PLAN_NO>DJ1</PLAN_NO>\n" + "  <WAYDILL_NO>xxxxxx</WAYDILL_NO>\n" + "  <TEL_TAIL></TEL_TAIL>\n" + "\n" + "  <CIRCLE>\n" + "    <CIRCLE_ITEM>\n" + "      <MTRL_NO>SE122416203800</MTRL_NO>\n" + "      <SUPPLY_SEQ_NO>11802</SUPPLY_SEQ_NO>\n" + "    </CIRCLE_ITEM>\n" + "    <CIRCLE_ITEM>\n" + "      <MTRL_NO>SF122414400200</MTRL_NO>\n" + "      <SUPPLY_SEQ_NO>11801</SUPPLY_SEQ_NO>\n" + "    </CIRCLE_ITEM>\n" + "  </CIRCLE>\n" + "  <WAREHOUSE_ID>C81</WAREHOUSE_ID>\n" + "\n" + "\n" + "\n" + "</W1125_01C58>\n";

        String nodeInfo = xml2Node(xml);
        System.out.println(nodeInfo);
        NodeInfo nodeInfo1 = JsonUtil.fromJson(nodeInfo, NodeInfo.class);
        nodeInfo1.getMessageHeader().setSvcName("C81");
        System.out.println(node2Xml(nodeInfo1));
//        System.out.println("W1125_01C58".substring(0,8));
    }
}
