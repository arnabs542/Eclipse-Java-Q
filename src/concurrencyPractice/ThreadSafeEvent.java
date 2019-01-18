package concurrencyPractice;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

class CallBack {
	String name;
    
    public CallBack() {
    }
    
    public CallBack(String name) {
    		this.name = name;
    }
    
    public void call() {
    		System.out.println("CallBack Event " + this.name + "is running now");
    }
}

public class ThreadSafeEvent {
	
}
/*
 * Single Thread Event Class
 * 
 * If this class is used in multi-thread condition:
 * we share a mutable variable upon different threads without synchronizing the access to this variable
 *    which results in a race condition.
 * 
 * - if say register finished if check(isFired = false) and then fire is run and finished(isFired = true),
 * then cb in register is left in the queue without anyone running.
 * 
 * Thread Interference error
 * Memory consistency error
 * 
 * */
class Event0 {
	
    Queue<CallBack> eventQueue = new ArrayDeque<>();
    boolean isFired = false;
    
    public void reg_cb(CallBack cb) {
        if (!isFired) {
        		eventQueue.offer(cb);
        } else {
        		cb.call();
        }
    }
    
    public void fire() {
    		isFired = true; // what's the benefit of isFired = true
        while (!eventQueue.isEmpty()) {
        		CallBack cb = eventQueue.poll();
        		cb.call();
        }
    }
}

// add synchronized keyword

/* Internally Java uses a so called monitor (aka. monitor lock or intrinsic lock) in order to manage synchronization. 
 * This monitor is bound to an object, e.g. when using synchronized methods each method share the same monitor of the corresponding object.
 * 
 * All implicit monitors implement the reentrant characteristics. 
 * Reentrant means that locks are bound to the current thread. 
 * A thread can safely acquire the same lock multiple times without running into deadlocks 
 *   (e.g. a synchronized method calls another synchronized method on the same object).

 * */

class Event2a {
    Queue<CallBack> eventQueue = new ArrayDeque<>();
    boolean isFired = false;
    Lock lock;
    
    public void reg_cb(CallBack cb) {
        lock.lock();
        if (!isFired) {
        		// lock.lock(); // Why not put the lock here?
            eventQueue.offer(cb);
            lock.unlock();
        } else {
            lock.unlock(); // Why unlock() is before cb.call();? 
            			      // - a) invoke(), could call this.register again and we are going to have a deadlock (because Mutex is not reentrant?)
            				  // - b) invoke can take a long time which make everything slower
            cb.call();
        }
    }
    
    public void fire() {
        lock.lock();
        isFired = true;
        while (!eventQueue.isEmpty()) {
            CallBack cb = eventQueue.poll();
            lock.unlock();
            cb.call();
            lock.lock();
        }
        lock.unlock();
    }
}

class Event2b {
    private Queue<CallBack> queue = new ArrayDeque<CallBack>();
    private boolean fired = false;
    private Lock lock;
    
    public void fire() {
        lock.lock();
        while (!queue.isEmpty()) {
            Queue<CallBack> tmp = new LinkedList<CallBack>(queue);
            Queue<CallBack> q2 =  queue;
            queue = tmp;
            queue.clear();
            lock.unlock();
            while (!q2.isEmpty()) {
                q2.poll().call();
            }
            lock.lock();
        }
        fired = true;
        lock.unlock();
    }
    
    public void register(CallBack cb) {
        lock.lock();
        if (fired) {
            lock.unlock();
            cb.call();
        } else {
            queue.offer(cb);
            lock.unlock();
        }
    }
}


