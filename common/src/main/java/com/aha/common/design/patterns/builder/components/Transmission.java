package com.aha.common.design.patterns.builder.components;

/**
 * 汽车产品特征 - 3 - 档位类型
 *
 * @author WT
 * @date 2022/01/18 14:54:46
 */
public enum Transmission {

    /**
     * 没有档位，无法调节档位
     */
    SINGLE_SPEED,
    /**
     * 手动
     */
    MANUAL,
    /**
     * 自动
     */
    AUTOMATIC,
    /**
     * 半自动
     */
    SEMI_AUTOMATIC

}
