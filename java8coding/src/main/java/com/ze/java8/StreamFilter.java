package com.ze.java8;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * #author:qinze
 *
 * @date:2020-05-06
 * @description:
 **/

public class StreamFilter {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,6,7,7,1);

        List<Integer> result = list.stream().filter(a -> a%2 == 0).collect(toList());
        System.out.println(result);

        result = list.stream().distinct().collect(toList());
        System.out.println(result);

        result = list.stream().skip(5).collect(toList());
        System.out.println(result);

        result = list.stream().limit(5).collect(toList());
        System.out.println(result);
    }
}
