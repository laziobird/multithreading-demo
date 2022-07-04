package com.laziobird.thread;
/**
 * 上下文切换的例子
 * @author jiangzhiwei
 * 
 */
public class ContextSwitchTest {
	public static void main(String[] args) {
		try {
			// 先让程序起来，好观察
			Thread.sleep(30000);
			System.out.println("------------------------ 上下文切换开始，默认等待30s ---------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 服务器是4 逻辑内核
		// 运行多线程，起2个并发线程
		MultiThreadTester test2 = new MultiThreadTester(2);		
		// 运行多线程，起4个并发线程
		MultiThreadTester test4 = new MultiThreadTester(4);
		// 运行多线程，起8个并发线程
		MultiThreadTester test8 = new MultiThreadTester(8);	
		// 运行多线程，起32个并发线程
		MultiThreadTester test32 = new MultiThreadTester(32);	
		test2.Start();
		
		try {
			// 先让程序起来，好观察
			Thread.sleep(30000);
			System.out.println("------------------------ 默认等待30s ---------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test4.Start();
		
		try {
			// 先让程序起来，好观察
			Thread.sleep(30000);
			System.out.println("------------------------ 默认等待30s ---------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test8.Start();
		
		try {
			// 先让程序起来，好观察
			Thread.sleep(30000);
			System.out.println("------------------------ 默认等待30s ---------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test32.Start();	
		
		try {
			// 先让程序起来，好观察
			Thread.sleep(30000);
			System.out.println("------------------------ 默认等待30s ---------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println("------------------------ 没有上下文切换 ---------");
		// 没有上下文切换
		SerialTester test1 = new SerialTester();
		test1.Start();
	}

	static class MultiThreadTester extends ThreadContextSwitchTester {
		int threadCount;
		public MultiThreadTester(int threadCount) {
			this.threadCount = threadCount;
		}
		@Override
		public void Start() {
			long start = System.currentTimeMillis();
			MyRunnable myRunnable1 = new MyRunnable();
			Thread[] threads = new Thread[threadCount];
			System.out.println(" 并发线程数: "+threadCount+"  开始启动咯！");
			// 创建多个线程
			for (int i = 0; i < threadCount; i++) {
				threads[i] = new Thread(myRunnable1);
				threads[i].start();
			}
			for (int i = 0; i < threadCount; i++) {
				try {
					// 等待一起运行完
					threads[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("multi thread ,the thread count is  "+threadCount+" | exce time: " + (end - start) + "s");
			System.out.println("counter: " + counter);
		}

		// 创建一个实现Runnable的类
		class MyRunnable implements Runnable {
			public void run() {
				while (counter < 200000000) {
					synchronized (this) {
						if (counter < 200000000) {
							increaseCounter();
						}

					}
				}
			}
		}
	}

	// 创建一个单线程
	static class SerialTester extends ThreadContextSwitchTester {
		@Override
		public void Start() {
			long start = System.currentTimeMillis();
			for (long i = 0; i < count; i++) {
				increaseCounter();
			}
			long end = System.currentTimeMillis();
			System.out.println("serial exec time: " + (end - start) + "s");
			System.out.println("counter: " + counter);
		}
	}

	// 父类
	static abstract class ThreadContextSwitchTester {
		public static final int count = 200000000;
		public volatile int counter = 0;
		public int getCount() {
			return this.counter;
		}
		public void increaseCounter() {

			this.counter += 1;
		}

		public abstract void Start();
	}
}