/*
 * Created Date: June 1,2018
 * 
 * Question : Closest In Sorted Array
 * 
 * Given a target integer T and an integer array A sorted in ascending order, 
 * find the index i in A such that A[i] is closest to T.
 * 
 * Assumption: There can be duplicate elements in the array, and we can return any of the indices with same value.
 * 
 * Examples
 * A = {1, 2, 3}, T = 2, return 
 * A = {1, 4, 6}, T = 3, return 1
 * A = {1, 4, 6}, T = 5, return 1 or 2
 * A = {1, 3, 3, 4}, T = 2, return 0 or 1 or 2
 * 
 * Corner Cases:
 * What if A is null or A is of zero length? We should return -1 in this case.
 * 
 */

package BinarySearchRelated;

public class ClosestInSortedArray {
	
	public static int closest(int[] array, int target) {
		
		if (array == null || array.length == 0) return -1; // corner case
		
		int i = 0;
		int j = array.length - 1;
		
		while (i < j - 1) {
			int mid = i + (j - i) / 2;
			if (array[mid] == target) return mid;
			else if(array[mid] > target) j = mid;
			else i = mid;
		}

		if (Math.abs(array[i] - target) <= Math.abs(array[j] - target)) return i;
		else return j;
	}	
	// Time Complexity: O(logn)
	// Space Complexity: O(1)
	
	public static void main(String[] args) {
		int[] arr = {3, 4, 5, 6, 6, 12, 16};
		
		for (int item : arr) {
			System.out.print(item + " ");
		}
		
		int result = ClosestInSortedArray.closest(arr, 10);
		
		System.out.println("\n" + result);

	}
}

// Mistake:  while(i + 1 <= j) {...}, will enter a endless loop: 
// i = 4, j = 5, mid = 4
// arr[mid] < target
// i = mid = 4
// can't get out of while loop

