package com.ze.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * #author:qinze
 *
 * @date:2020-05-07
 * @description:
 **/

public class StreamInAction {
    public static void main(String[] args) {

        Trader mario = new Trader("Mario","Milan");
        Trader ze = new  Trader("Ze", "Sherbrooke");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2020,300),
                new Transaction(ze, 2019,1000),
                new Transaction(mario, 2018,400),
                new Transaction(mario, 2019,700),
                new Transaction(ze, 2019,930),
                new Transaction(brian, 2016,666)
        );

        //Find all transactions in the year 2019 and sort them by value order by ASC
        List<Transaction> result = transactions.stream().filter(transaction -> transaction.getYear() == 2019)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());

        System.out.println(result);

        // Find the unique cities where the traders work
        transactions.stream().map( t -> t.getTrader().getCity())
                .distinct().forEach(System.out::println);

        //Find all traders from Cambridge and sort them by name
        transactions.stream().map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        //Return a String of all traders' names sorted alphabetically.
        String value = transactions.stream().map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (name1, name2) -> name1 + name2);
        System.out.println(value);

        // Are any traders based in Milan ?
        boolean milan1 = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        boolean milan2 = transactions.stream().map(t -> t.getTrader())
                .anyMatch(t -> t.getCity().equals("Milan"));
        System.out.println(milan1 + " " + milan2);

        //Show all transactions' values from the traders living in Cambridge
        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // Find the highest value from all the transactions
        transactions.stream().map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);
        Optional<Integer> maxValue = transactions.stream().map(Transaction::getValue)
                .reduce((i, j) -> i > j ? i : j);
        System.out.println(maxValue.get());

        // Find the smallest value of transaction
        Optional<Integer> minValue = transactions.stream().map(Transaction::getValue)
                .reduce((i, j) -> i < j ? i : j);
        System.out.println(minValue.get());
    }
}
