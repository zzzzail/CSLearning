package com.qingtian.lcpes.base.facade;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class BaseVO implements Serializable {

    @ModelField(fieldDesc = "创建人ID")
    @TableField("CREATED_BY")
    private String createdBy;

    @ModelField(fieldDesc = "创建人名称")
    @TableField("CREATED_BY_NAME")
    private String createdByName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ModelField(fieldDesc = "创建时间")
    @TableField(value = "CREATED_DT", fill = FieldFill.INSERT)
    private LocalDateTime createdDt;

    @ModelField(fieldDesc = "更新人ID")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ModelField(fieldDesc = "更新人名称")
    @TableField("UPDATED_BY_NAME")
    private String updatedByName;

    @ModelField(fieldDesc = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "UPDATED_DT", fill = FieldFill.UPDATE)
    private LocalDateTime updatedDt;

    @ModelField(fieldDesc = "公司别主键")
    private String companySid;

    @ModelField(fieldDesc = "公司别编码")
    private String companyCode;

    @ModelField(fieldDesc = "公司别名称")
    private String companyName;

    private int pageNum = 1;

    private int pageSize = 10;

    private String columnName;

    private Boolean isAsc;

    private Map<String, Object> customizeMap = new HashMap();

    public BaseVO() {
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDateTime getCreatedDt() {
        return this.createdDt;
    }

    public void setCreatedDt(LocalDateTime createdDt) {
        this.createdDt = createdDt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDateTime getUpdatedDt() {
        return this.updatedDt;
    }

    public void setUpdatedDt(LocalDateTime updatedDt) {
        this.updatedDt = updatedDt;
    }

    public String getCompanySid() {
        return this.companySid;
    }

    public void setCompanySid(String companySid) {
        this.companySid = companySid;
    }

    public String getCompanyCode() {
        return this.companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Boolean getIsAsc() {
        return this.isAsc;
    }

    public void setIsAsc(Boolean isAsc) {
        this.isAsc = isAsc;
    }

    public Boolean getAsc() {
        return this.isAsc;
    }

    public void setAsc(Boolean asc) {
        this.isAsc = asc;
    }

    public Map<String, Object> getCustomizeMap() {
        return this.customizeMap;
    }

    public void setCustomizeMap(Map<String, Object> customizeMap) {
        this.customizeMap = customizeMap;
    }
}
