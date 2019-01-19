/*
 * == Created Date ==
 * Jan 18, 2019
 * 
 * == Question - Implement a callback machenism ==
 *   
 * == Notes == 
 * Pure Storage Q
 * 
 */

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
class Event1 {
	
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

class Event2 {
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
            lock.unlock(); /* Why put unlock() before cb.call()? 
            			          - a) if this line is after cb.call(), deadlock could happens:
                                    as the Lock in java is not reentrant, and invoke() could call this.register again 
            				      - b) invoke can take a long time which make everything slower  */
            cb.call();
        }
    }
    
    public void fire() {
        lock.lock();
        isFired = true;
        while (!eventQueue.isEmpty()) {
            lock.unlock(); // Why unlock() is before cb.call()? The same as above
            eventQueue.poll().call();
            lock.lock();
        }
        lock.unlock();
    }
}

class Event3 {
    Queue<CallBack> eventQueue = new LinkedList<>();
    boolean isFired = false;
    Lock lock;
    
    public void reg_cb(CallBack cb) {
        lock.lock();
        if (!isFired) {
            eventQueue.offer(cb);
            lock.unlock();
        }
        else {
            lock.unlock();
            cb.call();
        }
    }
    
    public void fire() {
    	
    		// putting this two lines brefore isFired = true, any problems?
        // lock.lock();
        // lock.unlock();
        
        isFired = true;
        
        // write the following 2 statements on purpose
        // they server as a barrier, before moving forward to process queue,
        // we're ensured that isFired has been set.
        lock.lock();
        lock.unlock();
        
        
        while (!eventQueue.isEmpty()) {
            CallBack cb = eventQueue.poll();
            cb.call();
        }
    }
}

class Event4 {
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

class NoLockEvent {
    private Queue<CallBack> queue = new LinkedList<>();
    private boolean fired = false;
    
    public void fire() {
        while (true){
            CallBack callBack = queue.poll();
            if (callBack == null) {
                break;
            }
            callBack.call();
        }
        fired = true;
    }
    
    public void register(CallBack callBack) {
        if (fired) {
            callBack.call();
        } else {
            queue.offer(callBack);
            if (fired) { // race condition
                CallBack callBack1 = queue.poll();
                if (callBack1 != null) {
                    callBack1.call();
                }
            }
        }
    }
}