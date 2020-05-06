package com.ze.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
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

        //Stream<String> streamFromFile = createStreamFromFile();

        //createStreamFromIterator().forEach(System.out::println);
        //createStreamFromGenerator().forEach(System.out::println);
        createObjStreamFromGenerator().forEach(System.out::println);
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

    private static Stream<Integer> createStreamFromIterator() {
        Stream<Integer> stream = Stream.iterate(0, n -> n + 2).limit(10);
        return stream;
    }

    private static Stream<Double> createStreamFromGenerator() {
        return Stream.generate(Math::random).limit(10);
    }

    private static  Stream<Obj> createObjStreamFromGenerator() {
        return Stream.generate(new ObjSupplier()).limit(10);
    }

    static class ObjSupplier implements Supplier<Obj> {
        private int index = 0;
        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index = random.nextInt(100);
            return new Obj(index, "Name ->" + index);
        }
    }

    static class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
