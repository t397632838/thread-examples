package com.tang.java8.Stream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Supplier;

public class Stream {


    public static void main(String[] args) {
        //Lists是Guava中的一个工具类
        List<Integer> nums = Lists.newArrayList(1, null, 3, 4, null, 6);
        System.out.println(nums.stream().filter(num -> num != null).count());
        //  of方法：有两个overload方法，一个接受变长参数，一个接口单一值
        java.util.stream.Stream<Integer> integerStream = java.util.stream.Stream.of(1, 2, 3, 5);
        java.util.stream.Stream<String> stringStream = java.util.stream.Stream.of("taobao");

        // generator方法：生成一个无限长度的Stream，
        // 其元素的生成是通过给定的Supplier（这个接口可以看成一个对象的工厂，每次调用返回一个给定类型的对象）
        java.util.stream.Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        });
        java.util.stream.Stream.generate(() -> Math.random());
        java.util.stream.Stream.generate(Math::random);

        //iterate方法：也是生成无限长度的Stream，和generator不同的是，
        // 其元素的生成是重复对给定的种子值(seed)调用用户指定函数来生成的。
        // 其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环
        java.util.stream.Stream.iterate(1, item -> item + 1).forEach(System.out::println);


        List<Integer> num = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:" + num.stream().filter(n -> n != null).
                distinct().mapToInt(n -> n * 2).
                peek(System.out::println).skip(2).limit(4).sum());

    }
}
