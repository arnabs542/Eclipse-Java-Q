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
	
	public void push(ListNode node) {
		if (size == 0) {
			head = node;
		} else {
			head.next = node;
			head = node;
		}
	}
	
	public ListNode pop() {
		return head;
	}

}
