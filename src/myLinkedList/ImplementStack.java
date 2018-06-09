/*
 * Created Date: June 7,2018
 * 
 * Practice Class 7: Implement data structure Stack with Linked List
 * 
 * Mirror Question: 
 *   Implement Queue with array;
 *   Implement Queue with Linked List; 
 * 
 */

package myLinkedList;

public class ImplementStack {
	
	public ListNode head;
	public int size;
	
	public ImplementStack() {
		head = null;
		size = 0;
	}
	
	public void push(Integer val) {
		ListNode node = new ListNode(val);
		node.next = head;
		head = node;
		size++;	
	}
	// Mistake: 
	// head.next = new ListNode(val);
	// head = head.next;
	
	public Integer pop() {
		if (this.head == null) {
			return null;
		} else {
			ListNode pre = head;
			head = head.next;
			pre.next = null;
			size--;
			return pre.value;
		}
	}
	
	public boolean isEmpty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Integer size() {
		return size;
	}
	
	public Integer peek() {
		if (head == null) {
			return null;
		} else {
			return head.value;
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
		
		/* Test Case 3*/
		System.out.println("---< Test Case 3 >---");
		System.out.println(myStack.pop()); // expected: 3  // bottom || 1 2 
		System.out.println(myStack.pop()); // expected: 2, // bottom || 1 
		System.out.println(myStack.pop()); // expected: 
		System.out.println(myStack.pop()); // expected: null
		System.out.println(myStack.size()); // expected: 0
		System.out.println(myStack.isEmpty()); // expected: true
	}

}
