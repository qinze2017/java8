package com.ze.java8;

import java.util.Optional;

/**
 * #author:qinze
 *
 * @date:2020-05-07
 * @description:
 **/

public class OptionalInAction {

    public static void main(String[] args) {

        System.out.println(getInsuranceNameOptional(null));
        Optional.ofNullable(getInsuranceNameOptional(null)).ifPresent(System.out::println);
    }

    private static String getInsuranceNameOptional(Person person) {
        return Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName).orElse("UNKNOWN");
    }
}
