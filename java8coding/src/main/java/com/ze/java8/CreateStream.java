package com.ze.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * #author:qinze
 *
 * @date:2020-05-06
 * @description:
 **/

public class CreateStream {

    public static void main(String[] args) {
        /*createStreamFromCollection().forEach(System.out::println);
        createStreamFromValues().forEach(System.out::println);*/
        //createStreamFromArrays().forEach(System.out::println);
        Stream<String> streamFromFile = createStreamFromFile();
    }

    private static  Stream<String> createStreamFromCollection(){
        List<String> list = Arrays.asList("hello", "abc", "world", "stream");
        return list.stream();
    }

    private static Stream<String> createStreamFromValues(){
       return Stream.of("hello", "abc", "world", "stream");
    }

    private static Stream<String> createStreamFromArrays(){
        String[] strings = {"hello", "abc", "world", "stream"};
        return Arrays.stream(strings);
    }

    private static Stream<String> createStreamFromFile() {
        Path path =Paths.get("D:\\project\\java8\\java8coding\\src\\main\\java\\com\\ze\\java8\\CreateStream.java");
        try(Stream<String> streamFromFile = Files.lines(path)) {
            streamFromFile.forEach(System.out::println);
            return streamFromFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
