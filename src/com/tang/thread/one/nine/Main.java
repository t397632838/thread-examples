package com.tang.thread.one.nine;

import java.util.concurrent.TimeUnit;

/**
 * 并发应用的一个关键地方就是共享数据。这个对那些扩展Thread类或者实现Runnable接口的对象特别重要。

    如果你创建一个类对象，实现Runnable接口，然后多个Thread对象使用同样的Runnable对象，全部的线程都共享同样的属性。
    这意味着，如果你在一个线程里改变一个属性，全部的线程都会受到这个改变的影响。

    有时，你希望程序里的各个线程的属性不会被共享。 Java 并发 API提供了一个很清楚的机制叫本地线程变量。
 */

/**
 * Java 并发 API 包括 InheritableThreadLocal 类提供线程创建线程的值的遗传性 。
 * 如果线程A有一个本地线程变量，然后它创建了另一个线程B，那么线程B将有与A相同的本地线程变量值。
 * 你可以覆盖 childValue() 方法来初始子线程的本地线程变量的值。 它接收父线程的本地线程变量作为参数。
 */
public class Main {

    public static void main(String[] args) {
        SafeTask task = new SafeTask();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
