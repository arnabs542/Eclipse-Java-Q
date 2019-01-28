package myMain;

import java.util.ArrayDeque;
import java.util.Deque;

public class MainClass {

    

	public static void main(String[] args) {
        
		Deque<Integer> queue = new ArrayDeque<>();
		queue.offerLast(1);
		queue.offerLast(2);
		queue.offerLast(3);
		// first [ 1 2 3 ] last
		System.out.println(queue.peekLast());
		System.out.println(queue.pollLast());
//		System.out.println(queue.pollLast());
		
		
		
//		queue.offerFirst(1);
//		queue.offerFirst(2);
//		queue.offerFirst(3);
//		// first [ 3 2 1 ] last
//		System.out.println(queue.peek());
////		System.out.println(queue.pollLast());
////		System.out.println(queue.pollLast());
		
		
		
	}	    	 	
}
