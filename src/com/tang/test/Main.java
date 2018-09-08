package com.tang.test;

/**
 * Created by raytine on 2018/9/8.
 */
public class Main {

    public static void main(String[] args) {
        Thread thread[] = new Thread[10];
        Number number = new Number();
        for (int i = 0; i<10 ;i++){
            thread[i] = new Thread(number);
            thread[i].start();
        }

    }
}
