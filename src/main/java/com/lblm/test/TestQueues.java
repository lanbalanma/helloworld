package com.lblm.test;

import java.util.LinkedList;
import java.util.List;

public class TestQueues {
	public static List<Task> queue = new LinkedList<Task>();

	/**
	 * 假如 参数o 为任务
	 * 
	 * @param o
	 */
	public static void add(Task t) {
		synchronized (TestQueues.queue) {
			TestQueues.queue.add(t); // 添加任务
			TestQueues.queue.notifyAll();// 激活该队列对应的执行线程，全部Run起来
		}
	}

	static class Task {
		String name;

		Task(String n) {
			this.name = n;
		}

		public void test() {
			System.out.println(
					name + "  我被执行了:" + Thread.currentThread().getName());
		}
	}
}