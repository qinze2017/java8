package com.ze.java8;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * #author:qinze
 *
 * @date:2020-05-16
 * @description:
 **/

public class AccumulatorRecursiveAction extends RecursiveAction {

    private final int start;
    private final int end;
    private final int[] data;
    private final int LIMIT = 3;

    public AccumulatorRecursiveAction(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {

        if( (end - start) <= LIMIT) {
            for (int i = start; i < end; i++  ) {
                AccumulatorHelper.accumulate(data[i]);
            }
        } else {
            int mid = (start + end) / 2;
            AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start, mid, data);
            AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(mid, end, data);
            left.fork();
            left.join();
            right.fork();
            right.join();
        }
    }

    static class AccumulatorHelper {
        //thread safe, an atomic counter with many threads concurrently
        private static final AtomicInteger result = new AtomicInteger(0);

        static void accumulate(int value) {
            result.getAndAdd(value);
        }

        public static int getResult(){
             return result.get();
        }

        void reset() {
            result.set(0);
        }
    }
}
