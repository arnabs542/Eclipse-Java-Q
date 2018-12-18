/*
 * == Created Date ==
 * Dec 18, 2018
 * 
 * == Question - Largest Rectangle Area ==
 *   
 * == Notes == 
 * LeetCode 84 - Hard
 * Lai Fall Class Strengthen 5
 * 
 */

package queueStackRelated;

public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int result = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        for (int i = 0; i <= heights.length; i++) {
            // we need a way to pop put all the elements in the stack at last
            int cur = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= cur) {
                int h = heights[stack.pop()];
                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                int curArea = (i - left) * h;
                area = Math.max(area, curArea);
            }
            stack.push(i);
        }
        return area;
    }
}
