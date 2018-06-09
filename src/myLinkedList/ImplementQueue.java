/*
 * Created Date: June 7,2018
 * 
 * Practice Class 7: Implement data structure Queue with Linked List
 * 
 * Mirror Question: 
 *   Implement Queue with array;
 *   Implement Stack with Linked List;
 * 
 */

package myLinkedList;

public class ImplementQueue {
	
	public int size;
	
	public ListNode first;
	public ListNode tail;
	
	public ImplementQueue() {		
		first = null;
		tail = null;
		size = 0;
	}
	
	public void offer(int val) {
		if (first == null) {
			first = new ListNode(val);
			tail = first;
		} else {
			tail.next = new ListNode(val);
			tail = tail.next;		
		}
		this.size++;
	}
	
	public Integer poll() {
		if (first == null) {
			return null;
		} else {
			int result = first.value;
			first = first.next;
			if (first == null) { // forgot to process tail!!!
				tail = null; 
			}
			this.size--;
			return result;
		}		
	}
	
	public Integer peek() {
		if (first == null) {
			return null;
		} else {
			return first.value;
		}
	}
	
	public Integer size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
			
		ImplementQueue myQ = new ImplementQueue();
		
		/* Test Case 0*/
		System.out.println(myQ.size()); // expected: 0
		System.out.println(myQ.isEmpty()); // expected: true
		
		/* Test Case 1*/
		myQ.offer(1);
		myQ.offer(2);
		myQ.offer(3); // 3 -> 2 -> 1 -- head
		System.out.println(myQ.size()); // expected: 3
		System.out.println(myQ.isEmpty()); // expected: false
		
		/* Test Case 2*/
		System.out.println(myQ.peek()); // expected: 1
		
		/* Test Case 3*/
		System.out.println(myQ.poll()); // expected: 1 // 3 -> 2 -- head
		System.out.println(myQ.poll()); // expected: 2 // 3 -- head
		System.out.println(myQ.poll()); // expected: 3
		System.out.println(myQ.poll()); // expected: null
		System.out.println(myQ.size()); // expected: 0
		System.out.println(myQ.isEmpty()); // expected: true
	}
}
