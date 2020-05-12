package com.ze.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * #author:qinze
 *
 * @date:2020-05-11
 * @description: instance the method ToListCollector
 **/

public class CustomerCollectorAction {

    public static void main(String[] args) {
        Collector<String, List<String>, List<String>> collector = new ToListCollector<>();

        //String[] arrs = new String[]{"Alex", "Wang", "Hello", "Lambda", "Collector", "Java 8", "Stream"};

        /*List<String> result = Arrays.stream(arrs)
                .filter(s -> s.length() >= 5)
                .collect(collector);
        System.out.println(result);*/

        List<String> result = Arrays.asList("Alex", "Wang", "Hello", "Lambda","Collector", "Java 8", "Stream")
                .parallelStream()
                .filter(s -> s.length() > 5)
                .collect(collector);
        System.out.println(result);
    }
}
