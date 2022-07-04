package com.laziobird.jmh;
/**
 * Synchronized 异步方式和异步代码块案例
 * 反编译指令
 * 1、编译class 文件: javac -encoding UTF-8 SynchronizedTest.java 
 * 2、反编译的class文件 SynchronizedTest.class 
 * @author jiangzhiwei
 *
 */
public class SynchronizedTest {
	
	

	// 关键字在方法上，锁为当前实例
	  public synchronized void methodA() {
	      // code
	  }	  
	  // 关键字在代码块上，锁为括号里面的对象
	  public void methodB() {
	      Object obj = new Object();
	      synchronized (obj) {
	          // code
	      }
	  }
	  
	  
}
