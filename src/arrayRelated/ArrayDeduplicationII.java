/*
 * == Created Date ==
 * July 29, 2018
 * 
 * == Question - Array Deduplication II & V ==
 * Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. 
 * Return the array after deduplication.
 * 
 */

package arrayRelated;

import java.util.Arrays;


public class ArrayDeduplicationII {
	
	/*
	 * == Question - Array Deduplication II (medium) == 
	 * Given a *sorted* integer array, remove duplicate elements. 
	 * For each group of elements with the same value keep at most two of them.
	 *   
	 * == Example ==
	 * {1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
	 * 
	 */
	
	public int[] dedup(int[] array) {
		if (array == null || array.length <= 2) {
			return array;
		}
		int fast = 2;
		int slow = 2;
		while (fast < array.length) {
			if (array[fast] != array[slow - 2]) {
				array[slow++] = array[fast++];
			} else {
				fast++;
			}
		}
		return Arrays.copyOf(array, slow);
	}
	
	/*
	 * == Question - Array Deduplication V (medium) == 
	 * Given an integer array(not guaranteed to be sorted), remove adjacent repeated elements.
	 * For each group of elements with the same value keep at most two of them.  
	 *    
	 * == Example ==
	 * {2, 1, 2, 2, 2, 3} --> {2, 1, 2, 2, 3}  
	 * 
	 */
	
	public int[] dedupV(int[] array) {
	    if (array.length <= 2) { // corner case
	      return array;
	    }
	    int slow = 2; // [0, slow) are elements to keep
	    int fast = 2;
	    while (fast < array.length) {
	      if (array[fast] != array[slow - 2] || array[fast] != array[slow - 1]) {
	        array[slow] = array[fast];
	        slow++;
	      } 
	      fast++;
	    }
	    return Arrays.copyOf(array, slow);
	  }
	  
		/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
			
		ArrayDeduplicationII testObj = new ArrayDeduplicationII();
			
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[] result = testObj.dedupV(new int[] {1,2,2,3,2,2,2,3,3,3,3,4});
				
		for (int ele : result) {
			System.out.print(ele + " ");
		}
		System.out.print("\n");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
			
	}
	  
	  
}
