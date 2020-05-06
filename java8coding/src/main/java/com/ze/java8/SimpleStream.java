package com.ze.java8;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * #author:qinze
 *
 * @date:2020-05-06
 * @description:
 **/

public class SimpleStream {

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

       /*List<String> dishNamesByCollections = getDishNamesByCollections(menu);
       System.out.println(dishNamesByCollections);*/

       /*List<String> dishNamesBySteam = getDishNamesByStream(menu);
       System.out.println(dishNamesBySteam);*/

        //Stream<Dish> stream = menu.stream();
        //stream.forEach(System.out::println);
        //stream.forEach(System.out::println);

        Stream<Dish> dishStream = Stream.of(new Dish("salmon", false, 450, Dish.Type.FISH),
                new Dish("pizza", true, 550, Dish.Type.OTHER));
        dishStream.forEach(System.out::println);

        List<String> result = menu.stream().filter(d -> {
           System.out.println("filtering->" + d.getName());
           return d.getCalories() > 300;
        })
                     .map(d -> {
                         System.out.println("map->" + d.getName());
                         return d.getName();
        })
                     .limit(3).collect(toList());

        System.out.println(result);
    }

    private static List<String> getDishNamesByStream(List<Dish> menu){
        return menu.stream().filter(d -> {
                    // ForkJoinPool
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                return d.getCalories() < 400;
                }

                ).sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }

    private static List<String> getDishNamesByCollections(List<Dish> menu) {
        List<Dish> lowCalories = new ArrayList<>();

        //filter and get calories less 400
        for(Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCalories.add(d);
            }
        }

        //JConsole to verify thread and process
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //sort
        Collections.sort(lowCalories, (d1,d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));

        List<String> dishNameList = new ArrayList<>();
        for(Dish d : lowCalories) {
            dishNameList.add(d.getName());
        }
        return dishNameList;
    }
}
