/*
 * == Created Date ==
 * December 27, 2018
 * 
 * == Question - 3Sum Closest ==

 * == Notes == 
 * LeetCode 16, medium
 * 
 */
package hashTableRelated;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1; 
            int right = nums.length - 1;
            while (left < right) {  
                int tripleSum = nums[i] + nums[left] + nums[right];
                if (tripleSum == target) {
                    return target;
                } else if (tripleSum < target) {
                    if ((target - tripleSum) < diff) {
                        diff = target - tripleSum;
                        result = tripleSum;
                    }
                    left++;
                } else {
                    if ((tripleSum - target) < diff) {
                        diff = tripleSum - target;
                        result = tripleSum;
                    }
                    right--;
                }
            }
        }
        return result;
    }
}
