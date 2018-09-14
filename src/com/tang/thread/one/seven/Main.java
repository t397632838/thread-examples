package com.tang.thread.one.seven;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Java有一种特别的线程叫做守护线程。
 * 这种线程的优先级非常低，通常在程序里没有其他线程运行时才会执行它。
 * 当守护线程是程序里唯一在运行的线程时，JVM会结束守护线程并终止程序。

    根据这些特点，守护线程通常用于在同一程序里给普通线程（也叫使用者线程）提供服务。
    它们通常无限循环的等待服务请求或执行线程任务。
    它们不能做重要的任务，因为我们不知道什么时候会被分配到CPU时间片，并且只要没有其他线程在运行，它们可能随时被终止。
    JAVA中最典型的这种类型代表就是垃圾回收器。
 */

public class Main {
    /**
     * 如果分析这个程序的输出，你可以发现queue可以一直增加直到它有30个事件，然后它的大小会在27-30之间直到运行结束。

     程序开始时有3个 WriterTask 线程。
     每个线程写一个事件然后休眠1秒。10秒之后，我们有30个事件在queue里。
     在这10秒内，当3个 WriterTask 线程休眠时， CleanerTasks已经开始运行，但是它没有删除任何事件，因为所有事件都才生成不到10秒。
     在剩下的运行里，CleanerTask 每秒删除3个事件, 然而3个 WriterTask 线程会另写3个，所以queue的大小在27-30之间。

     你可以修改 WriterTask 线程的休眠时间。如果你使用一个较小的值，
     你会发现CleanerTask 被分配到 CPU 时间片会更少，由于 CleanerTask 没有删除任何事件，
     所以queue大小会一直增加。
     */

    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<Event>();
        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writer);
            thread.start();
        }
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();
    }
}
