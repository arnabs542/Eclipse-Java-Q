/*
 * == Created Date ==
 * September 21, 2018
 * 
 * == Question - Subarray Sum Equals K (medium) ==
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
 * == Similar Question == 
 * Maximum Size Subarray Sum Equals K, see the end below
 * 
 */

package hashTableRelated;

import java.util.*;

public class SubarraySumEqualsK {
	
	/* == Solution 1: Brute Force == 
	 * Time Complexity: O(n ^ 3), Time Limit Exceeded 
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
	 * Time Complexity: O(n ^ 2)
	 * Space Complexity: O(1)
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
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 * 
	 *  Input: nums = [1, 1, 1], k = 2
	 *        index    0  1  2 
	 *  prefixSum  [0  1  2  3] 
	 *               
	 *  Because:
	 *        prefixSum(j) = nums[0] + nums[1] + ... + nums[i - 1] + nums[i] + ...+ nums[j]
	 *    prefixSum(i - 1) = nums[0] + nums[1] + ... + nums[i - 1]
	 *    
	 *  Thus: 
	 * 	  Sum of the sub-array (i, j) is prefixSum(j) - prefixSum(i - 1)
	 *  
	 *  Now, the problem turns to find how many pair (i, j)
	 *  where i < j, and prefixSum[j] - prefixSum[i] == k
	 *                          
	 *  In implementation, use a Map to replace the prefixSum array 
	 *    key - prefixSum value; 
	 *    value - the number of occurrence of the prefix sum         
	 *    
	 *         <0, 1>    <1, 1>       <2, 1>                        <3, 1>
	 *                          exist sum = prefixSum[2] - k   exist sum = prefixSum[3] - k 
	 *   count = +0       + 0           + 1                           +1
	 *         = 2
	 */  
	
	public int subarraySumMeth3(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return 0;
		}		
		Map<Integer, Integer> prefixSumMap = new HashMap<>();
		prefixSumMap.put(0, 1);
		int prefixSum = 0;
		int count = 0;
		for (int ele : nums) {
			prefixSum += ele;
			count += prefixSumMap.getOrDefault(prefixSum - k, 0);
			prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
		}
		return count;
	}
	
	/* ----- Similar Question - Maximum Size Subarray Sum Equals K --- */
	/* Given an array nums and a target value k, find the maximum length of a subarray that sums to k. 
	 * If there isn't one, return 0 instead.
	 * 
	 * == Example ==
	 * Given nums = [1, -1, 5, -2, 3], k = 3
	 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
	 * 
	 * 
	 * */
	
	  public int maxSubArrayLen(int[] nums, int k) {
		  Map<Integer, Integer> map = new HashMap<>();
		  int maxSize = 0;
		  int prefixSum = 0;
		  map.put(0, -1);
		  for (int i = 0; i < nums.length; i++) {
			  prefixSum += nums[i];
			  if (map.containsKey(prefixSum - k)) {
				  maxSize = Math.max(maxSize, i - map.get(prefixSum - k));
				  // mistakes here: the size of the subarray is not i - map.get(prefixSum - k) + 1
				  // because sum of subarray (i, j) is prefixSum[j] - prefixSum[i - 1]
			  } 
			  if (!map.containsKey(prefixSum)) {
			      map.put(prefixSum, i);
			  }
		  }
		  return maxSize;
	  }
}
