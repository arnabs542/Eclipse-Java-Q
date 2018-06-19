/*
 * Created Date: May 16, 2018
 * 
 * Application: Practice Merge Sort using recursion method
 * 
 * Mirror Question:
 *   Merge Sort Linked List
 *   
 * Updated: 
 *   June 18, 2018: Review
 * 
 */

package sortingRelated;

public class MergeSort {
	
	public int[] mergeSort(int[] array) {
		if (array == null || array.length == 0) { // corner case 
			return array;
		}		
		int[] aux = new int[array.length];
		mergeSort(array, aux, 0, array.length - 1);
		return array;		
	}
		
	private void mergeSort(int[] arr, int[] aux, int left, int right) {						
		if (left >= right) { // base case
			return;
		}
		System.out.print("merge sort: ");
		print(arr, left, right);
		System.out.println("\n");
		
		int mid = left + (right - left) / 2;
		
		mergeSort(arr, aux, left, mid);
		mergeSort(arr, aux, mid + 1, right);
		merge(arr, aux, left, mid, right);
	}
	
	private void merge(int[] arr, int[] aux, int left, int mid, int right) {
		
		System.out.print("merge: "); print(arr, left, mid); System.out.print(" and "); print(arr, mid + 1, right);
		
		for (int k = left; k <= right; k++) {
			aux[k] = arr[k];
		}
		
		int leftIndex = left;
		int rightIndex = mid + 1;
		
		while (left <= right) {
			if (leftIndex > mid) {
				break; // if there is no element in the left half, 
				       // we don't need to copy the remaining elements in the right half, because they are already in their position
			} else if (rightIndex > right) {
				arr[left++] = aux[leftIndex++];
			} else if (aux[leftIndex] <= aux[rightIndex]) {
				arr[left++] = aux[leftIndex++];
			} else {
				arr[left++] = aux[rightIndex++];
			}
		}
		
		System.out.print("\nAfter mergeing: ");
		print(arr);
	}
	
	private void print(int[] arr) {
		for (int index: arr) {
			System.out.print(index + " ");
		}
		System.out.println("\n");		
	}
	
	private void print(int[] arr, int left, int right) {
		System.out.print("[ ");
		for (int k = left; k <= right; k++) {
			System.out.print(arr[k] + " ");
		}
		System.out.print("]");		
	}
	
	
	public static void main(String[] args) {
		MergeSort test = new MergeSort();
		
		int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};		
		test.mergeSort(array);
	}
}

