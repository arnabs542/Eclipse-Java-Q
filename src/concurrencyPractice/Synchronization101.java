package concurrencyPractice;

public class Synchronization101 {

}

/* == Data Race ==
 * Two oprations conflict with each other if they operate on the same memory location,
 * and at least one of them is a write.
 * 
 * Three factors to cause a data race:
 * 1. More than one operations work on the dame memory location
 * 2. At least one operation is a write
 * 3. At least two of those operations are concurrent
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
 * == Mutual Exclusion, Critical Section and Locks ==
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
 * == Mutual Exclusion in Java ==
 * 1. synchronized keyword
 * The simplest way of locking a certain method or Java class is to define the method 
 * or class with synchronized keyword
 * 
 * 
 */

class Counter1 {
	private int value;
	public Counter1(int value) {
		this.value = value;
	}
	
	public void increase() {
		synchronized(this) {
			value++;
		}
	}
	
	public synchronized void decrese() {
		value--;
	}
	
	/* the above function is equivalent to:
	 * ```
	 *  	public void decrease() {
	 *  		synchronized(this) {
	 *  			value--;
	 *  		}
	 *  }
	 * ```
	 * */
	
	public synchronized int getCount() {
		return value;
	}
}

/* 
 * synchronized on non-static method == synchronized(this)
 * static synchronized == synchronized(Counter.class) != synchronized(all instances)
 * 
 * */
class Counter2 {
	private static int value;

	public static void increase() {
		synchronized(Counter2.class) { // not ``` synchronized(Cthis) ``` as in Counter1 
			value++;
		}
	}
	
	public static synchronized void decrese() {
		value--;
	}
	
	public static synchronized int getCount() {
		return value;
	}
}


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