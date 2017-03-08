package com.threadtest;
/*继承Thread类的，我们相当于拿出三件事即三个卖票10张的任务分别分给三个窗口，他们各做各的事各卖各的票各完成各的任务，
因为MyThread继承Thread类，所以在new MyThread的时候在创建三个对象的同时创建了三个线程；

实现Runnable的， 相当于是拿出一个卖票10张得任务给三个人去共同完成，new MyThread相当于创建一个任务，然后实例化三个Thread，创建三个线程即安排三个窗口去执行。*/
class MyThread1 implements Runnable {
	private int ticket = 500;
	private String name;

	public synchronized void run() {
		for (int i = 0; i < 500; i++) {
			if (this.ticket > 0) {
				System.out.println(Thread.currentThread().getName() + "卖票---->" + (this.ticket--));
			}
		}
	}
}

public class RunnableDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 设计三个线程
		MyThread1 mt = new MyThread1();
		Thread t1 = new Thread(mt, "一号窗口");
		Thread t2 = new Thread(mt, "二号窗口");
		Thread t3 = new Thread(mt, "三号窗口");
		// MyThread1 mt2 = new MyThread1();
		// MyThread1 mt3 = new MyThread1();
		t1.start();
		t2.start();
		t3.start();
	}

}