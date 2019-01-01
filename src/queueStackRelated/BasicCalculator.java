/*
 * == Created Date ==
 * Dec 30, 2018
 * 
 * == Question - Basic Calculator ==
 *   
 * == Notes == 
 * LeetCode 224(H)
 *   
 */

package queueStackRelated;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {
	
	/* ----- < Method - Using stack > -----
	 * Time Complexity: O(N);
	 * Space Complexity: O(N);
	 * 
	 * One important thing is that the input is valid, which means the parentheses are always paired and in order.
	 * 
	 * Only 5 possible input we need to pay attention:
	 *  1. digit: it should be one digit from the current number, use the sign before it and add it to result
	 *  2. '+': set the sign to 1
	 *  3. '-': set the sign to -1
	 *  4. '(': push the previous result and the sign into the stack, 
	 *          set result to 0, just calculate the new result within the parenthesis.
	 *  5. ')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, 
	 *          second is the temporary result before this pair of parenthesis. We add them together.
	 *         
	 * */
    public int calculate(String s) {
        int sign = 1;
        int result = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        
        for (int i = 0; i < s.length(); i++) {
        		if (Character.isDigit(s.charAt(i))) {
        			int sum = s.charAt(i) - '0';
        			while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
        				sum = sum * 10 + s.charAt(i + 1) - '0';
        				i++;
        			}
        			result += sum * sign;
        		} else if (s.charAt(i) == '+') {
        			sign = 1;
        		} else if (s.charAt(i) == '-') {
        			sign = -1;
        		} else if (s.charAt(i) == '(') {
        			stack.push(result);
        			stack.push(sign);
        			result = 0;
        			sign = 1;
        		} else if (s.charAt(i) == ')') {
        			result = result * stack.pop() + stack.pop();
        		}
        }
        return result;
    	}	
    
	/* ----- < Method - Using stack > -----
	 * Time Complexity: O(N);
	 * Space Complexity: O(N);
	 * 
	 * */
}
