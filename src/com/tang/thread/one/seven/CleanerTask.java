package com.tang.thread.one.seven;

import java.util.Date;
import java.util.Deque;


public class CleanerTask extends Thread {

    private Deque<Event> deque;

    /**
     * 声明 queue，储存事件并实现类的构造函数，初始化queue，
     * 在这个构造函数，用setDaemon() 方法让此线程成为守护线程
     */
    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
        // 只能在start() 方法之前可以调用 setDaemon() 方法。一旦线程运行了，就不能修改守护状态。
        // 可以使用 isDaemon() 方法来检查线程是否是守护线程（方法返回 true) 或者是使用者线程 (方法返回 false)。
        setDaemon(true);
    }


    /**
     * 实现run()方法。它是无限循环来获取当前日期并调用 clean() 方法.
     */
    @Override
    public void run() {
        while (true) {
            Date date = new Date();
            clean(date);
        }
    }


    /**
     * 实现 clean() 方法. 它获取最后的事件，如果它在10秒前被创建，就删除它并查看下一个事件。
     * 如果一个事件被删除，它会写一个事件信息和queue的新的大小，为了让你看到变化过程。
     */
    private void clean(Date date) {
        long difference;
        boolean delete;
        if (deque.size() == 0) {
            return;
        }
        delete = false;
        do {
            Event e = deque.getLast();
            difference = date.getTime() - e.getDate().getTime();
            if (difference > 10000) {
                System.out.printf("Cleaner: %s\n", e.getEvent());
                deque.removeLast();
                delete = true;
            }
        } while (difference > 10000);
        if (delete) {
            System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
        }
    }
}
