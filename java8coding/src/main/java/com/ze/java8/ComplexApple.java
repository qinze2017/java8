package com.ze.java8;

/**
 * #author:qinze
 *
 * @date:2020-05-06
 * @description:
 **/

public class ComplexApple {

    private String color;
    private Long weight;
    private String name;

    public ComplexApple(String color, Long weight, String name) {
        this.color = color;
        this.weight = weight;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ComplexApple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }
}
