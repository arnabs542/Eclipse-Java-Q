/*
 * == Created Date == 
 * June 6, 2018
 * 
 * == Question - Remove Linked List Elements == 
 * Remove all elements from a linked list of integers that have value val.
 *   
 * == Example ==
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6, Return: 1 --> 2 --> 3 --> 4 --> 5  
 *   
 * == Notes ==
 * LeetCode 203(E)
 * Review: Jan 26, 2019
 */

package linkedList;

public class RemoveLinkedListElements {
	
	/* ----- < Solution 1 - Iterative > -----
	 * Time Complexity: O(n);
	 * Space Complexity: O(1);
	 * 
	 * */
	public ListNode removeElements(ListNode head, int val) {   
		ListNode dummyHead = new ListNode(0);
	    ListNode curr = dummyHead;
	    while (head != null) {
	    		if (head.value != val) {
	    			curr.next = head;
	    			curr = curr.next;
	    		} 
	    		head = head.next;
	    }
	    curr.next = null; //!!!!
	    return dummyHead.next;
	}

	public ListNode removeElementsII(ListNode head, int val) { 
	    ListNode dummy = new ListNode(0);
	    ListNode pre = dummy;
	    ListNode cur = head;
	    cur = head;
	    while (cur != null) {
	        ListNode next = cur.next;
	        if (cur.value == val) { // remove cur
	            pre.next = next;
	        } else { // not to remove cur
	            pre.next = cur;
	            pre = cur;
	        }
	        cur = next;
	    }
	    return dummy.next;
    }
	
	/* ----- < Solution 2 - Recursive > -----
	 * Time Complexity: O(n);
	 * Space Complexity: O(n);
	 * 
	 * */
    public ListNode removeElementsIII(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.value == val) {
            return removeElements(head.next, val);
        }
        head.next = removeElements(head.next, val);
        return head;
    }
}

// Common mistakes when solving problems related to Linked List: 
// 1. lose the head
// 2. multiple references
// 3. forget to let the tail of the list becoming null