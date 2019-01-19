package concurrencyPractice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Executors101 {

	public static void main(String[] args) {
		
		/* == Threads ==
		 * Java supports Threads since JDK 1.0. 
		 * 
		 * Before starting a new thread you have to specify the code to be executed by this thread, 
		 *   often called the task，often done by implementing Runnable - 
		 *   a functional interface defining a single void no-args method run() 
		 *   
		 * */
		Runnable task1 = () -> {
		    try {
		        String name = Thread.currentThread().getName();
		        System.out.println("Foo " + name);
		        TimeUnit.SECONDS.sleep(1);
		        System.out.println("Bar " + name);
		    }
		    catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		};

//		task1.run(); // main thread will run this
//
//		Thread thread = new Thread(task1);
//		thread.start();
//
//		System.out.println("Done!");
		
		
		/* == Executors ==
		 * The class Executors provides convenient factory methods 
		 *   for creating different kinds of executor services. 
		 * 
		 * - Executors have to be stopped explicitly - otherwise they keep listening for new tasks.
		 * */
		ExecutorService executor1 = Executors.newSingleThreadExecutor();
		executor1.submit(() -> {
		    String threadName = Thread.currentThread().getName();
		    System.out.println("Hello " + threadName);
		});
		
		/* == Shutdown Executors ==
		 * The executor shuts down softly by waiting a certain amount of time 
		 *   for termination of currently running tasks. 
		 * After a maximum of five seconds the executor finally shuts down by interrupting all running tasks.
		 * 
		 * */
		try {
		    System.out.println("attempt to shutdown executor");
		    executor1.shutdown();
		    executor1.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		    System.err.println("tasks interrupted");
		} finally {
		    if (!executor1.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    executor1.shutdownNow();
		    System.out.println("shutdown finished");
		}
		
		/* == Callables == 
		 * In addition to Runnable executors support another kind of task named Callable. 
		 * 
		 * This lambda expression defines a callable returning an integer after sleeping for one second:
		 * */
		Callable<Integer> task2 = () -> {
		    try {
		        TimeUnit.SECONDS.sleep(1);
		        return 123;
		    } catch (InterruptedException e) {
		        throw new IllegalStateException("task interrupted", e);
		    }
		};
		
		/* == Future ==
		 * Callables can be submitted to executor services just like runnables. 
		 * But what about the callables result? 
		 * Since submit() doesn't wait until the task completes, 
		 *   the executor service cannot return the result of the callable directly. 
		 * Instead the executor returns a special result of type Future 
		 *   which can be used to retrieve the actual result at a later point in time.
		 * */
		ExecutorService executor2 = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor2.submit(task2);

		System.out.println("future done? " + future.isDone());

		Integer result = -1;
		try {
			result = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("future done? " + future.isDone());
		System.out.print("result: " + result);

		executor2.shutdown();
		
		/* == Timeouts ==
		 * Any call to future.get() will block and wait until the underlying callable has been terminated.
		 * In the worst case a callable runs forever - thus making your application unresponsive. 
		 * You can simply counteract those scenarios by passing a timeout:
		 * 
		 * */
		ExecutorService executor3 = Executors.newFixedThreadPool(1);

		Future<Integer> future3 = executor3.submit(() -> {
		    try {
		        TimeUnit.SECONDS.sleep(2);
		        return 123;
		    }
		    catch (InterruptedException e) {
		        throw new IllegalStateException("task interrupted", e);
		    }
		});

		try {
			future3.get(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
