package com.ze.java8;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * #author:qinze
 *
 * @date:2020-05-06
 * @description:
 **/

public class NumericStream {

    public static void main(String[] args) {
        //int 4 byte/32 bits
        //Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        /*Integer result = stream.filter(i -> i.intValue() > 3).reduce(0, Integer::sum);
        System.out.println(result);*/

        //IntStream intStream = stream.mapToInt(i -> i.intValue());
        //int sum = intStream.filter(i -> i > 3).sum();
        //intStream.filter(i -> i > 3).reduce(0,(i, j) -> i+j);
        //System.out.println(sum);

        int a = 9;

        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map( b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a="+r[0] + ",b=" + r[1] + ",c=" + r[2]));

        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj( b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a="+r[0] + ",b=" + r[1] + ",c=" + r[2]));

    }
}
