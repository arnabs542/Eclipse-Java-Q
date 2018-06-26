/*
 * Created Date: June 26, 2018
 * 
 * Question - Missing Number I:
 *   Given an integer array of size N - 1, containing all the numbers from 1 to N except one, find the missing number.
 *      
 *   Example: 
 *   A = {2, 1, 4}, the missing number is 3
 *   A = {1, 2, 3}, the missing number is 4
 *   A = {}, the missing number is 1
 *   
 * Lesson:
 *   When finishing the code, run several test cases including corner cases in your head!!!
 *   
 * Updated:
 * 
 */

package hashTableRelated;

import java.util.HashSet;
import java.util.Set;

public class MissingNumberI {
	
	/* --------------< Method 1 - Use Hash Table >--------------*/
	public int missingMeth1(int[] arr) {
		Set<Integer> set = new HashSet<>();
	    for (int item : arr) {
	      set.add(item);
	    }   
	    for (int i = 1; i <= arr.length + 1; i++) { // i from 1, not 0, don't just write code, think through!!!
	      if (!set.contains(i)) {
	        return i;
	      }
	    }   
	    return -1;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		System.out.print(1);
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
