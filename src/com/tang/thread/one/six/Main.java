package com.tang.thread.one.six;

import java.util.Date;

public class Main {

    /**
     * 在某些情况下，我们需要等待线程的终结。
     * 例如，我们可能会遇到程序在执行前需要初始化资源。
     * 在执行剩下的代码之前，我们需要等待线程完成初始化任务。
     * 为达此目的, 我们使用Thread 类的join() 方法。
     * 当前线程调用某个线程的这个方法时，它会暂停当前线程，直到被调用线程执行完成。
     *
     * @param args
     */
    public static void main(String[] args) {
        DataSourcesLoader dsLoader = new DataSourcesLoader();
        Thread thread1 = new Thread(dsLoader, "DataSourceThread");
        NetworkConnectionsLoader networkConnectionsLoader = new NetworkConnectionsLoader();
        Thread thread2 = new Thread(networkConnectionsLoader, "NetworkConnectionsLoader");
        thread1.start();
        thread2.start();
        try {
            thread1.join(1000);
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
    }
}
