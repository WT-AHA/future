package com.aha.common.designpatterns.builder.components;

/**
 * 汽车产品特征 - 引擎
 *
 * @author WT
 * @date 2022/01/18 14:48:08
 */
public class Engine {

    /**
     * 容积
     */
    private final double volume;
    /**
     * 行驶里程 - 英里
     */
    private double mileage;
    /**
     * 是否点火
     */
    private boolean started;

    public Engine(double volume, double mileage) {
        this.volume = volume;
        this.mileage = mileage;
    }

    public void on() {
        started = true;
    }

    public void off() {
        started = false;
    }

    public boolean isStarted() {
        return started;
    }

    public void go(double mileage) {
        if (started) {
            this.mileage += mileage;
        } else {
            System.err.println("Cannot go(), you must start engine first!");
        }
    }

    public double getVolume() {
        return volume;
    }

    public double getMileage() {
        return mileage;
    }

}
