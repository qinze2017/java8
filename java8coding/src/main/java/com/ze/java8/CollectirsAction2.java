package com.ze.java8;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

// passive loading class without initialize, if remove final, so it will be active loading
import static com.ze.java8.CollectorsAction.menu;

/**
 * #author:qinze
 *
 * @date:2020-05-09
 * @description:
 **/

public class CollectirsAction2 {

    public static void main(String[] args) {
        testGroupingByConcurrentWithFunction();
        testGroupingByConcurrentWithFunctionAndCollector();
        testGroupingByConcurrentWithFunctionAndSupplierAndCollector();
        testJoiningWithDelimiter();
        testJoiningWithDelimiterAndPrefixAndSuffix();
        testMapping();
        testMaxBy();
        testMinBy();

        testPartitioningByWithPredicate();
        testPartitioningByWithPredicateAndCollector();
        testReducingBinaryOperator();
        testReducingBinaryOperatorAndIdentity();
        testReducingBinaryOperatorAndIdentityAndFunction();
        testSummarizingDouble();
        testSummarizingInt();
        testSummarizingLong();

        testSummeringDouble();
        testSummeringLong();
        testSummeringInt();
        testToCollection();
        testToConcurrentMap();
        testToConcurrentMapWithBinaryOperator();
        testToConcurrentMapWithBinaryOperatorAndSupplier();
        testToList();
        testToSet();
        testToMap();
        testToMapWithBinaryOperator();
        testToMapWithBinaryOperatorAndSupplier();
    }

    private static void testGroupingByConcurrentWithFunction(){
        ConcurrentMap<Dish.Type, List<Dish>> collect = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);

    }

    private static void testGroupingByConcurrentWithFunctionAndCollector(){
        ConcurrentMap<Dish.Type, Double> collect = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector(){
        ConcurrentSkipListMap<Dish.Type, Double> collect = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testJoiningWithDelimiter(){
        Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",")))
                .ifPresent(System.out::println);
    }

    private static void testJoiningWithDelimiterAndPrefixAndSuffix(){
        Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",", "Names[","]")))
                .ifPresent(System.out::println);
    }

    private static void testMapping(){
        Optional.of(menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining(","))))
                .ifPresent(System.out::println);
    }

    private static void testMaxBy(){
        menu.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testMinBy(){
        menu.stream().collect(Collectors.minBy(Comparator.comparing(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testPartitioningByWithPredicate(){
        Map<Boolean, List<Dish>> collect = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testPartitioningByWithPredicateAndCollector(){
        Map<Boolean, Double> collect = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testReducingBinaryOperator(){
        menu.stream()
                .collect(
                        Collectors.reducing(
                                BinaryOperator.maxBy(
                                        Comparator.comparing(
                                                Dish::getCalories)
                                )
                        )
                ).ifPresent(System.out::println);
    }

    private static void testReducingBinaryOperatorAndIdentity(){
        Integer collect = menu.stream()
                .map(Dish::getCalories)
                .collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
        System.out.println(collect);
    }

    private static void testReducingBinaryOperatorAndIdentityAndFunction(){
        Integer collect = menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2));
        System.out.println(collect);
    }

    private static void testSummarizingDouble(){
        Optional.of(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testSummarizingInt(){
        Optional.of(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testSummarizingLong(){
        Optional.of(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testSummeringDouble(){
        Optional.of(menu.stream().collect(Collectors.summingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);

        Optional.of(menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum())
                .ifPresent(System.out::println);

    }

    private static void testSummeringLong(){
        Optional.of(menu.stream().collect(Collectors.summingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testSummeringInt(){
        Optional.of(menu.stream().collect(Collectors.summingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testToCollection(){
        Optional.of( menu.stream().filter(d -> d.getCalories() > 600).collect(Collectors.toCollection(LinkedList::new)))
                .ifPresent(System.out::println);
    }

    private static void testToConcurrentMap(){
        Optional.of( menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories)))
                .ifPresent((v) -> {
                    System.out.println(v.getClass());
                    System.out.println(v);
                });
    }

    // Type : Total
    //The ConcurrentHashMap is thread safe but not ordered
    private static void testToConcurrentMapWithBinaryOperator(){
        Optional.of( menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b)))
                .ifPresent(v -> {
                    System.out.println(v.getClass());
                    System.out.println(v);
                });
    }

    private static void testToConcurrentMapWithBinaryOperatorAndSupplier(){
        Optional.of( menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a +b, ConcurrentSkipListMap::new)))
                .ifPresent(v -> {
                    System.out.println(v.getClass());
                    System.out.println(v);
                });
    }

    private static void testToList(){
        Optional.of( menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList()))
                .ifPresent(v -> {
                    System.out.println(v.getClass());
                    System.out.println(v);
                });
    }

    // no doublons
    private static void testToSet(){
        Optional.of( menu.stream().filter(Dish::isVegetarian).collect(Collectors.toSet()))
                .ifPresent( v -> {
                    System.out.println(v.getClass());
                    System.out.println(v);
                });
    }

    private static void testToMap(){
        Optional.of( menu.stream()
                .collect(Collectors.toMap(Dish::getName, Dish::getCalories)))
                .ifPresent(v -> {
                    System.out.println(v.getClass());
                    System.out.println(v);
                });

        //thread safe
        Optional.of( menu.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(Dish::getName, Dish::getCalories),
                        Collections::synchronizedMap)))
                .ifPresent(v -> {
                    System.out.println(v.getClass());
                    System.out.println(v);
                });

    }

    private static void testToMapWithBinaryOperator(){
        Optional.of( menu.stream()
                .collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b)))
                .ifPresent(v -> {
                    System.out.println(v.getClass());
                    System.out.println(v);
                });
    }

    private static void testToMapWithBinaryOperatorAndSupplier(){
        Optional.of( menu.stream()
                .collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a +b, Hashtable::new)))
                .ifPresent(v -> {
                    System.out.println(v.getClass());
                    System.out.println(v);
                });
    }
}
