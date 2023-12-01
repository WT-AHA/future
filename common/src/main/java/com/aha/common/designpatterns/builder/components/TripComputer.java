package com.aha.common.designpatterns.builder.components;

import com.aha.common.designpatterns.builder.cars.Car;

/**
 * 汽车产品特征 - 4 -
 *
 * @author WT
 * @date 2022/01/18 14:54:33
 */
public class TripComputer {

    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    public void showFuelLevel() {
        System.out.println("Fuel level: " + car.getFuel());
    }

    public void showStatus() {
        if (this.car.getEngine().isStarted()) {
            System.out.println("Car is started");
        } else {
            System.out.println("Car isn't started");
        }
    }

}
