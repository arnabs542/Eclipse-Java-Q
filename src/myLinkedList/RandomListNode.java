/*
 * Created Date: July 28, 2018
 * 
 * Application: Used by problem "Deep Copy LinkedList With Random Pointer"
 * 
 */

package myLinkedList;

public class RandomListNode {
	
	int value;
	ListNode next = null;
	
	public RandomListNode random; 
	
	public RandomListNode(int v) {
		this.value = v;
		this.next = null;
		this.random = null;
	}
}