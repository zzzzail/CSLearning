package com.designpattern.facade;

import lombok.Data;

/**
 * 配置数据的 model
 *
 * @author zail
 * @date 2022/5/26
 */
@Data
public class ConfigModel {
    
    /* 是否需要生成表现层，默认为 true */
    private boolean needGenPresentation = true;
    
    /* 是否需要生成逻辑层，默认为 true */
    private boolean needGenBusiness = true;
    
    /* 是否需要生成 DAO，默认为 true */
    private boolean needGenDAO = true;
    
}
