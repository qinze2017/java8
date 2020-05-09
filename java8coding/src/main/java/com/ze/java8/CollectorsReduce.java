package com.ze.java8;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * #author:qinze
 *
 * @date:2020-05-08
 * @description:
 **/

public class CollectorsReduce {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        long count = menu.stream().filter(d -> d.isVegetarian()).count();
        System.out.println(count);

        Long collectCount = menu.stream().filter(d -> d.isVegetarian()).collect(Collectors.counting());
        System.out.println(collectCount);

        Optional<Integer> maxCalorie = menu.stream().map(Dish::getCalories).reduce(Integer::max);
        System.out.println(maxCalorie.get());

        Optional<Dish> maxCalorie2 = menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        maxCalorie2.ifPresent(System.out::println);

        Optional<Dish> collect = menu.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        collect.ifPresent(System.out::println);

        Integer collect1 = menu.stream().collect(Collectors.collectingAndThen(toList(), t -> t.size()));
        System.out.println(collect1);

        Map<Dish.Type, List<Dish>> collect2 = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        Optional.ofNullable(collect2).ifPresent(System.out::println);

        Map<Dish.Type, Double> collect3 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect3).ifPresent(System.out::println);
    }





}
