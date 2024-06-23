package com.aha.common.design.patterns.builder.director;

import com.aha.common.design.patterns.builder.components.Engine;
import com.aha.common.design.patterns.builder.components.GPSNavigator;
import com.aha.common.design.patterns.builder.components.TripComputer;
import com.aha.common.design.patterns.builder.builders.Builder;
import com.aha.common.design.patterns.builder.cars.CarType;
import com.aha.common.design.patterns.builder.components.Transmission;

/**
 * 主管类使用通用的 builder 定义构造顺序，因此你可能不知道构造出来了什么产品。
 *
 * @author WT
 * @date 2022/01/18 14:57:40
 */
public class Director {

    /**
     * 跑车的通用构造
     * @param builder 构造器
     */
    public void constructSportsCar(Builder builder) {
        builder.carType(CarType.SPORTS_CAR)
                .seats(2)
                .engine(new Engine(3.0, 0))
                .transmission(Transmission.SEMI_AUTOMATIC)
                .tripComputer(new TripComputer())
                .GPSNavigator(new GPSNavigator());
    }

//    /**
//     * 轿车的通用构造
//     * @param builder 构造器
//     */
//    public void constructCityCar(Builder builder) {
//        builder.setCarType(CarType.CITY_CAR);
//        builder.setSeats(2);
//        builder.setEngine(new Engine(1.2, 0));
//        builder.setTransmission(Transmission.AUTOMATIC);
//        builder.setTripComputer(new TripComputer());
//        builder.setGPSNavigator(new GPSNavigator());
//    }
//
//    /**
//     * SUV 的通用构造
//     * @param builder 构造器
//     */
//    public void constructSUV(Builder builder) {
//        builder.setCarType(CarType.SUV);
//        builder.setSeats(4);
//        builder.setEngine(new Engine(2.5, 0));
//        builder.setTransmission(Transmission.MANUAL);
//        builder.setGPSNavigator(new GPSNavigator());
//    }

}
