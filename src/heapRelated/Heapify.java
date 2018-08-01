/*
 * Created Date: June 13, 2018
 * 
 * Question - Heapify:
 *   Heapify an unsorted array to min heap.
 *   
 * Updated:
 *   July 10, 2018: Review
 *   July 31, 2018: Forgot a lot of details already
 */

package heapRelated;

public class Heapify {
	
	private int[] arr;
	
	public void heapifyBottomUp(int[] arr) {
		if (arr == null || arr.length == 0) { // corner case
			return;
		}
		this.arr = arr;
				
		int start = arr.length / 2 - 1; // start with the last parent node
		while (start >= 0) {
			sink(start); // sift down the node to the proper place such that all nodes below are in heap order
			start--; // go to the next parent node
		}
	}
	
	private void sink(int k) {
		while (2 * k + 1 < arr.length) { // While the root has at least one child
			int succ = 2 * k + 1;
			
			if (succ < arr.length && less(succ + 1, succ)) {
				succ++; // If there is a right child and that child is smaller
			}
			if (less(succ, k)) {
				swap(succ, k);
				k = succ;
			} else { // often forget to add this part!!
				break;
			}
		}
	}
	
	private boolean less(int a, int b) {
		if (arr[a] < arr[b]) {
			return true;
		} else {
			return false;
		}
	}
	
	private void swap(int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(1);
	
	/* ----------------------< test stub >-------------------------*/
	
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
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[] arr1 = {5, 4, 3, 2, 1};
		printArr(arr1);
		
		Heapify heap1 = new Heapify();
		heap1.heapifyBottomUp(arr1);
		printArr(heap1.arr);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int[] arr2 = {6, 5, 8, 4, 10, 1, 3, 2, 1};
		printArr(arr2);
		
		Heapify heap2 = new Heapify();
		heap2.heapifyBottomUp(arr2);
		printArr(heap2.arr);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
