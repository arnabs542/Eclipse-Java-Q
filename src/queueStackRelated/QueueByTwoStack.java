/*
 * Created Date: May 15,2018
 * Question: Queue By Two Stacks
 * Implement a queue by using two stacks. The queue should provide size(), isEmpty(), offer(), poll() and peek() operations. 
 * When the queue is empty, poll() and peek() should return null.
 * 
 * Updated: June 1, 2018
 * Time Complexity Analysis
 */

package queueStackRelated;

import java.util.Deque;
import java.util.LinkedList;

public class QueueByTwoStack {
		
	Deque<Integer> inStack = new LinkedList<>();
	Deque<Integer> outStack = new LinkedList<>();
		
	public Integer size() {
		return inStack.size() + outStack.size();
	}
	
	public boolean isEmpty() {
		if(inStack.isEmpty() && outStack.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void offer(int element) {		
		inStack.push(element);	
	}
	
	// Time Complexity
	// ## worst case, when outStack is empty, do the following:
	// (1) pop all the elements of inStack	O(n)
	// (2) push all the elements in outStack O(n)
	// (3) call outStack.pop					O(1)
	// 	thus, worst case -> O(n)
	//
	// ## amortized time
	// example: assume poll 1000 times
	//		1st call: 1000 + 1000 + 1 = 2001
	//		2nd call: 1
	//		3rd call: 1
	// 		...
	//		nth call: 1
	//  thus, amortized time = (2001 + 999) / 1000 = O(3) = O(1)
	
	// Space Complexity: O(1);
	
	public Integer poll() {		
		if(outStack.isEmpty()) {
			if(inStack.isEmpty()) {
				return null;
			}
			else {
				while(inStack.isEmpty() == false) {
					outStack.push(inStack.pop());
				}
			}
		}
		return outStack.pop();
	}
	
	public Integer peek() {	
		if(outStack.isEmpty()) {
			if(inStack.isEmpty()) {
				return null;
			}
			else {
				while(inStack.isEmpty() == false) {
					outStack.push(inStack.pop());
				}
			}
		}
		return outStack.peek();
	}

	public static void main(String[] args) {	
		
		QueueByTwoStack myQ = new QueueByTwoStack();
		System.out.println(myQ.isEmpty());
		System.out.println("size = " + myQ.size());
		System.out.println("peek " + myQ.peek());
		
		myQ.offer(1);
		myQ.offer(2);
		System.out.println("peek " + myQ.peek());
		myQ.offer(3);
		System.out.println("peek " + myQ.peek());
		
		System.out.println(myQ.isEmpty());
		System.out.println("peek " + myQ.peek());
		System.out.println("size = " + myQ.size());
		System.out.println("poll: " + myQ.poll());
		System.out.println("size = " + myQ.size());
		System.out.println("peek " + myQ.peek());
		System.out.println("poll: " + myQ.poll());
		System.out.println("poll: " + myQ.poll());
	}
}

