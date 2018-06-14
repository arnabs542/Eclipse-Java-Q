/*
 * Created Date: June 14, 2018
 * 
 * Question - Heap Sort:
 *   A comparison based sorting algorithm with O(nlogn) time and O(1) space.
 *   
 *   You have to do it in place, extra space used is no more than O(1).
 *   Time complexity is O(nlogn)
 *   
 *   Mirror Question: 
 *     Heapify
 *
 */



package heapRelated;

public class HeapSort {
	
	public int[] heapsort(int[] array) {
		if (array == null || array.length == 0) { // corner case
			return array;
		}
		array = heapify(array); // construct a max heap
		return sort(array);
	}
	
	private int[] sort(int[] arr) {
		int size = arr.length;
		while (size >= 1) {		
			swap(arr, 0, size - 1);
			size--;
			sink(arr, size, 0);
		}		
		return arr;
	}
	
	private int[] heapify(int[] arr) {
		for (int k = arr.length / 2 - 1; k >= 0; k--) {
			sink(arr, arr.length, k);
		}
		return arr;
	}
	
	private void sink(int[] arr, int size, int k) {
		int leftChild = 2 * k + 1;
		while (leftChild < size) { // while a node has children
			int succ = leftChild;
			if (succ + 1 < size && less(arr, succ, succ + 1)) {
				succ++;
			}
			if (less(arr, k, succ)) {
				swap(arr, k, succ);
				k = succ;
				leftChild = 2 * k + 1;
			} else {
				break;
			}			
		}
	}
	
	private boolean less(int[] arr, int a, int b) {
		return arr[a] < arr[b];
	}
	
	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	private static void printArr(int[] arr) {
		if(arr == null) {
			System.out.println("null");
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("\n");
	}
	
	/* ----------------------< test stub >-------------------------*/
	
	public static void main(String[] args) {
		
		HeapSort heapSort = new HeapSort();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {2, 6};
		arr1 = heapSort.heapsort(arr1);
		
		printArr(arr1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int[] arr2 = {20, 18, 6, 14, 7, 17};
		arr2 = heapSort.heapsort(arr2);		
		printArr(arr2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
