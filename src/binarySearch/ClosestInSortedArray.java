/*
 * Created Date: June 1,2018
 * 
 * Question - Closest In Sorted Array:
 * 	Given a target integer T and an integer array A sorted in ascending order, 
 * 	find the index i in A such that A[i] is closest to T.
 * 
 *  Assumption: 
 * 		There can be duplicate elements in the array, and we can return any of the indices with same value.
 * 
 * 	Examples:
 * 			A = {1, 2, 3}, T = 2, return 1
 * 			A = {1, 4, 6}, T = 3, return 1
 * 			A = {1, 4, 6}, T = 5, return 1 or 2
 * 			A = {1, 3, 3, 4}, T = 2, return 0 or 1 or 2
 * 
 * 	Corner Cases:
 * 		What if A is null or A is of zero length? We should return -1 in this case.
 * 
 * Follow up questions: 
 * 		K Closest In Sorted Array
 * 
 * Updated: 
 *   June 30, 2018: Review
 * 
 */

package binarySearch;

public class ClosestInSortedArray {
	
	public int closest(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int leftBound = 0;
		int rightBound = array.length - 1;
		while (leftBound + 1 < rightBound) {
			int mid = leftBound + (rightBound - leftBound) / 2;
			if (array[mid] == target) {
				return mid;
			} else if (array[mid] < target) {
				leftBound = mid;
			} else {
				rightBound = mid;
			}
		}
		return Math.abs(array[leftBound] - target) <= Math.abs(array[rightBound] - target) ? leftBound: rightBound;
	}	
	
	// Time Complexity: O(logn)
	// Space Complexity: O(1)
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ClosestInSortedArray testObj = new ClosestInSortedArray();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {3, 4, 5, 6, 6, 12, 16};		
		int result1 = testObj.closest(arr1, 10);		
		System.out.println(result1); // expected 5
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		int[] arr2 = {1, 3, 3, 4};	
		int result2 = testObj.closest(arr2, 2);		
		System.out.println(result2); // expected 0 or 1 or 2
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}

// Mistake:  while(i + 1 <= j) {...}, will enter a endless loop: 
// i = 4, j = 5, mid = 4
// arr[mid] < target
// i = mid = 4
// can't get out of while loop
