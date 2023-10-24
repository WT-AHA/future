package com.aha.common.designpatterns.builder.builders;

import com.commons.designpatterns.builder.cars.CarType;
import com.commons.designpatterns.builder.cars.Manual;
import com.commons.designpatterns.builder.components.Engine;
import com.commons.designpatterns.builder.components.GPSNavigator;
import com.commons.designpatterns.builder.components.Transmission;
import com.commons.designpatterns.builder.components.TripComputer;

/**
 * 汽车使用手册的生成器
 *
 * @author WT
 * @date 2022/01/18 14:41:55
 */
public class CarManualBuilder implements Builder{

    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
    public CarManualBuilder carType(CarType type) {
        this.type = type;
        return this;
    }

    @Override
    public CarManualBuilder seats(int seats) {
        this.seats = seats;
        return this;
    }

    @Override
    public CarManualBuilder engine(Engine engine) {
        this.engine = engine;
        return this;
    }

    @Override
    public CarManualBuilder transmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    @Override
    public CarManualBuilder tripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
        return this;
    }

    @Override
    public CarManualBuilder GPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
        return this;
    }

    public Manual getResult() {
        return new Manual(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }

}
