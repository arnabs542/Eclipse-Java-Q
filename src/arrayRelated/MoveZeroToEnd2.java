/*
 * Created Date: May 31,2018
 * 
 * Question - Move 0s To The End II (easy+):
 * 	Given an array of integers, move all the 0s to the right end of the array.
 * 	The relative order of the elements in the original array need to be maintained.
 * 
 * 	Examples:
 * 		{1} --> {1} 
 * 		{1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0}
 * 
 * Updated: June 7, 2018 : review 
 * 
 */

package arrayRelated;

public class MoveZeroToEnd2 {
	
	public static void moveZero(int[] arr) {
		
		if (arr == null) {
			return;
		}
		
		int end = 0;
		int curr = 0;
		
		while (curr < arr.length) {
			if (arr[curr] != 0) {
				arr[end] = arr[curr];	
				end++;
			} 
			curr++;		
		}	
		
		for (int i = end; i < arr.length; i++) {
			arr[i] = 0;
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
		print(arr2);
		
		/* Test Case 3*/
		int[] arr3 = {1, 0, 2, 0, 0, 3, 0, 4, 5};
		moveZero(arr3);
		print(arr3);
	}
}