package com.aha.common.design.patterns.builder.builders;

import com.aha.common.design.patterns.builder.cars.Car;
import com.aha.common.design.patterns.builder.components.Engine;
import com.aha.common.design.patterns.builder.components.GPSNavigator;
import com.aha.common.design.patterns.builder.components.Transmission;
import com.aha.common.design.patterns.builder.components.TripComputer;
import com.aha.common.design.patterns.builder.cars.CarType;

/**
 * 汽车生成器
 *
 * @author WT
 * @date 2022/01/18 14:28:26
 */
public class CarBuilder implements Builder{

    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
    public CarBuilder carType(CarType type) {
        this.type = type;
        return this;
    }

    @Override
    public CarBuilder seats(int seats) {
        this.seats = seats;
        return this;
    }

    @Override
    public CarBuilder engine(Engine engine) {
        this.engine = engine;
        return this;
    }

    @Override
    public CarBuilder transmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    @Override
    public CarBuilder tripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
        return this;
    }

    @Override
    public CarBuilder GPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
        return this;
    }

    public Car getResult() {
        return new Car(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }

}
