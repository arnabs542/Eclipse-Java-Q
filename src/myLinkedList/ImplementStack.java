/*
 * Created Date: June 7,2018
 * 
 * Practice Class 7: Implement data structure Stack with Linked List
 * 
 * Mirror Question: 
 *   Implement Queue with array;
 *   Implement Queue with Linked List; 
 *   
 * Updated:
 *   June 14, 2018: exception, some syntax simplification
 * 
 */

package myLinkedList;

import java.util.EmptyStackException;

public class ImplementStack {
	
	public ListNode top;
	public int size;
	
	public ImplementStack() {
		top = null;
		size = 0;
	}
	
	public void push(Integer val) {
		ListNode node = new ListNode(val);
		node.next = top;
		top = node;
		size++;	
	}
	
	public Integer pop() {
		if (top == null) {
			throw new EmptyStackException();
		} else {
			int temp = top.value;
			top = top.next;
			size--;
			return temp;
		}
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public Integer size() {
		return size;
	}
	
	public Integer peek() {
		if (top == null) {
			throw new EmptyStackException();
		} else {
			return top.value;
		}
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
			
		ImplementStack myStack = new ImplementStack();
		
		/* Test Case 0*/
		System.out.println("---< Test Case 0 >---");
		
		System.out.println(myStack.size()); // expected: 0
		System.out.println(myStack.isEmpty()); // expected: true
		
		/* Test Case 1*/
		System.out.println("---< Test Case 1 >---");
		
		myStack.push(1);
		myStack.push(2);
		myStack.push(3); // bottom || 1 2 3
		System.out.println(myStack.size()); // expected: 3
		System.out.println(myStack.isEmpty()); // expected: false
		
		/* Test Case 2*/
		System.out.println("---< Test Case 2 >---");
		
		System.out.println(myStack.peek()); // expected: 3
		System.out.println(myStack.pop()); // expected: 3  // bottom || 1 2 
		System.out.println(myStack.pop()); // expected: 2, // bottom || 1 
		System.out.println(myStack.pop()); // expected: 1
		
		/* Test Case 3*/
		System.out.println("---< Test Case 3 >---");
		System.out.println(myStack.size()); // expected: 0
		System.out.println(myStack.isEmpty()); // expected: true
		System.out.println(myStack.pop()); // expected: throw exception: java.util.EmptyStackException		
	}

}
