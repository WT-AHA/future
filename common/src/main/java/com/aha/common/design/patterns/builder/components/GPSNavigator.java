package com.aha.common.design.patterns.builder.components;

/**
 * 汽车产品特征 - 2 - GPS 导航
 *
 * @author WT
 * @date 2022/01/18 14:51:07
 */
public class GPSNavigator {

    private String route;

    public GPSNavigator() {
        this.route = "221b, Baker Street, London  to Scotland Yard, 8-10 Broadway, London";
    }

    public GPSNavigator(String manualRoute) {
        this.route = manualRoute;
    }

    public String getRoute() {
        return route;
    }

}
