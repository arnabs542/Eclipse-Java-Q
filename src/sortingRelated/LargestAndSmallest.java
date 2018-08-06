/*
 * Created Date: August 6, 2018
 * 
 * Question - Largest And Smallest:
 *   Use the least number of comparisons to get the largest and smallest number in the given integer array. 
 *   Return the largest number and the smallest number.
 *     
 *   Example: 
 *     {2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1. return [5, 1].
 *     
 * 
 */

package sortingRelated;

public class LargestAndSmallest {
	
	// Tournament Sort， 1.5n comparisons
	public int[] largestAndSmallest(int[] array) {
		if (array == null || array.length < 1) { // corner case
			return array;
		}
		if (array.length == 1) {
			return new int[] {1, 1};
		}
		
		for (int i = 0; i < array.length; i += 2) {
			if (i + 1 != array.length && array[i] > array[i + 1]) {
				swap(array, i, i + 1);
			} 
		}
		
		int min = array[0];
		for (int i = 0; i < array.length; i += 2) {
			min = Math.min(min, array[i]);
		}
		
		int max = array[1];
		for (int i = 1; i < array.length; i += 2) {
			max = Math.max(max, array[i]);
		}
		max = Math.max(max, array[array.length - 1]);
			
		System.out.print("max = " + max + ", min = " + min);
		return new int[] {max, min};
	}
	
	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		LargestAndSmallest testObj = new LargestAndSmallest();
		
		/* Test Case 0 */
		System.out.println("\n---< Test Case 0 >---");
		int[] arr0 = {1};
 		testObj.largestAndSmallest(arr0);
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		int[] arr1 = {2,1,4,5};
 		testObj.largestAndSmallest(arr1);	
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		int[] arr2 = {1,2,3,4,5};
 		testObj.largestAndSmallest(arr2);
		
		/* Test Case 3 */
		System.out.println("\n---< Test Case 3 >---");
		
		int[] arr3 = {5,2,3,4,1};
 		testObj.largestAndSmallest(arr3);
		
	}

}
