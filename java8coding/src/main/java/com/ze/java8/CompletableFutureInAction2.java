package com.ze.java8;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * #author:qinze
 *
 * @date:2020-05-18
 * @description: supplyAsync
 **/

public class CompletableFutureInAction2 {

    public static void main(String[] args) throws InterruptedException {

        AtomicBoolean finished = new AtomicBoolean(false);

        ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            //Daemon Thread
            t.setDaemon(false);
            return t;
        });

        CompletableFuture.supplyAsync(CompletableFutureInAction::get, executor)
                .whenComplete((v,t) -> {
                    Optional.of(v).ifPresent(System.out::println);
                    finished.set(true);
                    Optional.of(t).ifPresent(x -> x.printStackTrace());
                });
        System.out.println("=====no===block=====");

        //Thread.currentThread().join();
        /*while (!finished.get()) {
            Thread.sleep(1);
        }*/

        /*ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        executor.execute(() -> System.out.println("test..."));*/
        //executor.shutdown();
    }
}
