package com.ze.java;

/**
 * #author:qinze
 *
 * @date:2020-05-05
 * @description:
 **/
public class Apple {
    private String color;
    private long weight;

    public Apple(String color, long weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }
}
