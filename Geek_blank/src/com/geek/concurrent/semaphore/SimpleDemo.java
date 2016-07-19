package com.geek.concurrent.semaphore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
public class SimpleDemo implements Runnable{
	
	private final Semaphore semp = new Semaphore(5);
	
	@Override
	public void run() {
		try {
			semp.acquire();
			// 模拟业务逻辑操作
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId()+":done!");
			semp.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(20);
		final SimpleDemo demo = new SimpleDemo();
		for (int i = 0; i < 20; i++) {
			exec.submit(demo);
		}
		exec.shutdown();
	}
}