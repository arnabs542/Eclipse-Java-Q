/*
 * Created Date: July 21, 2018
 * 
 * Question - Array Hopper II (medium) :
 *   Given an array A of non-negative integers, you are initially positioned at index 0 of the array. 
 *   A[i] means the maximum jump distance from index i (you can only jump towards the end of the array). 
 *   Determine the minimum number of jumps you need to reach the end of array. 
 *   If you can not reach the end of the array, return -1.
 * 
 *  Assumptions:
 *    The given array is not null and has length of at least 1.
 *   
 *  Example: 
 *    {3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array)
 *    {2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in this case.
 * 
 */

package dynamicProgramming;

public class ArrayHopperII {
	
	public int minJump(int[] array) {
		int[] M = new int[array.length]; // M[i] record the minimal jumps from index i to the destination
		int end = array.length - 1;
		M[end] = 0; 
		for (int i = end - 1; i >= 0; i--) {
			boolean canJump = false;
			int curMin = Integer.MAX_VALUE;
			for (int j = 1; j <= array[i]; j++) {
				if (i + j <= end && M[i + j] != -1) {
					canJump = true;
					curMin = Math.min(curMin, M[i + j]);
				}
			}
			if (canJump) {
				M[i] = curMin + 1;
			} else {
				M[i] = -1;
			}
		}
		return M[0];
	}

	// Time Complexity: O(n^2);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ArrayHopperII testObj = new ArrayHopperII();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {3, 3, 1, 0, 4};
		int result1 = testObj.minJump(arr1);
		System.out.println(result1); // expected: 2
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int[] arr2 = {2, 1, 1, 0, 2};
		int result2 = testObj.minJump(arr2);
		System.out.println(result2); // expected: -1
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
		int[] arr3 = {2};
		int result3 = testObj.minJump(arr3);
		System.out.println(result3); // expected: 0		
	}
}
