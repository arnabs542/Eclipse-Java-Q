/*
 * == Created Date ==
 * Dec 30, 2018
 * 
 * == Question - Contains Duplicate II ==
 * Given an array of integers and an integer k, 
 *   find out whether there are two distinct indices i and j in the array 
 *   such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 *  
 * == Notes == 
 * LeetCode 219(E)
 *   
 */

package hashTableRelated;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class ContainsDuplicateII {

	/* ----- < Method 1 - Brute Force > -----
	 * Time Complexity: O(N * K);
	 * Space Complexity: O(1);
	 * 
	 * */
	public boolean containsNearbyDuplicateI(int[] nums, int k) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length && j <= i + k; j++) {
				if (nums[i] == nums[j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	/* ----- < Method 2 - Use Map > -----
	 * Time Complexity: O(N);
	 * Space Complexity: O(N);
	 *            i
	 *  0 1 2 3 4 5
	 * [1,2,3,1,2,3]
	 * 
	 * map 
	 * 1 3
	 * 2 1
	 * 3 2
	 * */
    public boolean containsNearbyDuplicateII(int[] nums, int k) {
    		// key: number, value: the last index we have seen for this number
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
        		Integer lastIndex = indexMap.get(nums[i]);
        		if (lastIndex != null && Math.abs(lastIndex - i) <= k) {
        			return true;
        		}
            indexMap.put(nums[i], i);
        }
        return false;
    }
    
	/* ----- < Method 3 - Sliding Window + Set > -----
	 * Time Complexity: O(N);
	 * Space Complexity: O(k);
	 * 
	 * */
    public boolean containsNearbyDuplicateIII(int[] nums, int k) {
    		Set<Integer> distinct = new HashSet<>();

    		for (int i = 0; i < nums.length; i++) {
    			if (!distinct.add(nums[i])) {
    				return true;
    			}
    			if (i >= k) {
    				distinct.remove(nums[i - k]);
    			}
    		}
    		return false;
    }
}
