package com.aha.common.designpatterns.builder;

import com.commons.designpatterns.builder.builders.CarBuilder;
import com.commons.designpatterns.builder.builders.CarManualBuilder;
import com.commons.designpatterns.builder.cars.Car;
import com.commons.designpatterns.builder.cars.Manual;
import com.commons.designpatterns.builder.director.Director;

/**
 * 客户端使用 建造者 模式 示例
 *
 * @author WT
 * @date 2022/01/18 15:03:53
 */
public class Client {

    public static void main(String[] args) {
        Director director = new Director();

        // Director gets the concrete builder object from the client
        // (application code). That's because application knows better which
        // builder to use to get a specific product.
        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);

        // The final product is often retrieved from a builder object, since
        // Director is not aware and not dependent on concrete builders and
        // products.
        Car car = builder.getResult();
        System.out.println("Car built:\n" + car.getCarType());


        CarManualBuilder manualBuilder = new CarManualBuilder();

        // Director may know several building recipes.
        director.constructSportsCar(manualBuilder);
        Manual carManual = manualBuilder.getResult();
        System.out.println("\nCar manual built:\n" + carManual.print());
    }

}
