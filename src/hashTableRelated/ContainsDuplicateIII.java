/*
 * == Created Date ==
 * Dec 30, 2018
 * 
 * == Question - Contains Duplicate III ==
 * Given an array of integers, find out whether there are two distinct indices i and j in the array 
 *   such that the absolute difference between nums[i] and nums[j] is at most t 
 *   and the absolute difference between i and j is at most k.  
 *   
 * == Notes == 
 * LeetCode 220(M)
 *   
 */

package hashTableRelated;

import java.util.TreeSet;

public class ContainsDuplicateIII {

	/* ----- < Method 1 - Brute Force > -----
	 * Time Complexity: O(N * K);
	 * Space Complexity: O(1);
	 * 
	 * */
	public boolean containsNearbyAlmostDuplicateI(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
        		for (int j = i + 1; j <= i + k && j < nums.length; j++) {
        			if (Math.abs(Long.valueOf(nums[i]) - Long.valueOf(nums[j])) <= t) {
        				return true;
        			}
        			// Use  ``` Long.valueOf(nums[i]) - Long.valueOf(nums[j]) ``` to avoid 
        			// cases like: 0 - Integer.MAX_VALUE
        		}
        }
        return false;
    }
	
	/* ----- < Method 2 - Use Binary Search Tree > -----
	 * Time Complexity: O(nlogk);
	 * Space Complexity: O(k);
	 * 
	 * */
	public boolean containsNearbyAlmostDuplicateII(int[] nums, int k, int t) {
		TreeSet<Integer> set = new TreeSet<>();
		for (int i = 0; i < nums.length; i++) {
			
			// the smallest number that larger than nums[i]
			Integer ceil = set.ceiling(nums[i]);
			if (ceil != null && Long.valueOf(ceil) - Long.valueOf(nums[i]) <= t) {
				return true;
			}
			
			// the largest number that smaller than nums[i]
			Integer floor = set.floor(nums[i]);
			if (floor != null && Long.valueOf(nums[i]) - Long.valueOf(floor) <= t) {
				return true;
			}
			
			set.add(nums[i]);
			
			// set always maintains k numbers (similar to sliding window)
			if (i >= k) {
				set.remove(nums[i - k]);
			}
		}
		return false;
	}
	
	/* ----- < Method 3 - Bucket > -----
	 * Time Complexity: O(?);
	 * Space Complexity: O(?);
	 * 
	 * */
}
