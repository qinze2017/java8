package com.ze.java8;

/**
 * #author:qinze
 *
 * @date:2020-05-07
 * @description:
 **/

public class Trader {

    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return name + " in " + city ;
    }
}


