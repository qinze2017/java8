package com.ze.java8;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * #author:qinze
 *
 * @date:2020-05-18
 * @description:
 **/

public class CompletableFutureInAction {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            double value = get();
            completableFuture.complete(value);
        }).start();

        System.out.println("====no===block====");

        //Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);
        completableFuture.whenComplete((v, t) -> {
           Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
        });
    }

    public static double get() {
        try {
            Thread.sleep(RANDOM.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double result = RANDOM.nextDouble();
        System.out.println(result);
        return result;
    }
}
