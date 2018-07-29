/*
 * Created Date: July 29, 2018
 * 
 * Question - Array Deduplication II (medium)
 *   Given a sorted integer array, remove duplicate elements. 
 *   For each group of elements with the same value keep at most two of them. 
 *   Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. 
 *   Return the array after deduplication.
 *   
 *   Example: 
 *     {1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
 * 
 */

package arrayRelated;

import java.util.Arrays;

public class ArrayDeduplicationII {
	public int[] dedup(int[] array) {
		if (array == null || array.length <= 2) {
			return array;
		}
		int fast = 2;
		int slow = 2;
		while (fast < array.length) {
			if (array[fast] != array[slow - 2]) {
				array[slow++] = array[fast++];
			} else {
				fast++;
			}
		}
		return Arrays.copyOf(array, slow);
	}

}
