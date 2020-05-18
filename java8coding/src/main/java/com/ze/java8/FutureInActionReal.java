package com.ze.java8;

import java.util.concurrent.*;

/**
 * #author:qinze
 *
 * @date:2020-05-18
 * @description:
 **/

public class FutureInActionReal {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(10000L);
                return "Finished";
            } catch (InterruptedException e) {
                return "Error";
            }
        });

        //String s = future.get(10, TimeUnit.MICROSECONDS);
        while (!future.isDone()) {
            Thread.sleep(10);
        }
        String s = future.get();
        System.out.println(s);
        executorService.shutdown();
    }
}
