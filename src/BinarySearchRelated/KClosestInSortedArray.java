/*
 * Created Date: June 4, 2018
 * 
 * Question - K Closest In Sorted Array:
 * 	Given a target integer T, a non-negative integer K and an integer array A sorted in ascending order, 
 * 	find the K closest numbers to T in A.
 * 
 * 	Assumptions: 
 * 		A is not null and K is guranteed to be >= 0 and K is guranteed to be <= A.length
 * 	
 *  Return:
 *  		A size K integer array containing the K closest numbers(not indices) in A, 
 *  		sorted in ascending order by the difference between the number and T. 
 *  
 *  Examples:
 *  		A = {1, 2, 3}, T = 2, K = 3, return {2, 1, 3} or {2, 3, 1}
 *  		A = {1, 4, 6, 8}, T = 3, K = 3, return {4, 1, 6}
 *  
 *  Updated:
 *    June 30, 2018: Review
 * 
 */

package BinarySearchRelated;

public class KClosestInSortedArray {
		
	public static int[] kClosest(int[] array, int target, int k) {	 
		int leftBound = 0;
		int rightBound = array.length - 1;
		while (leftBound + 1 < rightBound) {
			int mid = leftBound + (rightBound - leftBound) / 2;
			if (array[mid] <= target) {
				leftBound = mid;
			} else {
		       rightBound = mid;
		    }
		}
		 
		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			if (leftBound < 0) {
				result[i] = array[rightBound];
				rightBound++;
			} else if (rightBound >= array.length) {
				result[i] = array[leftBound];
				leftBound--;
			} else if (Math.abs(array[leftBound] - target) <= Math.abs(array[rightBound] - target)) {
				result[i] = array[leftBound];
				leftBound--;
			} else {
				result[i] = array[rightBound];
				rightBound++;
			}
		}
		return result;
	}
	// Time Complexity: O(logn + k)
	// Space Complexity: O(k)
	
	public static void main(String[] args) {				
		int[] testArr1 = {1, 2, 3};
		int[] rst1 = kClosest(testArr1, 2, 3);
		for (int item : rst1) {
			System.out.print(item + " ");
		}	
		System.out.print("\n");
			
		int[] testArr2 = {1, 4, 6, 8};		
		int[] rst2 = kClosest(testArr2, 3, 3);
		for (int item : rst2) {
			System.out.print(item + " ");
		}	
		System.out.print("\n");	
			
		int[] testArr3 = {1, 5};		
		int[] rst3 = kClosest(testArr3, 10, 1);
		for (int item : rst3) {
			System.out.print(item + " ");
		}	
		System.out.print("\n");	
	}
}
