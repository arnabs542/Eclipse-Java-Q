/*
 * Created Date: May 18,2018
 * 
 * Question - First Occurrence:
 * 	Given a target integer T and an integer array A sorted in ascending order, 
 *  find the index of the first occurrence of T in A or return -1 if there is no such index.
 *  
 *  Assumption:
 *  Corner case: What if A is null or A of zero length? We should return -1 in this case.
 *  
 *  Examples:
 *  A = {1, 2, 3}, T = 2, return 1
 *  A = {1, 2, 3}, T = 4, return -1
 *  A = {1, 2, 2, 2, 3}, T = 2, return 1
 * 
 * Mirror Question: Last Occurrence
 * 
 * Updated: June 3, 2018 : review 
 * 
 */

package BinarySearchRelated;

public class FirstOccurBinarySearch {
	
	// Time Complexity: O(logn);
	// Space Complexity: O(1);
	public static int firstOccurBinarySearch(int[] arr, int target) {
		//corner case
		if(arr == null || arr.length == 0) {
			return -1;
		}
		int leftBound = 0;
		int rightBound = arr.length - 1;
		while (leftBound + 1 < rightBound) {
			int mid = leftBound + (rightBound - leftBound) / 2;
			if (arr[mid] >= target) {
				rightBound = mid;
			} else {
				leftBound = mid;
			}
		}
		if (arr[leftBound] == target) {
			return leftBound;
		} else if (arr[rightBound] == target) {
			return rightBound;
		} else {
			return -1;
		}
	}
	// Notice: the differences with Last Occurrence question!!
	
	public static void main(String[] args) {	
		
		int[] testArr1 = {};
		int rst = firstOccurBinarySearch(testArr1, 4);
		System.out.println(rst);	
		
		int[] testArr2 = {0};
		rst = firstOccurBinarySearch(testArr2, 4);
		System.out.println(rst);	
		
		int[] testArr3 = {1, 2, 4, 4, 4, 5, 7, 8, 10};		
		rst = firstOccurBinarySearch(testArr3, 4);
		System.out.println(rst);		
		
		int[] testArr4 = {7, 7, 7};		
		rst = firstOccurBinarySearch(testArr4, 7);
		System.out.println(rst);	
	}	
}
