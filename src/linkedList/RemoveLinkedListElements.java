/*
 * Created Date: June 6, 2018
 * 
 * Question - Remove Linked List Elements (easy):
 *   Remove all elements from a linked list of integers that have value val.
 *   
 *   Example: 
 *   Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6, Return: 1 --> 2 --> 3 --> 4 --> 5  
 * 
 */


package linkedList;

public class RemoveLinkedListElements {
	
	public ListNode removeElements(ListNode head, int val) {   
	    ListNode dummyHead = new ListNode(0);
	    ListNode curr = dummyHead;
	    while (head != null) {
	      if (head.value == val) {
	        head = head.next;
	      } else {
	        curr.next = head;
	        head = head.next;
	        curr = curr.next;
	      }
	    }
	    curr.next = null; //!!!!
	    return dummyHead.next;
	  }

}

// Common mistakes when solving problems related to Linked List: 
// 1. lose the head
// 2. multiple references
// 3. forget to let the tail of the list becoming null