/*
 * Created Date: May 15,2018
 * 
 * Question-  Queue By Two Stacks
 *   Implement a queue by using two stacks. The queue should provide size(), isEmpty(), offer(), poll() and peek() operations. 
 *   When the queue is empty, poll() and peek() should return null.
 * 
 * Updated: 
 *   June 1, 2018: Time Complexity Analysis
 *   June 30, 2018: Review
 *   August 5, 2018: Review. 
 *     Mistakes: 1. Deque<Integer> inStack = new ArrayList<>();
 *     Mistakes: 2. Deque don't have function top(), only peek()
 * 
 */

package queueStackRelated;

import java.util.Deque;
import java.util.LinkedList;

public class QueueByTwoStack {
		
	Deque<Integer> inStack; // always insert elements to inStack
	Deque<Integer> outStack; // always poll elements from outStack
	
	public QueueByTwoStack () {
		inStack = new LinkedList<>();
		outStack = new LinkedList<>();
	}
		
	public Integer size() {
		return inStack.size() + outStack.size();
	}
	
	public boolean isEmpty() {
		if (inStack.isEmpty() && outStack.isEmpty()) {
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
	// (1) pop all the elements of inStack --- O(n)
	// (2) push all the elements in outStack --- O(n)
	// (3) call outStack.pop --- O(1)
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
		move();
		return outStack.isEmpty() ? null : outStack.pop(); 
	}
	
	public Integer peek() {	
		move();
		return outStack.isEmpty()? null : outStack.peek(); 
	}
	
	private void move() {
		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
		}
	}

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		QueueByTwoStack myQ = new QueueByTwoStack();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		System.out.println(myQ.isEmpty()); // expected: true
		System.out.println("size = " + myQ.size()); // expected: 0
		System.out.println("peek " + myQ.peek()); // expected: null
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		myQ.offer(1);
		myQ.offer(2);
		System.out.println("peek " + myQ.peek()); // expected: 1
		myQ.offer(3);
		System.out.println("peek " + myQ.peek()); // expected: 1
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		System.out.println(myQ.isEmpty()); // expected: false
		System.out.println("size = " + myQ.size()); // expected: 3
		System.out.println("poll: " + myQ.poll()); // expected: 1
		System.out.println("size = " + myQ.size()); // expected: 2
		System.out.println("peek " + myQ.peek()); // expected: 2
		System.out.println("poll: " + myQ.poll()); // expected: 2
		System.out.println("poll: " + myQ.poll()); // expected: 3
		
		/* Test Case 3 */
		System.out.println("\n---< Test Case 3 >---");	
	}
}

