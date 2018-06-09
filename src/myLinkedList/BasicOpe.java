/*
 * Created Date: May 29,2018
 * 
 * Application: Practice Class basic operation of linked list
 * 
 */

package myLinkedList;

public class BasicOpe {
	
	public static int getLength(ListNode head) {
		ListNode curr = head;
		int length = 0;
		while (curr != null) {
			length++;
			curr = curr.next;
		}
		return length;
	}
	
	public static Integer getIndexVal(ListNode head, int index) {		
		ListNode curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		if (curr == null) {
			return null;// Integer type has "null", but int type doesn't
		}
		return curr.value;
	} 
	// mistake: i <= index
	
	public static ListNode appendHead(ListNode head, int val) {
		ListNode newHead = new ListNode(val);
		newHead.next = head;
//		head = newHead; 
//		return head; // Java is pass by Value, "head" is just a copy, operate it here won't have any effect
		return newHead;
	}
	
	public static ListNode appendTail(ListNode head, int val) {
		if (head == null) {
			return new ListNode(val);
		} else {
			ListNode tail = head;
			while (tail.next != null) {
				tail = tail.next;
			}
			tail.next = new ListNode(val);
			return head;
		}
	}
	
	public static void main(String[] args) {
		
		ListNode head = new ListNode(1);
		head.next = new ListNode(3);
		head.next.next = new ListNode(5);
		head.next.next.next = new ListNode(7);
		head.next.next.next.next = new ListNode(9);
		head.printList(head);
		
		System.out.println(getLength(head));
	}	
}
