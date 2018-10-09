/*
 * == Created Date == 
 * June 15, 2018
 * 
 * == Question - K Smallest In Unsorted Array ==
 * Find the K smallest numbers in an unsorted integer array A. 
 * The returned numbers should be in ascending order.  
 *   
 * == Example ==
 * A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}
 * 
 * == Updated ==
 * August 1, 2018: Review
 * October 6, 2018: Add two more methods
 *
 */

package heapRelated;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class KSmallestInUnsortedArray {
		
	/* --------------------< Solution 1 - Use a Max heap >----------------------
	 * Time Complexity: O( klogk + (n - k)logk ) // O( k + (n - k)logk )
	 * Space Complexity: O(k);
	 * 
	 * Advantage: 
	 * 1. In reality, k << n, so Max Heap Solution is better than Min Heap
	 * 
	 * 2. Online Algorithm, Stream Processing (Use Max Heap) 
	 * 
	 *    VS 
	 * 
	 *    Offline, Batch Processin (Use Min Heap, Quick Select)
	 * 
	 * 
	 */
	public int[] kSmallestMeth1(int[] arr, int k) {	
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
	
	/* == Three ways to create a max heap == */	
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
	
	/* --------------------< Solution 2 - Use a Min heap >----------------------
	 * Time Complexity: O( n + klogn )
	 * Space Complexity: O(n);
	 * 
	 */	
	public int[] kSmallestMeth2(int[] arr, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		int[] res = new int[k];
		for (int ele : arr) {
			minHeap.offer(ele);
		}
		for (int i = 0; i < k; i++) {
			res[i] = minHeap.poll();
		}
		return res;
	}
	
	/* --------------------< Solution 3 - Quick Select >----------------------
	 * Time Complexity: average - O(n + n/2 + n/4 + ... 1) = O(n)
	 * 					worst - O( n + (n - 1) + (n - 2) + ... + 1) = O(n^2)
	 * Space Complexity: O(1);
	 *     
	 * xxxxxxxxPxxxxxxxxxxxxx
	 * 
	 * Step1: select a pivot p and partion the array 
	 *        such that all elements in left side of p are smaller than p and right elements are larger than p,
	 * 
	 */
	public int[] kSmallestMeth3(int[] arr, int k) {
		return quickSelect(arr, 0, arr.length - 1, k);
	}
	
	private int[] quickSelect(int[] arr, int lo, int hi, int k) {
		int left = partition(arr, lo, hi);
		
		if (left == k - 1) { // number of elements in the left side == k, return k elements
			int[] res = new int[k];
			for (int i = 0; i < k; i++) {
				res[i] = arr[i];
			}
			return res;
		} else if (left < k - 1) {
			return quickSelect(arr, left, hi, k);
		} else {
			return quickSelect(arr, lo, left - 1, k); 
			// Mistake here, not quickSelect(arr, lo, left, k); 
			// similar to binary search, must narrow down the search area every time, 
			// or it might go into an infinate loop
		}
	}
	
	private int partition(int[] arr, int lo, int hi) {
		Random rand = new Random();
		int pivotIndex = lo + rand.nextInt(hi - lo + 1);
		int pivotVal = arr[pivotIndex];
		
		swap(arr, hi, pivotIndex);
		
		int left = lo;
		int right = hi - 1;
		while (left <= right) {
			if (arr[left] <= pivotVal) {
				left++;
			} else {
				swap(arr, left, right);
				right--;
			}
		}
		swap(arr, left, hi);
		return left;
	}
	
	private void swap(int[] array, int a, int b) {		
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
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
		
		KSmallestInUnsortedArray testObj = new KSmallestInUnsortedArray();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1A = {3, 4, 1, 2, 5};
		arr1A = testObj.kSmallestMeth1(arr1A, 3);		
		printArr(arr1A); // expected: 1 2 3
		
		int[] arr1B = {3, 4, 1, 2, 5};
		arr1B = testObj.kSmallestMeth2(arr1B, 3);		
		printArr(arr1B); // expected: 1 2 3
		
		int[] arr1C = {3, 4, 1, 2, 5};
		arr1C = testObj.kSmallestMeth3(arr1C, 3);		
		printArr(arr1C); // expected: 1 2 3
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int[] arr2 = {5, 3, 4, 2, 1, 1, 2, 1, 8, 4, 4, 9, 13, 5, 8}; 
		arr2 = testObj.kSmallestMeth1(arr2, 10);		
		printArr(arr2);// expected: 1 1 1 2 2 3 4 4 4 5 
				
		int[] arr2C = {5, 3, 4, 2, 1, 1, 2, 1, 8, 4, 4, 9, 13, 5, 8}; 
		arr2C = testObj.kSmallestMeth3(arr2C, 10);	
		printArr(arr2C);// expected: 1 1 1 2 2 3 4 4 4 5 
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");	
		
		int[] arr3 = {5,3,4,2,1,1,2,1,8,4,4,9,13,5,8};
		
		arr3 = testObj.kSmallestMeth2(arr3, 9);	
		printArr(arr3);// expected: 1,1,1,2,2,3,4,4,4
		
		int[] arr3C = {5,3,4,2,1,1,2,1,8,4,4,9,13,5,8};
		arr3C = testObj.kSmallestMeth3(arr3C, 9);	
		printArr(arr3C);// expected: 4,3,4,2,1,1,2,1,4
	}
}
