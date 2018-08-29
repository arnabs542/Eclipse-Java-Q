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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
	
	// Given an array of integers, return indices of the two numbers such that they add up to a specific target.
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                return res;
            } else {           	
            		map.put(nums[i], i);
            }
        }
        return res;
    }
}
