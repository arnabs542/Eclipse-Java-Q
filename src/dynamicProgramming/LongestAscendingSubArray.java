/*
 * Created Date: July 7, 2018
 * 
 * Question - Longest Ascending SubArray:
 *   Given an unsorted array, find the length of the longest subarray 
 *    in which the numbers are in ascending order.
 *    
 *   Assumptions:
 *     The given array is not null
 *     
 *   Examples
 *     {7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.
 *     {1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3.
 * 
 */

package dynamicProgramming;

public class LongestAscendingSubArray {
	
	public int longest(int[] array) {
		if (array.length <= 1) {
	      return array.length;
	    }
	    int result = 1;
	    int[] memory = new int[array.length];
	    memory[0] = 1;
	    for (int i = 1; i < array.length; i++) {
	      if (array[i] > array[i - 1]) {
	        memory[i] = memory[i - 1] + 1;
	        result = Math.max(result, memory[i]);
	      } else {
	        memory[i] = 1;
	      }
	    }
	    return result;
	  }

	// Time Complexity: O(n);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		LongestAscendingSubArray testObj = new LongestAscendingSubArray();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {7, 2, 3, 1, 5, 8, 9, 6};
		int result1 = testObj.longest(arr1);
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
