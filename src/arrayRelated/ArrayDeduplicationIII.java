/*
 * Created Date: July 29, 2018
 * 
 * Question - Array Deduplication III (medium):
 *   Given a sorted integer array, remove duplicate elements. 
 *   For each group of elements with the same value do not keep any of them. 
 *   Do this in-place, using the left side of the original array and and maintain the relative order of the elements of the array. 
 *   Return the array after deduplication.
 *     
 *   Example: 
 *     {1, 2, 2, 3, 3, 3} → {1}
 * 
 */

package arrayRelated;

import java.util.Arrays;

public class ArrayDeduplicationIII {
		
	public int[] dedup(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int slow = 0;
		int fast = 0;
		while (fast < array.length) {
			int begin = fast;
			while (fast < array.length && array[fast] == array[begin]) {
 				fast++;
			}
			if (fast - begin == 1) {
				array[slow++] = array[begin];
			}
		}
		return Arrays.copyOf(array, slow);
	}
	/* ----------------------< test stub >-------------------------*/
	
	private static void print(int[] arr) {
		for (int item : arr) {
			System.out.print(item + " ");
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args) {
		
		ArrayDeduplicationIII testObj = new ArrayDeduplicationIII();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[] arr1 = {1, 1, 2, 3, 3, 4, 5, 5, 5};
		int[] res1 = testObj.dedup(arr1);
		print(res1); // expected: 2 4

		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		int[] arr2 = {1, 2, 3};
		int[] res2 = testObj.dedup(arr2);
		print(res2); // expected: 1 2 3

		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		int[] arr3 = {1, 1, 2, 2, 2, 3}; 
		int[] res3 = testObj.dedup(arr3);
		print(res3); // expected: 3
	}
}
