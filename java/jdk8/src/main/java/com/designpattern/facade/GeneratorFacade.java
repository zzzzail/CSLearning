package com.designpattern.facade;

/**
 * @author zail
 * @date 2022/5/26
 */
public class GeneratorFacade {
    
    public void gen() {
        gen(true, true, true);
    }
    
    /**
     * 外观模式的核心方法
     *
     * @param presentation 是否生成表现层
     * @param business     是否生成业务层
     * @param dao          是否生成 DAO 层
     */
    public void gen(boolean presentation, boolean business, boolean dao) {
        if (presentation) {
            new PresentationGenerator().generate();
        }
        if (business) {
            new BusinessGenerator().generate();
        }
        if (dao) {
            new DAOGenerator().generate();
        }
    }
}
