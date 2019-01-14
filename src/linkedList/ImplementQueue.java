/*
 * Created Date: June 7,2018
 * 
 * Practice Class 7: Implement data structure Queue with Linked List
 * 
 * Mirror Question: 
 *   Implement Queue with array;
 *   Implement Stack with Linked List;
 *   
 *   3 -> 2 -> 1
 *  first      last
 *  
 *  offer() :  4 -> 3 ..
 *           first 
 * 
 *  poll()
 * 
 * 
 *  1 -> 2 -> 3
 * first     tail
 * 
 * 
 * 
 */

package linkedList;

import java.util.NoSuchElementException;

/*   
 * 
 * 
 *    3 -> 2 -> 1
 *  first      last
 *  
 *  offer() :  4 -> 3 ..
 *           first 
 * 
 *  poll() : fail, becase we lost the previous node of last
 * -----------------------------------------------------------
 * 
 *  1 -> 2 -> 3
 * first     tail
 * 
 * offer() :  ... 3 -> 4
 *                 tail
 *                 
 * poll() :   1   2 -> 3             
 *              first
 */

public class ImplementQueue {
	
	public int size;
	
	public ListNode first;
	public ListNode last;
	
	public ImplementQueue() {		
		first = null;
		last = null;
		size = 0;
	}
	
	public void offer(int val) {
		ListNode newNode = new ListNode(val);
		if (last != null) {
			last.next = newNode;
		}
		last = newNode;
		this.size++;
		if (first == null) {
			first = last;
		}		
	}
	
	public Integer poll() {
		if (first == null) {
			throw new NoSuchElementException();
		} else {
			int result = first.value;
			first = first.next;
			if (first == null) { // forgot to process tail!!!
				last = null; 
			}
			this.size--;
			return result;
		}		
	}
	
	public Integer peek() {
		if (first == null) {
			throw new NoSuchElementException();
		} else {
			return first.value;
		}
	}
	
	public Integer size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
			
		ImplementQueue myQ = new ImplementQueue();
		
		/* Test Case 0*/
		System.out.println("---< Test Case 0 >---");
		System.out.println(myQ.size()); // expected: 0
		System.out.println(myQ.isEmpty()); // expected: true
		
		/* Test Case 1*/
		System.out.println("---< Test Case 1 >---");
		myQ.offer(1);
		myQ.offer(2);
		myQ.offer(3); // 3 -> 2 -> 1 -- head
		System.out.println(myQ.size()); // expected: 3
		System.out.println(myQ.isEmpty()); // expected: false
		
		/* Test Case 2*/
		System.out.println("---< Test Case 2 >---");
		System.out.println(myQ.peek()); // expected: 1
		System.out.println(myQ.poll()); // expected: 1 // 3 -> 2 -- head
		System.out.println(myQ.poll()); // expected: 2 // 3 -- head
		System.out.println(myQ.poll()); // expected: 3
		
		/* Test Case 3*/
		System.out.println("---< Test Case 3 >---");
		System.out.println(myQ.size()); // expected: 0
		System.out.println(myQ.isEmpty()); // expected: true
		System.out.println(myQ.poll()); // expected: java.util.NoSuchElementException

	}
}
