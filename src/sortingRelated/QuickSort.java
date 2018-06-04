/*
 * Created Date: May 21, 2018
 * Application: Practice Quick Sort
 * 
 */

package sortingRelated;

import java.util.Random;

public class QuickSort {
	
	public void quickSort(int[] array) {
		// corner case
		if (array == null) {
			return;
		}
		quickSort(array, 0, array.length - 1);
	}

	private void quickSort(int[] array, int left, int right) {	
		//base case
		if (left >= right) {
			return;
		}
		// define a pivot and use it to partition the array
		int pivotPos = partition(array, left, right);		
		// do the recursive call on the two partitions
		quickSort(array, left, pivotPos - 1);
		quickSort(array, pivotPos + 1, right);		
	}
	
	private int partition(int[] array, int left, int right) {	
		// randomly select a pivot index from the part to be partitioned
		int pivotIndex = selectPivot(left, right);
		int pivotVal = array[pivotIndex];
		// swap the pivot element to the rightmost element first
		swap(array, pivotIndex, right);		
		int leftBound = left;
		int rightBound = right - 1;		
		while (leftBound <= rightBound) {
			if (array[leftBound] <= pivotVal) {
				leftBound++;
			} else {
				swap(array, leftBound, rightBound);
				rightBound--;
			}
		}		
		// swap back the pivot element
		swap(array, leftBound, right);			
		return leftBound;
	}
	
	private int selectPivot(int left, int right) {
		Random rand = new Random();
		// pick random element in the range of [left, right]
		return left + rand.nextInt(right - left + 1); 	
	}
		
	private void swap(int[] array, int a, int b) {		
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public static void main(String[] args) {
		int[] array = {20, 18, 5, 21, 8, 4, 4};
		QuickSort myQuickSort = new QuickSort();
		myQuickSort.quickSort(array);
		for(int i : array) {
			System.out.print(i + " ");
		}
	}
}

//new function:
//rand.nextInt(x) -> randomly select a number in [0, x)

/*1st Review
package myMain;

import java.util.Random;

public class MainClass {
	
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
	
	public static void main(String[] args) {
		int[] arr = {3, 5, 1, 9};
		MainClass myMain = new MainClass();
		myMain.quickSort(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}	    	 
}
 
*/
