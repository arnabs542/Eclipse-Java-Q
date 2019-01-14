package concurrencyPractice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* # Condition Synchronization */

/////////////////////////////////////////////////////////////////////////////////////////////
/* -------------------------------< BlockingQueue >-------------------------------------
 * - When the queue is empty, consumers will wait until produces provide one element
 * - When the queue is full, producers will wait until consumers release one element
 * 
 * How to implement Mutual exclusive?  -> Use locks
 * Wait -> Condition Synchronization
 * 
 * Each Java Object is a Monitor
 * Each Java Object provides two concurrency premises:
 *  1) one lock -> synchronized
 *  2) one condidtion -> wait
 */
/////////////////////////////////////////////////////////////////////////////////////////////

class BlockingQueue {
	private Queue<Integer> queue;
	private final int limit;
	
	public BlockingQueue(int limit) {
		this.queue = new LinkedList<>();
		this.limit = limit;
	}
	
	public synchronized void put(Integer element) { // synchronized(this)
		// Use "while" because after being waken up, queue may still be full
		while (queue.size() == limit) {
			try {
				this.wait(); // this.wait(); back to room#2 (wait queue)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (queue.size() == 0) {
			this.notifyAll();
		}
		queue.offer(element);
	}
	
	public synchronized Integer take() {
		while (queue.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (queue.size() == limit) {
			this.notifyAll();
		}
		return queue.poll();
	}
}

////////////////////////////////////////////////////
/* --------------< Producer >---------------------*/
////////////////////////////////////////////////////

// Why "implements Runnable" instead of "extends Thread"?
class Producer implements Runnable {
	BlockingQueue queue;
	private int[] num;
	
	public Producer(BlockingQueue queue, int[] num) {
		super();
		this.queue = queue;
		this.num = num;
	}
	
	@Override
	public void run() {
		queue.put(num[0]);
		long id = Thread.currentThread().getId();
		System.out.println("Producer " + id + " puts " + num[0]);
		num[0]++;
	}
}

////////////////////////////////////////////////////
/* --------------< Consumer >---------------------*/
////////////////////////////////////////////////////

class Consumer implements Runnable {
	BlockingQueue queue;
	
	public Consumer(BlockingQueue queue) {
		super();
		this.queue = queue;
	}
	
	@Override
	public void run() {
		long id = Thread.currentThread().getId();
		int take = queue.take();
		System.out.println("Consumer " + id + " takes " + take);
	}
}


public class ProducersAndConsumers {
	public static void main(String[] args) {
		int[] num = new int[] {0};
		BlockingQueue q = new BlockingQueue(20);
		List<Thread> threads = new ArrayList<>();
		
		for (int i = 0; i < 50; i++) {
			threads.add(new Thread(new Producer(q, num)));
		}
		
		for (int i = 0; i < 50; i++) {
			threads.add(new Thread(new Consumer(q)));
		}
		 
		for (Thread t : threads) {
			t.start();
		}
		
//		for (Thread t : threads) {
//			try {
//				t.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		System.out.println("Finished");
	}
}
