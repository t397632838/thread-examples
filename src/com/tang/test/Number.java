package com.tang.test;

/**
 * Created by raytine on 2018/9/8.
 */
public class Number implements Runnable {

    private int count;


    @Override
    public void run() {
        count++;
        System.out.println(count);
    }
}

