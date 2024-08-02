package de.cwlounge.cwloungedelobbysystem.UTILS;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskAPI {
	private static TaskAPI instance = new TaskAPI();

	ThreadPoolExecutor threads = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	
	public static TaskAPI getInstance() {
		return instance;
	}
	
	public void runAsync(Runnable run) {
		threads.execute(run);
	}

	public void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
