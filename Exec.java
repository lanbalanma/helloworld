package com.test.thread;

import java.util.List;

public class Exec implements Runnable {
	@Override
	public void run() {
		while (true) {
			synchronized (TestQueues.queue) {
				while (TestQueues.queue.isEmpty()) { //
					try {
						TestQueues.queue.wait(); // 队列为空时，使线程处于等待状态
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("wait...");
				}
				TestQueues.Task t = TestQueues.queue.remove(0); // 得到第一个
				t.test(); // 执行该任务
				System.out.println("end");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Exec e = new Exec();
		for (int i = 0; i < 3; i++) {
			new Thread(e).start(); // 开始执行时，队列为空，处于等待状态
		}
		// 上面开启两个线程执行队列中的任务，那就是先到先得了
		// 添加一个任务测试
		for (int i = 0; i < 20; i++) {
			TestQueues.Task t = new TestQueues.Task("inovke:" + i);
			TestQueues.add(t); // 执行该方法，激活所有对应队列，那两个线程就会开始执行啦
			
			Thread.sleep(1000);
		}
	}

}