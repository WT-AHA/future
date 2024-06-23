package com.aha.common.design.patterns.builder.builders;


import com.aha.common.design.patterns.builder.components.Engine;
import com.aha.common.design.patterns.builder.components.GPSNavigator;
import com.aha.common.design.patterns.builder.components.Transmission;
import com.aha.common.design.patterns.builder.components.TripComputer;
import com.aha.common.design.patterns.builder.cars.CarType;

/**
 * 通用生成器接口
 *
 * @author WT
 * @date 2022/01/18 14:26:57
 */
public interface Builder {

    /**
     * 设置汽车的类型
     * @param type 汽车的类型
     */
    Builder carType(CarType type);

    /**
     * 设置汽车座位的个数
     * @param seats 座位的个数
     */
    Builder seats(int seats);
    Builder engine(Engine engine);
    Builder transmission(Transmission transmission);
    Builder tripComputer(TripComputer tripComputer);
    Builder GPSNavigator(GPSNavigator gpsNavigator);

}
