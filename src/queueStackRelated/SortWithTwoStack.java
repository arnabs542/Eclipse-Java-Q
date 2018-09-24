/*
 * == Created Date ==
 * September 12, 2018
 * 
 * == Question - Sort With Stacks == 
 *   
 */

package queueStackRelated;

import java.util.Arrays;
import java.util.LinkedList;

public class SortWithTwoStack {
	
	/*
	 * == Question - Sort With 3 Stacks ==
	 * Given one stack with integers, sort it with two additional stacks (total 3 stacks). 
	 * After sorting the original stack should contain the sorted integers 
	 *   and from top to bottom the integers are sorted in ascending order.
	 *   
	 */
		
	public void sortWith3Stack(LinkedList<Integer> s1) {
		LinkedList<Integer> s2 = new LinkedList<Integer>();
		LinkedList<Integer> s3 = new LinkedList<Integer>();
		  
		// Write your solution here.
		// add all the elements in orginal stack to s3
	    while (!s1.isEmpty()) {
	    		s3.add(s1.pollLast());
	    }
	    
	    int size = s3.size();
	    for (int i = 0; i < size; i++) {
		    int tempMin = Integer.MAX_VALUE;
	        while (!s3.isEmpty()) { // each iteration, poll elements from s3, find the minimum number, use s2 to store
	            int cur = s3.pollLast();
		        s2.add(cur);
	            tempMin = Math.min(tempMin, cur);
	        }
	        s1.add(tempMin); // use s1 to store the minimum number
		    
		   // except the minimum number, push elements back to s3
		   boolean flag = true; // use flag to deal with multiple tempMin elements
		   while (!s2.isEmpty()) {
			   int cur = s2.pollLast();
			   if (flag && cur == tempMin) {
				   flag = !flag;
			   } else {
			        s3.add(cur);
			   }
		   }
	    } 
	    
		for (int i : s1) {
			System.out.print(i + " ");
		}	
	}
	
	/*
	 * == Question - Sort With Two Stack == 
	 * Given an array that is initially stored in one stack, sort it with one additional stacks (total 2 stacks).
	 * After sorting the original stack should contain the sorted integers 
	 *   and from top to bottom the integers are sorted in ascending order.
	 *   
	 */
	
	public void sort(LinkedList<Integer> s1) {
	    LinkedList<Integer> s2 = new LinkedList<Integer>(); 
	    int size = s1.size();
	    for (int i = 0; i < size; i++) {         	
	      int tempMin = s1.get(s1.size() - 1);        
	      for (int j = i; j < size; j++) {
	        int cur = s1.pollLast();
	        s2.add(cur);
	        tempMin = Math.min(tempMin, cur);
	      }     
	      s1.add(tempMin);   
	      
		  boolean flag = true;
		  while (!s2.isEmpty()) {
		      int cur = s2.pollLast();
	          if (flag && cur == tempMin) {
	        	  flag = !flag;
	        } else {
		        s1.add(cur);
		      }
		    }
	    }	  
	    
		for (int i : s1) {
			System.out.print(i + " ");
		}		
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		SortWithTwoStack tesjObj = new SortWithTwoStack();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(5,4,3,3,3,2,1));
		// java.util.Arrays class has a static asList() method 
		//		which will convert the set of values provided to a List and return. 
		
		tesjObj.sort(list);
		tesjObj.sortWith3Stack(list);
	
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
