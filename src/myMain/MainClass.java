package myMain;

import java.util.ArrayDeque;
import java.util.Queue;

public class MainClass {
	
	
	
	public static void main(String[] args) {
		
		
		Queue<Integer> q = new ArrayDeque<>();
		
		q.add(1);
		q.add(2);
		q.add(2);
		
		System.out.print(q.size());
		System.out.print(q.peek());
	}	    	 
}