/*
 * Created Date: May 29,2018
 * 
 * Question - Move 0s To The End I (easy):
 * 	Given an array of integers, move all the 0s to the right end of the array.
 * 	The relative order of the elements in the original array does not need to be maintained.
 * 
 * 	Examples:
 * 		{1} --> {1} 
 * 		{1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0} or {1, 1, 3, 0, 0} or {3, 1, 1, 0, 0}
 * 
 * Follow up questions: 
 * 	Move 0s To The End I
 * 
 * Updated: June 7, 2018 : review 
 * 
 */

package arrayRelated;

public class MoveZeroToEnd1 {
	
	// (, leftBound) larger than 0
	// [leftBound, rightBound] unknown
	// (rightBound, ) 0
	
	public static void moveZero(int[] arr) {
	    if (arr == null) {
	    		return;
	    }	    
	    int left = 0;
	    int right = arr.length - 1;	    
	    while (left <= right) {
	    		if(arr[left] == 0) {	    			
	    			arr[left] = arr[right];
	    			arr[right] = 0;
	    			right--;
	    		} else {
	    			left++;
	    		}
	    }	
	}
	
	/* ----------------------< test stub >-------------------------*/
	private static void print(int[] arr) {
		if(arr == null) {
			return;
		}
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args) {
		
		/* Test Case 1*/
		int[] arr1 = {1};
		moveZero(arr1);
		print(arr1);
		
		/* Test Case 2*/
		int[] arr2 = {1, 0, 3, 0, 1};
		moveZero(arr2);
		print(arr2); // expected: {1, 3, 1, 0, 0} or {1, 1, 3, 0, 0} or {3, 1, 1, 0, 0}
		
		/* Test Case 3*/
		int[] arr3 = {1, 0, 2, 0, 0, 3, 0, 4, 5};
		moveZero(arr3);
		print(arr3);
	}
}
