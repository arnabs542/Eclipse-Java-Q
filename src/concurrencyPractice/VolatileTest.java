package concurrencyPractice;

public class VolatileTest {
	/*
	  # Atomicity
	 
	  - The volatile keyword guaranteed visibility (semantically, happens-before), 
	 	muhc like acquiring and releading a lock
	 
	 	public volatile int x = 0;
	 
	  - Different to C++'s volatile: C++'s volatile is an implemnetation-level hint to the compilers
		In Java, the atomic keyword is similar to C++'s volatile

	  -	volatile can only guarantee single read/write operation is atomic.
	    Such as valut++ is read-then-update, volatile cannot guarantee its automicity
	    
	  - volatile is expensive: read ~10x slower, write ~70x slower

	 */
	
	public static volatile boolean flag = false; // Try to remove volatile and test
	
	public static class MyRunnable implements Runnable {
		@Override 
		public void run() {
			int i = 0; 
			while (!flag) {
				
				// If the flag is not marked as volatile
				// and the println() line is being commented out,
				// this thread will not finished.
				
				// The reason is related to the compiler optimization, we don't need to care about. 
				// We only need to understand this problem happens because of data race 
				System.out.println(i + " The thread is runing...");
				
				i++;
			}
			System.out.println("The thread is finished..");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread newThread = new Thread(new MyRunnable());
		newThread.start();
		Thread.sleep(1000);
		flag = true;
		System.out.println("Main thread is finished..");
	}
}
