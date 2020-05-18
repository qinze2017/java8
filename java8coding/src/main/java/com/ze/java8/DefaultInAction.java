package com.ze.java8;

/**
 * #author:qinze
 *
 * @date:2020-05-18
 * @description:
 **/

public class DefaultInAction {

    public static void main(String[] args) {
        /*A a = () -> 10;
        System.out.println(a.size());
        System.out.println(a.isEmpty());*/

        /*DefaultInAction ac = new DefaultInAction();

        //ac.confuse(null);
        int[] a = null;
        Object o = a;
        ac.confuse(o);*/

        A c = new C();
        c.hello();
    }

    /*private void confuse(Object o){
        System.out.println("Object");
    }

    private void confuse(int[] i) {
        System.out.println("int[]");
    }

    private interface A {

        int size();

        default boolean isEmpty() {
            return size() == 0;
        }
    }*/

    private interface A {
        default void hello() {
            System.out.println("==A.hello==");
        }
    }

    private interface B extends A {

        @Override
        default void hello() {
            System.out.println("==B.hello==");
        }
    }

    private interface D {

        default void hello() {
            System.out.println("==D.hello==");
        }
    }

    private static class C implements B, A, D {

        @Override
        public void hello(){
            D.super.hello();
            System.out.println("==C.hello==");
        }
    }
}
