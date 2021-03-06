/*
 * Created Date: May 21, 2018
 * 
 * Application: Quick Sort
 * 
 * Updated:
 * 	June, 8, 2018: Review
 *  August, 28, 2018: Review
 * 
 */

package sortingRelated;

import java.util.Random;

public class QuickSort {
	
	public void quickSort(int[] array) {		
		if (array == null) { // corner case
			return;
		}
		quickSort(array, 0, array.length - 1);
	}

	private void quickSort(int[] array, int left, int right) {		
		if (left >= right) { //base case
			return;
		}
		
		// define a pivot and use it to partition the array
		int pivotPos = partition(array, left, right);		
		
		// do the recursive call on the two partitions
		// noted that the current pivotPos is in the correct place, no need to move it!
		quickSort(array, left, pivotPos - 1);
		quickSort(array, pivotPos + 1, right);		
	}
	
	private int partition(int[] array, int left, int right) {	
		// randomly select a pivot index from the part to be partitioned
		int pivotIndex = left + new Random().nextInt(right - left + 1);
		int pivotVal = array[pivotIndex];
		
		// swap the pivot element to the rightmost element first
		swap(array, pivotIndex, right);		
		
		int rightBound = right - 1;		
		while (left <= rightBound) {
			if (array[left] <= pivotVal) {
				left++;
			} else {
				swap(array, left, rightBound);
				rightBound--;
			}
		}		
		// swap back the pivot element
		swap(array, left, right);		// why swap with leftBound, not 	rightBound ?
		return left;
	}
		
	private void swap(int[] array, int a, int b) {		
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	
	// Time Complexity: O(nlogn);
	// Space Complexity: O(1);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		QuickSort myQuickSort = new QuickSort();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[] array1 = {20, 18, 5, 21, 8, 4, 4};
		
		myQuickSort.quickSort(array1);
		for (int i : array1) {
			System.out.print(i + " ");
		}
		System.out.print("\n");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		int[] array2 = {1, 4, 6, 5, 3, 2};
		
		myQuickSort.quickSort(array2);
		for (int i : array2) {
			System.out.print(i + " ");
		}
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

//new function:
//rand.nextInt(x) -> randomly select a number in [0, x)

class MistakesRecord {
	
	public void quickSort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return; // corner case
		}
		quickSort(arr, 0, arr.length - 1);		
	}
	
	private void quickSort(int[] arr, int left, int right) {
		if (left >= right) {
			return; // base case
		}
		// mistake: if (left == right) {...} will cause error
		// example, after a recursion, partition is 0, 
		// then do on right part: quickSort(arr, 0, -1)
		
		int pivot = partition(arr, left, right);
		quickSort(arr, left, pivot - 1);
		quickSort(arr, pivot + 1, right);
		// mistake: put pivot in the sorting part, 
		// pivot is already at the right position!!! don't confusing with merge sort
	}

	private int partition(int[] arr, int left, int right) {
		Random rand = new Random();
		int pivot = left + rand.nextInt(right - left + 1);
		System.out.println(pivot);
		int pivotVal = arr[pivot];
		swap(arr, pivot, right);
		
		int leftBound = left;
		int rightBound = right - 1;
		// mistake: rightBound = right
		
		while (leftBound <= rightBound) {
			if (arr[leftBound] >= pivotVal) {
				swap(arr, leftBound, rightBound); 
				rightBound--;
			} else {
				leftBound++;
			}			
		}
		swap(arr, leftBound, right);
		return leftBound;
	}
	
	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}	    	 
}

