/*
 * == Created Date == June 13, 2018
 * 
 * == Question - Heapify ==
 * Heapify an unsorted array to min heap.
 *   
 * == Updated == 
 * July 10, 2018: Review
 * July 31, 2018: Forgot a lot of details already
 */

package heapRelated;

public class Heapify {
	
	public int[] heapifyBottomUp(int[] arr) {
		if (arr == null || arr.length == 0) { // corner case
			return arr;
		}				
		int start = arr.length / 2 - 1; // start with the last parent node
		while (start >= 0) {
			sink(start, arr); // sift down the node to the proper place such that all nodes below are in heap order
			start--; // go to the next parent node
		}
		return arr;
	}
	
	private void sink(int k, int[] arr) {
		while (2 * k + 1 < arr.length) { // While the root has at least one child
			int succ = 2 * k + 1;
			
			if (succ < arr.length && less(arr, succ + 1, succ)) {
				succ++; // If there is a right child and that child is smaller
			}
			if (less(arr, succ, k)) {
				swap(arr, succ, k);
				k = succ;
			} else { // often forget to add this part!!
				break;
			}
		}
	}
	
	private boolean less(int[] arr, int a, int b) {
		if (arr[a] < arr[b]) {
			return true;
		} else {
			return false;
		}
	}
	
	private void swap(int[] arr, int a, int b) {
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
		
		Heapify testObj = new Heapify();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[] arr1 = {5, 4, 3, 2, 1};
		printArr(arr1);
				
		arr1 = testObj.heapifyBottomUp(arr1);
		printArr(arr1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int[] arr2 = {6, 5, 8, 4, 10, 1, 3, 2, 1};
		printArr(arr2);

		arr2 = testObj.heapifyBottomUp(arr2);
		printArr(arr2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}
