/*
 * == Created Date ==
 * September 12, 2018
 * 
 * == Question - Sort With Two Stack == 
 * 
 */

package queueStackRelated;

import java.util.Arrays;
import java.util.LinkedList;

public class SortWithTwoStack {
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
	      
	      while (!s2.isEmpty()) {
	        int cur = s2.pollLast();
	        if (cur != tempMin) {
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
		
		LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(5,4,3,2,1));
		// java.util.Arrays class has a static asList() method 
		//		which will convert the set of values provided to a List and return. 
		
		tesjObj.sort(list);
	
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
