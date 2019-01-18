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

/* == Data race ==
 * Two oprations conflict with each other if they operate on the same memory location,
 * and at least one of them is a write.
 * 
 * Three factors to cause a data race:
 * 1. More than one operations work on the dame memory location
 * 2. At least one operation is a write
 * 3. At least two of those operations are concurrent
 * 
 * 
 * == Synchronization ==
 * The coordination of events to operate a sysrem in union
 * Impose orders on concurrent events
 * 
 * How?
 * - Locks (synchronized, mutual exclusion)
 * - concurrent data structures
 * - wait / notify (condition synchronization)
 * - volatile
 * 
 * == Mutual Exclusion, Critical Section
 * - mutual exclusion : No two concurrent processes are in their critical section at the same time
 * - critical section : A part of a multi-process program that may not be concurrently executed
 *                      by more than one of the program's processes / threads
 * 
 * How to create critical section?
 * - By locks
 * 
 * lock() - wait no one is the critical section and go into the it
 * unlock() - leace the critical section and mark no one is here
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

/* Frequently asked interviews questions related to concurrency
 * 
 * 1. HashMap
 * 2. Blocking Queue
 * 3. Volatile Test
 * 
 * */

/* == Frequently asked in real-life interviews == 
 * 
 * 1. If we want to make it thread safe, where shall we put synchronized keyword to? Why? 
 * 
 * 2. Can you make your design better?
 * 
 * 	- number of operations to read >> write : use ReadWriteLock
 *  - Synchronized hash map VS concurrent hash map (ConcurrentHashMap)
 * 
 * Trick: Separating HashMap into segments and perform sync operations on each one of them 
 * separately (sharding)
 * 
 * */

/* == Conditions to form a deadlock ==
 * 1. Mutulal Exclusion
 * At least one resource must be helad in a non-shareable mode.
 * Only one process can use the resource at any given instant of time.
 * 
 * 2. Hold and Wait (aka Resource Holding) 
 * A process is currently holding at least one resouce and requesting additional resouces
 * which are being held
 * 
 * 3. No Preemption
 * A resource can be released only voluntarily by the process holding it.
 * Solution: time wait
 * 
 * 4. Circular Wait
 * acquire the locks in order
 * */

/* == Deadlock vs. Livelock == 
 * 
 * 
 * */
