/*
 * == Created Date ==
 * September 21, 2018
 * 
 * == Question - Subarray Sum Equals K ==
 * Given an array of integers and an integer k, 
 * you need to find the total number of continuous subarrays whose sum equals to k.  
 *   
 * == Example == 
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * 
 * == Notes == 
 * Frequent question
 * 
 */

package hashTableRelated;

public class SubarraySumEqualsK {
	
	/* == Solution 1: Brute Force == 
	 * O(n ^ 3), Time Limit Exceeded 
	 * 
	 * Input: nums = [1,1,1], k = 2
	 *                i
	 *                    j
	 */                  
	 
	public int subarraySumMeth1(int[] nums, int k) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				int sum = 0;
				for (int index = i; index <= j; index++) {
					sum += nums[index];
					if (sum == index) {
						count++;
					}
				}
			}
		}
		return count;
	}
	
	/* == Solution 2: Prefix Sum == 
	 * O(n ^ 2)
	 * 
	 * Input: nums = [1,1,1], k = 2
	 *                i
	 *                  j
	 *  prefixSum:  0 1 2                  
	 */
	
	public int subarraySumMeth2(int[] nums, int k) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int prefixSum = 0;
			for (int j = i; j < nums.length; j++) {
				prefixSum += nums[j];
				if (prefixSum == k) {
					count++;
				}
			}
		}
		return count;
	}
	
	/* == Solution 3: Prefix Sum Array == 
	 * 
	 */

}
