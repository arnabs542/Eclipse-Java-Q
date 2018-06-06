/*
 * Created Date: June 1, 2018
 * 
 */

package myLinkedList;

public class ListNode {	
	int value;
	ListNode next = null;
	
	public ListNode(int v) {
		this.value = v;
		this.next = null;
	}
	
	public void printList(ListNode head) {
		if (head == null) {
			System.out.print("null \n");
			return;
		}
			
		while (head != null) {
			System.out.print(head.value +" ");
			head = head.next;
		}		
		System.out.print("\n");
	}
}