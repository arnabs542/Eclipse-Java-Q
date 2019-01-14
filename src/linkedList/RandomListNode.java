/*
 * Created Date: July 28, 2018
 * 
 * Application: Used by problem "Deep Copy LinkedList With Random Pointer"
 * 
 */

package linkedList;

public class RandomListNode {
	
	int value;
	public RandomListNode next;
	public RandomListNode random; 
	
	public RandomListNode(int v) {
		this.value = v;
		this.next = null;
		this.random = null;
	}
}
