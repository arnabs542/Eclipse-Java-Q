/*
 * Created Date: May 18,2018
 * 
 * Question - Practice Classical Binary Search:
 * 	Given a target integer T and an integer array A sorted in ascending order, 
 * 	find the index i such that A[i] == T or return -1 if there is no such index.
 * 
 * Follow up questions: 
 * 		First Occurrence,
 * 		Last Occurrence,
 * 		Search In Sorted Matrix I
 * 
 * Updated: June 3, 2018 : review 
 * 
 */

package BinarySearchRelated;

public class ClassicalBinarySearch {
	
	public static int binarySearch(int[] array, int target) {		
		if (array == null || array.length == 0) {
			return -1;
		}
		int leftBound = 0;
		int rightBound = array.length - 1;	    	
		while (leftBound <= rightBound) {			
			int mid = leftBound + (rightBound - leftBound) / 2;
			if (array[mid] == target) {
				return array[mid];
			} else if (array[mid] > target) {
				rightBound = mid - 1;
			} else {
				leftBound = mid + 1;
			}				
		}	
		return -1;
	}
	// Time Complexity: O(logn);
	// Space Complexity: O(1);
	
	// ( , leftBound) : elements that smaller than mid
	// (rightBound, ) : larger than mid
	// Thus the condition to exit while loop is leftBound > rightBound, 
	// 	means every elements has been partitioned to the right place
	
	public static void main(String[] args) {	
		
		int[] testArr1 = {0};
		int rst = binarySearch(testArr1, 4);
		System.out.println(rst);	
		
		int[] testArr2 = {1, 2, 4, 4, 4, 5, 7, 8, 10};		
		rst = binarySearch(testArr2, 4);
		System.out.println(rst);		
	}
}

//System.out.println("array[i]:" + array[i]);
//System.out.println("array[j]:" + array[j]);
//System.out.println("array[mid]:" + array[mid]);
