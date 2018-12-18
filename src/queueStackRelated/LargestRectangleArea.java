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

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        
        for (int i = 0; i <= heights.length; i++) {
            int cur = i == heights.length ? 0 : heights[i]; // we set the a wall of 0 to pop put all the elements in the stack at last
            
            while (!stack.isEmpty() && heights[stack.peek()] >= cur) {
            	
                int h = heights[stack.pop()];
                int left = stack.isEmpty() ? 0 : stack.peek() + 1; 
                
                // if the stack is empty, means that the left boundry of the current segment should be 0, think case "2 1 2"
                // So the following lines are wrong!!!!
                // int left = stack.pop();
                // int h = heights[left];
                
                int curArea = (i - left) * h;
                area = Math.max(area, curArea);
            }
            
            stack.push(i);
        }
        return area;
    }
}
