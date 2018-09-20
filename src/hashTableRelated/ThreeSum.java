/*
 * == Created Date ==
 * September 19, 2018
 * 
 * == Question - 3 Sum All Unique Pair ==
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
 * Find all unique triplets in the array which gives the sum of zero.  
 *   
 * == Example == 
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 *   [
 * 		[-1, 0, 1],
 * 		[-1, -1, 2]
 * 	 ]   
 * 
 */

package hashTableRelated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
	public List<List<Integer>> allTriples(int[] array, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(array);
		for (int i = 0; i < array.length - 2; i++) {
			if (i > 0 && array[i] == array[i - 1]) {
				continue;
			}
			int left = i + 1;
			int right = array.length - 1;
			while (left < right) {
				int tmp = array[left] + array[right];
				if (tmp + array[i] == target) {
					result.add(Arrays.asList(array[i], array[left],array[right]));
					left++;
					while (left < right && array[left] == array[left - 1]) {
						left++;
					}
				} else if (tmp + array[i] < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		return result;
	}
	
    /* Mistake: 
    ```
    if (array[i] == array[i - 1]) {
        continue;
    }
    ```
    This is wrong. Think a sorted array -1, -1, 0, 2
    if i equals that last duplicate -1, then the right pair [-1, -1, 2] will be missed
    
    */
}
