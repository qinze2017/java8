package com.ze.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * #author:qinze
 *
 * @date:2020-05-19
 * @description:
 **/

public class CompletableFutureInActionFlux {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            //Daemon Thread
            t.setDaemon(false);
            return t;
        });
        /*CompletableFuture.supplyAsync(CompletableFutureInAction::get, executor)
                .thenApply(CompletableFutureInActionFlux::multiply)
                .whenComplete((v,t) -> {
                    Optional.ofNullable(v).ifPresent(System.out::println);
                });*/

        List<Integer> productIDs = Arrays.asList(1, 2, 3, 4, 5);
        /*Stream<CompletableFuture<Double>> completableFutureStream = productIDs.stream().map(i -> CompletableFuture.supplyAsync(() ->
                queryProduct(i), executor));
        Stream<CompletableFuture<Double>> multiplyFutures = completableFutureStream.map(future -> future.thenApply(CompletableFutureInActionFlux::multiply));

        List<Double> result = multiplyFutures.map(CompletableFuture::join).collect(toList());
        System.out.println(result);*/
        List<Double> result = productIDs
                .stream()
                .map(i -> CompletableFuture.supplyAsync(() -> queryProduct(i), executor))
                .map(future -> future.thenApply(CompletableFutureInActionFlux::multiply))
                .map(CompletableFuture::join).collect(toList());
        System.out.println(result);
    }

    private static double queryProduct(int i ) {
        return CompletableFutureInAction.get();
    }

    private static double multiply(double value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return  value * 10d;
    }
}
