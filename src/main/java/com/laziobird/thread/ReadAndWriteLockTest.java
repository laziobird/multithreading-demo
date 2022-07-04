package com.laziobird.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * ReetrantReadWriteLock 读写锁的简单实现，读锁并不限制并发读的情况
 * @author jiangzhiwei
 *
 */
public class ReadAndWriteLockTest {

    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        //同时读、写
        ExecutorService service = Executors.newCachedThreadPool();
        
        Runnable read1 = new Runnable() {
            @Override
            public void run() {
                readFile(Thread.currentThread());
            }
        };
        
        Runnable read2 = new Runnable() {
            @Override
            public void run() {
                readFile(Thread.currentThread());
            }
        };
        
        service.execute(read1);
        service.execute(read2);
        service.execute(new Runnable() {
            @Override
            public void run() {
                writeFile(Thread.currentThread());
            }
        });
    }

    // 读操作
    public static void readFile(Thread thread) {
    	System.out.println(thread.getName()+"进入读，获取读锁中");
        lock.readLock().lock();
        boolean writeLock = lock.isWriteLocked();
        if (!writeLock) {
            System.out.println(thread.getName()+"当前拿到读锁！");
        }
        try {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行读操作……");
            }
            System.out.println(thread.getName() + ":读操作完毕！");
        } finally {
            System.out.println("释放读锁！");
            lock.readLock().unlock();
        }
    }

    // 写操作
    public static void writeFile(Thread thread) {
    	System.out.println(thread.getName()+"进入写，获取写锁中");
        lock.writeLock().lock();
        boolean writeLock = lock.isWriteLocked();
        if (writeLock) {
            System.out.println(thread.getName()+"当前拿到写锁！");
        }
        try {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行写操作……");
            }
            System.out.println(thread.getName() + ":写操作完毕！");
        } finally {
            System.out.println("释放写锁！");
            lock.writeLock().unlock();
        }
    }
}