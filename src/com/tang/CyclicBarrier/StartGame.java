package com.tang.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

import static sun.jvm.hotspot.runtime.PerfMemory.start;

public class StartGame implements Runnable {

    private String player;
    private CyclicBarrier barrier;

    public StartGame(String player, CyclicBarrier barrier) {
        this.player = player;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.getPlayer() + " 开始匹配玩家...");
            findOtherPlayer();
            barrier.await();

            System.out.println(this.getPlayer() + " 进行选择角色...");
            choiceRole();
            System.out.println(this.getPlayer() + " 角色选择完毕等待其他玩家...");
            barrier.await();

            System.out.println(this.getPlayer() + " 开始游戏,进行游戏加载...");
            loading();
            System.out.println(this.getPlayer() + " 游戏加载完毕等待其他玩家加载完成...");
            barrier.await();


            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void findOtherPlayer() throws InterruptedException {
        Thread.sleep(1000);
    }

    private static void choiceRole() throws InterruptedException {
        Thread.sleep(1000);
    }

    private static void loading() throws InterruptedException {
        Thread.sleep(2000);
    }


    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }
}
