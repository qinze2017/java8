package com.ze.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * #author:qinze
 *
 * @date:2020-05-05
 * @description:
 **/

public class FilterApple {

    @FunctionalInterface
    public interface AppleFilter {

        boolean filter(Apple apple);

        /*default void print(String var) {
            System.out.println(var);
        }*/

    }

    public static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if (appleFilter.filter(apple))
                list.add(apple);
        }
        return list;
    }

    public static class GreenAnd160WeightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("green") && apple.getWeight() >= 160);
        }
    }

    public static class YellowLess150WeightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("yellow") && apple.getWeight() < 150);
        }
    }

    public static List<Apple> findApple(List<Apple> apples, String color) {
        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                list.add(apple);
            }
        }

        return list;
    }

    public static List<Apple> findGreenApple(List<Apple> apples) {

        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }

        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Apple> list = Arrays.asList(new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170)
                , new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170));

        List<Apple> greenList = list.stream().filter(a -> a.getColor().equals("green")).collect(Collectors.toList());
        Optional.ofNullable(greenList).ifPresent(System.out::println);

        Optional.ofNullable(groupByNormal(list)).ifPresent(System.out::println);

        Optional.ofNullable(groupByFunction(list)).ifPresent(System.out::println);

        Optional.ofNullable(groupByCollector(list)).ifPresent(System.out::println);
        
        /*List<Apple> greenApples = findGreenApple(list);
        assert greenApples.size() == 2;*/

        /*List<Apple> greenApples = findApple(list, "green");
        System.out.println(greenApples);

        List<Apple> redApples = findApple(list, "red");
        System.out.println(redApples);*/

       /* List<Apple> result = findApple(list, new GreenAnd160WeightFilter());
        System.out.println(result);

        List<Apple> yellowList = findApple(list, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return ("yellow".equals(apple.getColor()) && apple.getWeight() < 150);
            }
        });

        System.out.println(yellowList);*/

        /*List<Apple> lambdaResult = findApple(list, (Apple apple) -> {
            return apple.getColor().equals("green");
        });

        List<Apple> lambdaResult = findApple(list, (Apple apple) ->
            apple.getColor().equals("green");
        });

        System.out.println(lambdaResult);*/

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        Thread.currentThread().join();*/
    }

    private static Map<String, List<Apple>> groupByNormal(List<Apple> apples){
        Map<String, List<Apple>> map = new HashMap<>();
        for ( Apple apple : apples) {
            List<Apple> list = map.get(apple.getColor());
            if (null == list) {
                list = new ArrayList<>();
                map.put(apple.getColor(), list);
            }
            list.add(apple);
        }

        return map;
    }

    private static Map<String, List<Apple>> groupByFunction(List<Apple> apples){
        Map<String, List<Apple>> map = new HashMap<>();
        apples.stream().forEach(a -> {
            List<Apple> colorList = Optional.ofNullable(map.get(a.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                map.put(a.getColor(), list);
                return list;
            });
            colorList.add(a);
        });
        return map;
    }

    private static Map<String, List<Apple>> groupByCollector(List<Apple> apples){

        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}
