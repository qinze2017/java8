package com.ze.java8;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * #author:qinze
 *
 * @date:2020-05-05
 * @description:
 **/

public class LambdaExpression {

    public static void main(String[] args) {

        Comparator<Apple> byColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };

        List<Apple> list = Collections.emptyList();

        list.sort(byColor);

        Comparator<Apple> byColor2 = (o1, o2) ->
                o1.getColor().compareTo(o2.getColor());

        Function<String, Integer> flambda = s -> s.length();

        Predicate<Apple> p = (Apple a) -> a.getColor().equals("green");

        Runnable r = () -> {};

    }
}