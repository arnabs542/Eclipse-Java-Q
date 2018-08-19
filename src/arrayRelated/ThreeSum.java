/*
 * Created Date: August 19, 2018
 * 
 * Question - 3 Sum:
 *   Determine if there exists three elements in a given array that sum to the given target number. 
 *   Return all the triple of values that sums to target.
 *   
 *   Example: 
 *   A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]
 * 
 */

package arrayRelated;

import java.util.*;

public class ThreeSum {

	public List<List<Integer>> allTriples(int[] array, int target) {
		if (array == null) {
			return null;
		}
		List<List<Integer>> res = new ArrayList<>();	
		
		Set<Integer> set = new HashSet<>();
		for (int num : array) {
			if (!set.contains(num)) {
				set.add(num);
			} 
		}
		
		for (int i = 0; i < array.length; i++) {
			if (i + 2 < array.length && array[i] == array[i + 1] && array[i] == array[i + 2]) {
				i++;
			} else {
				twoSum(array, i, target - array[i], res, set);
			}			
		}
		return res;
	}
	
	private void twoSum(int[] array, int preIndex, int target, List<List<Integer>> res, Set<Integer> set) {	
		
		for (int i = preIndex + 1; i < array.length; i++) {
			List<Integer> onePair = new ArrayList<>();			
			if (i + 2 < array.length && array[i] == array[i + 1] && array[i] == array[i + 2]) {
				i++;
			} else {
				if (set.contains(target - array[i])) {
					onePair.add(array[preIndex]);
					onePair.add(array[i]);
					onePair.add(target - array[i]);
					res.add(onePair);
				}
			}	
		}
	}
}
