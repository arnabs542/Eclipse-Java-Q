/*
 * Created Date: May 17,2018
 * Question: Stack With Min()
 * Enhance the stack implementation to support min() operation. 
 * min() should return the current minimum value in the stack. 
 * If the stack is empty, min(), top() should return -1.
 * 
 * Updated: June 1, 2018
 * 
 */

package queueStackRelated;

import java.util.Deque;
import java.util.LinkedList;

public class StackWithMinOpe {
	
	Deque<Integer> stack;
	Deque<Integer> minStack;
	
	public StackWithMinOpe() {
		stack = new LinkedList<>();
		minStack = new LinkedList<>();
	}
	
	public int pop() {
		if (stack.size() == 0) {
			return -1;
		}
		
		if (stack.peek() == minStack.peek()) {
			minStack.pop();
		}		
		return stack.pop();
	}
	
	public void push(int element) {
			
		if (stack.size() == 0) {			
			minStack.push(element);
		} else {			
			if (element <= minStack.peekFirst()) {
				minStack.push(element);
			}
		}
		stack.push(element);	
	}
	  
	public int top() {
		if (stack.size() == 0) {
			return -1;	
		}
		return stack.peek();
	}
	  	    
	public int min() {
		if (stack.size() == 0) {
			return -1;	
		}
		return minStack.peek();
	}
	  	  
	public static void main(String[] args) {
			
		StackWithMinOpe stack = new StackWithMinOpe();
		stack.push(1);
		stack.push(2);
		stack.push(2);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println(stack.top());
		System.out.println(stack.min());

		stack.pop();
		System.out.println(stack.top());
		stack.pop();
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
	}
}

//New Knowledge:
//peek(): Retrieves the head (first element) of this list.
//peekFirst(): Retrieves the first element of this list, or returns null if this list is empty.
