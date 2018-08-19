/*
 * Created Date: August 19, 2018
 * 
 * Question - 2 Sum:
 *   Determine if there exist two elements in a given array, the sum of which is the given target number.
 *   
 *   Example: 
 *     A = {1, 2, 3, 4}, target = 5, return true (1 + 4 = 5)
 *     A = {2, 4, 2, 1}, target = 4, return true (2 + 2 = 4)
 *     A = {2, 4, 1}, target = 4, return false
 * 
 */

package arrayRelated;

import java.util.HashSet;
import java.util.Set;

public class TwoSum {
	
	// assumption:
	// - return what? yes/no? value? index?
	// - sorted(can use two pointers) / unsorted?
	// - find one result? or all pair?
	// - optimized time/space complexity
	// - data size
	
	public boolean existSum(int[] array, int target) {
		if (array == null) {
			return false;
		}
		Set<Integer> set = new HashSet<>();
		for (int num : array) {
			if (set.contains(target - num)) {
				return true;
			} else {
				set.add(num);
			}
		}
		return false;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(n);
}
