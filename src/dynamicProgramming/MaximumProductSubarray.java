/*
 * == Created Date ==
 * Feb 24, 2019
 * 
 * == Question - Maximum Product Subarray ==
 * 
 *   
 * == Notes == 
 * LeetCode 152(M)
 * 
 */

package dynamicProgramming;

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {        
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int tempMax = maxSoFar * nums[i];
            int tempMin = minSoFar * nums[i];
            maxSoFar = Math.max(nums[i], Math.max(tempMax, tempMin));
            minSoFar = Math.min(nums[i], Math.min(tempMax, tempMin));
            result = Math.max(result, maxSoFar);
        }
        return result;
    }
}
