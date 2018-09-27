/*
 * Created Date: June 15, 2018
 * 
 * Question - K Smallest In Unsorted Array:
 *   Find the K smallest numbers in an unsorted integer array A. 
 *   The returned numbers should be in ascending order.  
 *   
 *   Example: 
 *     A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}
 * 
 * Updated:
 *   August 1, 2018: Review
 *
 */

package heapRelated;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KSmallestInUnsortedArray {
		
	public int[] kSmallest(int[] arr, int k) {	
		if (arr == null || arr.length == 0) { // corner case
			return arr;
		}
		if (k < 0 || k > arr.length) {
			return null;
		} if (k == 0) {
			return new int[0];
		}
		
		PriorityQueue<Integer> maxHeap = getMaxHeap(k);

		for (int i = 0; i < arr.length; i++) {
			if (i < k) { // offer k elements to build a max heap of k size
				maxHeap.offer(arr[i]);
			} else { // compare the rest of the element with the top of the heap
				if (arr[i] <= maxHeap.peek()) {
					maxHeap.poll();
					maxHeap.offer(arr[i]);
				}
			}
		}
		
		int[] result = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			result[i] = maxHeap.poll();
		}
		return result;
	}
	
	/* ============= Three ways to create a max heap =============== */	
	private PriorityQueue<Integer> getMaxHeap(int k) {
		
	    /*--- < Syntax 1: Use Collections >---*/	
		PriorityQueue<Integer> maxHeap1 = new PriorityQueue<>(k, Collections.reverseOrder());    
		
		/*--- < Syntax 2: Override Comparator >---*/	
		class ReverseComparator implements Comparator<Integer> {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1.equals(o2)) {
					return 0;
				} 
				return o1 > o2 ? -1: 1;
			}
		}	
		PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>(k, new ReverseComparator());
		
		/*--- < Syntax 3: Lambda	 >---*/		
	    PriorityQueue<Integer> maxHeap3 = new PriorityQueue<>(k, new Comparator<Integer>() {
	       @Override
	       public int compare(Integer o1, Integer o2) {
	         if (o1.equals(o2)) { // or o1.intValue() == o2.intValue(); cannot use o1 == o2
			     return 0;
		     }
		     return o1 > o2 ? -1 : 1; // return -1 means o1 has higher priority
		   }
		});	
	    
	    return maxHeap1;
	}
	
	// Time Complexity: O( klogk + (n - k)logk );
	// Space Complexity: O(k);
	
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
		
		KSmallestInUnsortedArrayWithOwnHeap testObj = new KSmallestInUnsortedArrayWithOwnHeap();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {3, 4, 1, 2, 5};
		arr1 = testObj.kSmallest(arr1, 3);		
		printArr(arr1); // expected: 1 2 3
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		int[] arr2 = {5, 3, 4, 2, 1, 1, 2, 1, 8, 4, 4, 9, 13, 5, 8}; 
		arr2 = testObj.kSmallest(arr2, 10);		
		printArr(arr2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}
