/*
 * Created Date: June 13, 2018
 * 
 * Question - K Smallest In Unsorted Array:
 *   Find the K smallest numbers in an unsorted integer array A. The returned numbers should be in ascending order.  
 *   
 *   Example: 
 *     A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}
 * 
 */

package heapRelated;

public class KSmallestInUnsortedArrayWithOwnHeap {
	
	public int[] kSmallest(int[] array, int k) {
		if (array == null || array.length == 0) { // corner case
			return array;
		}
		if (k < 0 || k > array.length) {
			return null;
		}
		
		// construct a max heap with size k	
		int[] heap = constructMaxHeap(array, k);
	
		// offer the rest of the array elements into the heap
		int curr = k;
		while (curr < array.length) {
			if (array[curr] <= heap[0]) {
				heap[0] = array[curr];
				sink(heap, heap.length, 0);
			}
			curr++;
		}
		
		// turn the max heap into a sorting array in ascending order
        return sort(heap);	
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
	
	private int[] constructMaxHeap (int[] arr, int size) {
		int[] heap = new int[size];
		for (int i = 0; i < size; i++) {
			heap[i] = arr[i];
		}		
		return heapify(heap);
	}
	
	private int[] heapify(int[] heap) {
		for (int k = heap.length / 2 - 1; k >= 0; k--) {
			heap = sink(heap, heap.length, k);
		}
		return heap;
	}
	
	private int[] sink(int[] heap, int size, int k) {
		while (2 * k + 1 < size) {
			int succ = 2 * k + 1;
			if (succ + 1 < size && bigger(heap, succ + 1, succ)) {
				succ++;
			}
			if (bigger(heap, succ, k)) {
				swap(heap, succ, k);
				k = succ;
			} else {
				break;
			}
		}
		return heap;
	}
	
	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	private boolean bigger(int[] arr, int a, int b) {
		return arr[a] > arr[b];
	}
	
	/* ----------------------< test stub >-------------------------*/
	
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
	
	public static void main(String[] args) {
		
		KSmallestInUnsortedArrayWithOwnHeap testObj = new KSmallestInUnsortedArrayWithOwnHeap();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {3, 4, 1, 2, 5};
		arr1 = testObj.kSmallest(arr1, 3);		
		printArr(arr1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		int[] arr2 = {5, 3, 4, 2, 1, 1, 2, 1, 8, 4, 4, 9, 13, 5, 8}; 
		arr2 = testObj.kSmallest(arr2, 10);		
		printArr(arr2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
    
}
