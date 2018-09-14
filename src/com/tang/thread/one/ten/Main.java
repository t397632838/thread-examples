package com.tang.thread.one.ten;

import java.util.concurrent.TimeUnit;

/**
 * Java并发 API里有个有趣的方法是把线程分组。这个方法允许我们按线程组作为一个单位来处理。
 * 例如，你有一些线程做着同样的任务，你想控制他们，无论多少线程还在运行，他们的状态会被一个call 中断。

    Java 提供 ThreadGroup 类来组织线程。
    ThreadGroup 对象可以由 Thread 对象组成和由另外的 ThreadGroup 对象组成,生成线程树结构。
 */
public class Main {


    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("Searcher");
        Result result=new Result();
        SearchTask searchTask=new SearchTask(result);
        for (int i=0; i<5; i++) {
            Thread thread=new Thread(threadGroup, searchTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Number of Threads: %d\n",threadGroup. activeCount());
        System.out.printf("Information about the Thread Group\n");
        threadGroup.list();

        Thread[] threads=new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i=0; i<threadGroup.activeCount(); i++) {
            System.out.printf("Thread %s: %s\n",threads[i].getName(),threads[i].getState());
        }

        waitFinish(threadGroup);

        threadGroup.interrupt();

    }

    private static void waitFinish(ThreadGroup threadGroup) {
        while (threadGroup.activeCount()>9) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
