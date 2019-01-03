/*
 * == Created Date ==
 * Dec 18, 2018
 * 
 * == Question - Trapping Rain Water ==
 *   
 * == Notes == 
 * LeetCode 42 (H)
 * Lai Fall Class Strengthen 5
 * 
 * == Similar Question == 
 * LeetCode 755* (M) - Pour Water
 * LeetCode 11 (M) - Container With Most Water
 */

package arrayRelated;

public class TrappingRainWater {
	
	/* ----------------------< Brute Force Solution >-------------------------
	 * Time Complexity: O(n^2);
	 * Space Complexity: O(1);
	 * 
	 */
	public int trapI(int[] height) {
	    int waterToTrap = 0;
	    for (int i = 0; i < height.length; i++) {
	        int max_left = 0, max_right = 0;
	        for (int j = i; j >= 0; j--) { //Search the left part for max bar size
	            max_left = Math.max(max_left, height[j]);
	        }
	        for (int j = i; j < height.length; j++) { //Search the right part for max bar size
	            max_right = Math.max(max_right, height[j]);
	        }
	        waterToTrap += Math.min(max_left, max_right) - height[i];
	    }
	    return waterToTrap;
	}
	
	/* ----------------------< Solution using Dynamic Programing >-------------------------
	 * Time Complexity: O(n);
	 * Space Complexity: O(n);
	 * 
	 */
    public int trapMeth1(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        
        int leftMaxSeenSoFar = 0;
        for (int i = 0; i < height.length; i++) {
        		leftMaxSeenSoFar = Math.max(leftMaxSeenSoFar, height[i]);
            leftMax[i] = leftMaxSeenSoFar;
        }
        
        int rigntMaxSeenSoFar = 0;
        for (int i = height.length - 1; i >= 0; i--) {
        		rigntMaxSeenSoFar = Math.max(rigntMaxSeenSoFar, height[i]);
            rightMax[i] = rigntMaxSeenSoFar;
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
        // ```left <= right``` also works, because left and right will stop at the highest bar, trap 0 water
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
