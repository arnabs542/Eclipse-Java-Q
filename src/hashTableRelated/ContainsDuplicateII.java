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
	 * 
	 * */
    public boolean containsNearbyDuplicateII(int[] nums, int k) {
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (visited.containsKey(nums[i]) && Math.abs(visited.get(nums[i]) - i) <= k) {
                return true;
            } else {
                visited.put(nums[i], i);
            }
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
