package com.qingtian.lcpes.modules.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qingtian.lcpes.base.facade.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据字典表
 * </p>
 *
 * @author zhangxq
 * @since 2022-08-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysDictDataEntity vo对象", description = "数据字典表")
public class SysDictDataVO extends BaseVO {

    @ApiModelProperty(value = "SEQUENCE")
    private Long typeSid;

    @ApiModelProperty(value = "父节点ID")
    private Long parentSid;

    @ApiModelProperty(value = "类型ID")
    private String typeCode;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "类型描述")
    private String typeDesc;

    @ApiModelProperty(value = "顺序号")
    private Integer typeSeq;

    @ApiModelProperty(value = "类型级别")
    private Integer typeLevel;

    @ApiModelProperty(value = "预留字段")
    private String backup1;

    @ApiModelProperty(value = "预留字段")
    private String backup2;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDt;

    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    @ApiModelProperty(value = "更新人名称")
    private String updatedByName;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "公司别主键")
    private String companySid;

    @ApiModelProperty(value = "公司别编码")
    private String companyCode;

    @ApiModelProperty(value = "公司别名称")
    private String companyName;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedDt;

    /* 子数据 */
    private List<SysDictDataVO> children = new ArrayList<>();

    private String opt;

    private String typeValue;

}
