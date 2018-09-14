package com.tang.thread.six.two;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by raytine on 2018/9/8.
 */
public class Main {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();

        Thread threads[] = new Thread[100];

        for (int i = 0; i < 100; i++) {
            AddTask addTask = new AddTask(list);
            threads[i] = new Thread(addTask);
            threads[i].start();
        }

        System.out.printf("Main: %d AddTask threads have been launched", threads.length);

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Size of the List: %d", list.size());

        for (int i = 0; i < threads.length; i++) {
            PollTask pollTask = new PollTask(list);
            threads[i] = new Thread(pollTask);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Size of the List: %d", list.size());

    }
}
