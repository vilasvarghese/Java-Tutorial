package com.gpcoder.threadpool.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class RecursiveActionExample {
	public static void main(String[] args) {

		final AtomicLong sizeWindows = new AtomicLong();
		final AtomicLong sizeApps = new AtomicLong();
		final AtomicLong sizeDocuments = new AtomicLong();

		// Create ForkJoinPool using the default constructor.
		ForkJoinPool pool = new ForkJoinPool();

		// Create three FolderProcessor tasks. Initialize each one with a different
		// folder path.
		SizeOfFileAction system = new SizeOfFileAction("C:/Windows", sizeWindows);
		SizeOfFileAction apps = new SizeOfFileAction("C:/Program Files", sizeApps);
		SizeOfFileAction documents = new SizeOfFileAction("C:/Documents And Settings", sizeDocuments);

		// Execute the three tasks in the pool using the execute() method.
		pool.execute(system);
		pool.execute(apps);
		pool.execute(documents);

		// Write to the console information about the status of the pool every second
		// until the three tasks have finished their execution.
		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while ((!system.isDone()) || (!apps.isDone()) || (!documents.isDone()));

		// Shut down ForkJoinPool using the shutdown() method.
		pool.shutdown();

		// Write the number of results generated by each task to the console.
		System.out.printf("Size of Windows: %d bytes \n", sizeWindows.get());
		System.out.printf("Size of Apps: %d bytes \n", sizeApps.get());
		System.out.printf("Size of Documents: %d bytes \n", sizeDocuments.get());
	}
}
