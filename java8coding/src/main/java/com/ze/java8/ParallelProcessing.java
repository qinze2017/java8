package com.ze.java8;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * #author:qinze
 *
 * @date:2020-05-11
 * @description:
 **/

public class ParallelProcessing {

    public static void main(String[] args) {
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        System.out.println(Runtime.getRuntime().availableProcessors());

        System.out.println("The best process time of normalAdd is " + measureSumPerformance(ParallelProcessing::normalAdd, 100_000_000) + "ms");
        //System.out.println("The best process time of iterateStream is " + measureSumPerformance(ParallelProcessing::iterateStream, 10_000_000) + "ms");
        //System.out.println("The best process time of parallelStream is " + measureSumPerformance(ParallelProcessing::parallelStream, 10_000_000) + "ms");
        //System.out.println("The best process time of parallelStream2 is " + measureSumPerformance(ParallelProcessing::parallelStream2, 10_000_000) + "ms");
        System.out.println("The best process time of parallelStream3 is " + measureSumPerformance(ParallelProcessing::parallelStream3, 100_000_000) + "ms");
    }

    private static long iterateStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream2(long limit) {
        return Stream.iterate(1L, i -> i + 1).mapToLong(Long::longValue).parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream3(long limit) {
        //return LongStream.rangeClosed(1, limit).parallel().sum();
        return LongStream.rangeClosed(1, limit).parallel().reduce(0L, Long::sum);
    }

    private static long measureSumPerformance(Function<Long, Long> adder, long limit) {
        long fastest = Long.MAX_VALUE;
        for(int i = 0; i < 10; i++) {
            long startTimestamp = System.currentTimeMillis();
            long result = adder.apply(limit);
            long duration = System.currentTimeMillis() - startTimestamp;
            //System.out.println("The result of sum is " + result);
            if (duration < fastest)
                fastest = duration;
        }
        return fastest;
    }

    private static long normalAdd(long limit) {
        long result = 0L;
        for (long i = 1L; i < limit; i++){
            result += i;
        }
        return result;
    }
}
