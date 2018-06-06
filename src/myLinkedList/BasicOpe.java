package myLinkedList;

public class BasicOpe {
	
	public static int getLength(ListNode head) {
		ListNode curr = head;
		int length = 0;
		while(curr != null) {
			length++;
			curr = curr.next;
		}
		return length;
	}
	
	// ?
	// Integer type has "null", but int type doesn't
	public static Integer getIndexVal(ListNode head, int index) {
		
		ListNode curr = head;
		for(int i = 0; i < index; i++) {
			curr = curr.next;
		}
		if(curr == null) return null;
		return curr.value;
	} 
	// mistake: i <= index
	
	public static ListNode appendHead(ListNode head, int newValue) {
		ListNode newHead = new ListNode(newValue);
		newHead.next = head;
//		head = newHead;
//		return head; // no need
		return newHead;
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
