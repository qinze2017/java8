package com.ze.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * #author:qinze
 *
 * @date:2020-05-05
 * @description:
 **/

public class LambdaUsage {

   /* @FunctionalInterface
    public interface Adder {
        int add(int a, int b);
    }

*//*    Wrong :
    @FunctionalInterface
    public interface SmartAdder extends Adder {
        int add(int a, int b);
    }*//*

    @FunctionalInterface
    public interface Empty extends Adder {

    }

*//*    Wrong :
    @FunctionalInterface
    public interface DoNothing  {

    }*/

    private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : source) {
            if(predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterByBiPredicate(List<Apple> source, BiPredicate<String, Long> predicate) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : source) {
            if(predicate.test(apple.getColor(),apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }

    private static void testConsumer(List<Apple> source, Consumer<Apple> consumer){
        for(Apple apple : source) {
            consumer.accept(apple);
        }
    }

    private static void testBiConsumer(List<Apple> source, BiConsumer<Apple,String> consumer, String c){
        for(Apple apple : source) {
            consumer.accept(apple,c);
        }
    }

    private static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : source) {
            if(predicate.test(apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }

    private static String testFunction(Apple apple, Function<Apple,String> fun) {
        return fun.apply(apple);
    }

    private static Apple testBiFunction(String color, Long weight, BiFunction<String, Long, Apple> fun) {
        return fun.apply(color,weight);
    }

    public static void main(String[] args) {
        /*Runnable r1 = () -> System.out.println("Hello");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello"));*/

        List<Apple> list = Arrays.asList(new Apple("green",120),new Apple("red",150));

        List<Apple> greenList = filter(list, (apple) -> apple.getColor().equals("green"));
        System.out.println(greenList);

        List<Apple> weightList = filterByWeight(list, (w) -> w > 100);
        System.out.println(weightList);

        List<Apple> weightList2 = filterByBiPredicate(list, (s,w) -> s.equals("green") && w > 100);
        System.out.println(weightList2);

        testConsumer(list, (a) -> System.out.println(a));
        testBiConsumer(list, (a,s) -> System.out.println(s + a.getColor() + ":Weight=>" + a.getWeight()), "XXX");

        String resultFun = testFunction(new Apple("yellow",100), (a) -> a.toString());
        System.out.println(resultFun);

        IntFunction<Double> f = i -> i * 100.0d;
        double resultDoulbe = f.apply(10);
        System.out.println(resultDoulbe);

        Apple resultapple = testBiFunction("Blue", (long) 130, (s, w) -> new Apple(s, w));
        System.out.println(resultapple);

        Supplier<String> s = String::new;
        System.out.println(s.get().getClass());

        Apple supplierApple = createApple(() -> new Apple("green", 100));
        System.out.println(supplierApple);

        /*int i = 0;
        i++;*/

        //final int i = 0;
        int i = 0;
        /*Runnable r = new Runnable() {
            @Override
            public void run() {
                //i++;
                System.out.println(i);
            }
        };
        //i++;*/

        Runnable r2 = () -> System.out.println(i);
    }

    /*private static void process(Runnable r) {
        r.run();
    }*/

    private static Apple createApple(Supplier<Apple> supplier) {
        return supplier.get();
    }

}