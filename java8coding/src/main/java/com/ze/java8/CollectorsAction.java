package com.ze.java8;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

/**
 * #author:qinze
 *
 * @date:2020-05-08
 * @description: Collector actions
 **/

public class CollectorsAction {

        private final static List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        public static void main(String[] args) {
            testAveragingDouble();
            testAveragingInt();
            testAveragingLong();
            testCollectingAndThen();
            testCounting();
            testGroupingByFunction();
            testGroupingByFunctionAndCollector();
            testGroupingByFunctionAndSupplierAndCollector();
            testSummarizingInt();
        }

        private static void testAveragingDouble() {
            Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                    .ifPresent(System.out::println);
        }

        private static void testAveragingInt() {
            Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
        }

        private static void testAveragingLong() {
            Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                    .ifPresent(System.out::println);
        }

        private static void testCollectingAndThen(){
            Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), a -> "The average of calories is " + a)))
                    .ifPresent(System.out::println);

            List<Dish> list = menu.stream().filter(d -> d.getType().equals(Dish.Type.MEAT))
                    .collect(Collectors.toList());
            list.add(new Dish("", false, 100, Dish.Type.OTHER));

            // could not add Other type to the result list, else print exception
            List<Dish> listStable = menu.stream().filter(d -> d.getType().equals(Dish.Type.MEAT))
                    .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
            //listStable.add(new Dish("", false, 100, Dish.Type.OTHER));
            System.out.println(list);
            System.out.println(listStable);
        }

        private static void testCounting(){
            Optional.ofNullable(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
        }

        private static void testGroupingByFunction(){
            Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);
        }

        private static void testGroupingByFunctionAndCollector(){
            Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting() ))).ifPresent(System.out::println);
            Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories) ))).ifPresent(System.out::println);
        }

        private static void testGroupingByFunctionAndSupplierAndCollector(){
            Map<Dish.Type, Double> map = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
            System.out.println(map.getClass());
            // Change to other map type
            Map<Dish.Type, Double> treemap = menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.averagingInt(Dish::getCalories)));
            Optional.of(treemap.getClass()).ifPresent(System.out::println);
            Optional.of(treemap).ifPresent(System.out::println);
        }

        private static void testSummarizingInt(){
            IntSummaryStatistics result = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
            Optional.of(result).ifPresent(System.out::println);
        }

}

