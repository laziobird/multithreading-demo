# multithreading-demo
A Research on Multithreading Technique in Java 
 - StringAppendJmhTest.java:JMH 进行 String、StringBuffer、StringBuilder 基准测试例子
 - SynchronizedTest.java:Synchronized 异步方式和异步代码块反编译例子
```shell
反编译指令
1、编译class 文件: javac -encoding UTF-8 SynchronizedTest.java 
2、反编译的class文件 SynchronizedTest.class 
```
 - ContextSwitchTest.java:Java 线程上下文切换的例子
 ```shell
VMstat查看上下文切换
vmstat 2
```
 - ReadAndWriteLockTest.java:ReetrantReadWriteLock 读写锁的例子 
