package com.qingtian.lcpes.base.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author zhangxq
 * @since 2022/8/31
 */
public class JsonHeader {
    private String sid;
    private String appId;
    private String appName;
    private String operId;
    private String operTitle;
    private String memId;
    private String acctId;
    private String userId;
    private String bid;
    private String requiestId;
    private String pageId;
    private String controlId;
    private String st;
    private String sv;
    private String longitude;
    private String latitude;
    private String sign;
    private String timestamp;
    private String channelId;

    public JsonHeader() {
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getOperId() {
        return this.operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getOperTitle() {
        return this.operTitle;
    }

    public void setOperTitle(String operTitle) {
        this.operTitle = operTitle;
    }

    public String getMemId() {
        return this.memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getAcctId() {
        return this.acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBid() {
        return this.bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getRequiestId() {
        return this.requiestId;
    }

    public void setRequiestId(String requiestId) {
        this.requiestId = requiestId;
    }

    public String getPageId() {
        return this.pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getControlId() {
        return this.controlId;
    }

    public void setControlId(String controlId) {
        this.controlId = controlId;
    }

    public String getSt() {
        return this.st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getSv() {
        return this.sv;
    }

    public void setSv(String sv) {
        this.sv = sv;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String toString() {
        return JSONObject.toJSONString(this, new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect});
    }
}
