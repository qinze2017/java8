package com.ze.java8;

import java.util.concurrent.ForkJoinPool;

/**
 * #author:qinze
 *
 * @date:2020-05-16
 * @description:
 **/

public class ForkJoinPoolTest {

    private static int[] data = {1,2,3,4,5,6,7,8,9,10};

    public static void main(String[] args) {
        System.out.println("result is " + calc());
        AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer invoke = forkJoinPool.invoke(task);
        System.out.println("AccumulatorRecursiveTask is " + invoke);

        AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
        forkJoinPool.invoke(action);
        System.out.println("AccumulatorRecursiveAction is " + AccumulatorRecursiveAction.AccumulatorHelper.getResult());
    }

    private static int calc(){
        int result = 0;
        for(int i = 0; i< data.length; i++) {
            result += data[i];
        }
        return result;
    }
}
