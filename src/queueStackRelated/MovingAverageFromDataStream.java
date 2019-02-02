/*
 * == Created Date ==
 * Feb 1, 2019
 * 
 * == Question - Moving Average from Data Stream ==
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *   
 * == Example == 
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 * 
 * == Notes == 
 * LeetCode 346*(E)
 * 
 */


package queueStackRelated;

import java.util.ArrayDeque;
import java.util.Queue;

public class MovingAverageFromDataStream {

	/**
	 * Your MovingAverage object will be instantiated and called as such:
	 * MovingAverage obj = new MovingAverage(size);
	 * double param_1 = obj.next(val);
	 */
	
	/* ----- < Solution 1 - Use queue > -----
	 * Time Complexity: O(1);
	 * Space Complexity: O(n); n is the size of the window
	 * 
	 * */
	class MovingAverage {
	    private int size;
	    private Queue<Integer> queue;
	    private int sum = 0;
	    
	    /** Initialize your data structure here. */
	    public MovingAverage(int size) {
	        this.size = size;
	        queue = new ArrayDeque<>();
	    }
	    
	    public double next(int val) {
	        if (queue.size() == size) {
	            sum -= queue.poll();
	        }
	        queue.offer(val);
	        sum += val;
	        System.out.println(sum);
	        return sum / (double) queue.size();
	    }
	}
    
	/* ----- < Solution 2 - Use circular array > -----
	 * Time Complexity: O(1);
	 * Space Complexity: O(n); n is the size of the window
	 * 
	 * */
	class MovingAverageII {

	    private int windowSize;
	    private int sum = 0;
	    private int[] window; // implement queue with array
	    private int curSize = 0;
	    private int head;
	    private int tail;
	    
	    /** Initialize your data structure here. */
	    public MovingAverageII(int size) {
	        windowSize = size;
	        window = new int[size];
	    }

	    public double next(int val) {
	    		// if current window size is equals to given window size, poll a node from the queue
	        if (curSize == windowSize) {
	            head = head == windowSize ? 0 : head;
	            sum -= window[head++];
	            curSize--;
	        } 
	        // offer a node to the queue
	        tail = tail == windowSize ? 0 : tail;
	        window[tail++] = val;
	        sum += val;
	        curSize++;
	        
	        return sum / (double) curSize;
	    }
	}
}
