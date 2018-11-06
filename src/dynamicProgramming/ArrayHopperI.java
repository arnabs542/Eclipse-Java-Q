/*
 * == Created Date == 
 * July 10, 2018
 * 
 * == Question - Array Hopper I (medium) ==
 * Given an array A of non-negative integers, you are initially positioned at index 0 of the array. 
 * A[i] means the maximum jump distance from that position 
 * (you can only jump towards the end of the array). 
 * Determine if you are able to reach the last index.
 * 
 * == Assumptions ==
 * The given array is not null and has length of at least 1.
 *   
 * == Example 
 *   
 *   {1, 3, 2, 0, 3}, we are able to reach the end of array
 *     (jump to index 1 then reach the end of the array).
 *     
 *   {2, 1, 1, 0, 2}, we are not able to reach the end of array
 * 
 */

package dynamicProgramming;

public class ArrayHopperI {
	public boolean canJump(int[] array) {
		int end = array.length - 1;
	    boolean[] M = new boolean[array.length];
	    M[end] = true;
	    for (int i = end - 1; i >= 0; i--) {
	      for (int j = 1; j <= array[i]; j++) {
	        if (i + j > end || M[i + j] == true) {
	          M[i] = true;
	          break;
	        }
	      }
	    }
	    return M[0];
	}

	// Time Complexity: O(n^2);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ArrayHopperI testObj = new ArrayHopperI();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		boolean[] M = new boolean[3]; 
		System.out.println(M[0]); // default "false"
		System.out.println(M[1]);
		System.out.println(M[2]); 
		
		int[] I = new int[3]; 
		System.out.println(I[0]);// default "0"
		System.out.println(I[2]);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {1, 3, 2, 0, 3};
		boolean result1 = testObj.canJump(arr1);
		System.out.println(result1); // expected: true
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int[] arr2 = {2, 1, 1, 0, 2};
		boolean result2 = testObj.canJump(arr2);
		System.out.println(result2); // expected: false
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
		int[] arr3 = {2};
		boolean result3 = testObj.canJump(arr3);
		System.out.println(result3); // expected: true		
	}
}
