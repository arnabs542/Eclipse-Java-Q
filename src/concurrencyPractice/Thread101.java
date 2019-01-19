package concurrencyPractice;

/* Two ways of creating threads */

/* 1. Extends Thread */
class HelloThread extends Thread { // is-a
	@Override
	public void run() {
		System.out.println("Hello from: " + Thread.currentThread().getName());
	}
}

/* 2. Implements Runnable */
class HelloRunnable implements Runnable { // has-an-ability
	@Override
	public void run() {
		System.out.println("Hello from: " + Thread.currentThread().getName());
	}
}

/* == A Thread's Life Cycle ==
 * start() 
 *   -> Mark it as "ready to run" stat
 *   -> put into the ready to run queue and wait for the scheduler to assign its time frame (quota) for running. 
 *   -> End of quota or stat change by differrent event:
 *           
 *      - Thread.sleep() -> removed from the ready to run queue, and move to sleeping thread pool
 *      - Object.wait() -> removed from the ready to run queue, and move to the conditions' queue for 
 *                         signal to put back to the ready queue
 *      - Thread.yield() -> did not change the ready to run stat, but preempt itself the current quota,
 *                          put back to the ready queue again for the next quota. 
 *                          Not used frequently as sleep(). WHY? uncertainty.
 * 
 * 
 * */
public class Thread101 {
	
	public void someMethod () {
		System.out.println("Hello from: " + Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		
		Thread newThread1 = new HelloThread();
		newThread1.start();
		
		(new HelloThread()).start();
		
		Thread newThread2 = new Thread(new HelloRunnable());
		newThread2.start();
		
		/* lambda */
		Thread101 testObj = new Thread101();
		new Thread(() -> testObj.someMethod(), "t3").start();
		
		new Thread(testObj::someMethod, "t4").start(); // another lambda syntax
		
		// syntax before Java8
		new Thread(new Runnable() {
			@Override
			public void run() {
				testObj.someMethod();
			}
		}).start();;
	}
}


