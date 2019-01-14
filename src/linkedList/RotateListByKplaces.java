/*
 * == Created Date ==
 * October 6, 2018
 * 
 * == Question - Rotate List By K places ==
 * Given a list, rotate the list to the right by k places, where k is non-negative.  
 *   
 * == Example == 
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL  
 * 
 */

package linkedList;

public class RotateListByKplaces {
	
	public ListNode rotateKplace(ListNode head, int n) {
	    if (head == null || head.next == null) {
	      return head;
	    }
	    
	    int[] len = new int[]{0};
	    head = reverse(head, len); // 5 4 3 2 1
	    
	    n %= len[0];
	    if (n != 0) {
	    		ListNode leftTail = head;
	    		for (int i = 0; i < n - 1; i++) {
	    			leftTail = leftTail.next;
	    		}
	    		ListNode rightHead = leftTail.next;
	    		leftTail.next = null; // de-link the first part from the second part
	    
	    		head = reverse(head, len); // 4 5
	      
	    		rightHead = reverse(rightHead, len); // 1 2 3
	    
	    		leftTail = head;
	    		while (leftTail.next != null) { // find the tail of the first part
	    			leftTail = leftTail.next;
	    		}
	    		leftTail.next = rightHead; // link two parts
	    } else {
	    		head = reverse(head, len);
	    }
	    return head;
	}

	private ListNode reverse(ListNode head, int[] len) {
	    ListNode pre = null;
	    ListNode cur = head;
	    while (cur != null) {
	    		ListNode next = cur.next;
	    		cur.next = pre;
	    		pre = cur;
	    		cur = next;
	    		len[0]++; // len is only used to calculate number of nodes for the whole list
	    }
	    return pre;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(1);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		RotateListByKplaces testObj = new RotateListByKplaces();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		ListNode head1 = ListNode.genLinkedList(new int[] {1,2,3,4,5});
		
		head1 = testObj.rotateKplace(head1, 2);
		head1.printList(head1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		ListNode head2 = ListNode.genLinkedList(new int[] {1,2});
		
		head2 = testObj.rotateKplace(head2, 6);
		head2.printList(head2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
