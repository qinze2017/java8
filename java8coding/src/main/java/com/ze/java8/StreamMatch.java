package com.ze.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * #author:qinze
 *
 * @date:2020-05-06
 * @description:
 **/

public class StreamMatch {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        boolean matched = stream.allMatch(i -> i > 10);
        System.out.println(matched);
        // add -ea to VM Parameter
        //assert matched == true:"some elements not matrched!";

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        matched = stream.anyMatch(i -> i > 6);
        System.out.println(matched);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        matched = stream.noneMatch(i -> i < 0);
        System.out.println(matched);
    }
}
