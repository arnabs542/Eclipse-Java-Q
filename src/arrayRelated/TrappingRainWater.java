/*
 * == Created Date ==
 * Dec 18, 2018
 * 
 * == Question - Trapping Rain Water ==
 *   
 * == Notes == 
 * LeetCode 42 - Hard
 * Lai Fall Class Strengthen 5
 * 
 */

package arrayRelated;

public class TrappingRainWater {
	
	/* ----------------------< Solution using Dynamic Programing >-------------------------
	 * Time Complexity: O(n);
	 * Space Complexity: O(n);
	 * 
	 */
    public int trapMeth1(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        
        int lm = 0;
        for (int i = 0; i < height.length; i++) {
            lm = Math.max(lm, height[i]);
            leftMax[i] = lm;
        }
        
        int rm = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            rm = Math.max(rm, height[i]);
            rightMax[i] = rm;
        }
        
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }

    /* ----------------------< Solution using Dynamic Programing, Space Optimized >-------------------------
	 * Time Complexity: O(n);
	 * Space Complexity: O(1);
     * 
     * */
    public int trap(int[] height) {
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax <= rightMax) {
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
