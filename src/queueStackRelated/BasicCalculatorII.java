/*
 * == Created Date ==
 * Dec 30, 2018
 * 
 * == Question - Basic Calculator II ==
 *   
 * == Notes == 
 * LeetCode 227(M)
 *   
 */
package queueStackRelated;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculatorII {

	/* ----- < Method - Using stack > -----
	 * Time Complexity: O(N);
	 * Space Complexity: O(N);
	 * 
	 * */
	public int calculate(String s) {
	    if (s == null || s.length() == 0) {
	    		return 0;
	    }
	    Deque<Integer> stack = new ArrayDeque<>();
	    int num = 0;
	    char sign = '+';
	    
	    /* 123456
	     * 33+2*2
	     *      i
	     * 
	     * num: 2
	     * sign: *
	     * stack || 33 4
	     * */
	    for (int i = 0; i < s.length(); i++) {
	        if (Character.isDigit(s.charAt(i))) {
	            num = num * 10 + s.charAt(i) - '0';
	        }
	        // every time scan an oprand or the char is the last digit, process the last block (sign + num)
	        if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1) {
	            if (sign == '-') {
	                stack.push(-num);
	            }
	            if (sign == '+') {
	                stack.push(num);
	            }
	            if (sign == '*') {
	                stack.push(stack.pop() * num);
	            }
	            if (sign == '/') {
	                stack.push(stack.pop() / num);
	            }
	            sign = s.charAt(i);
	            num = 0;
	        }
	    }

	    int result = 0;
	    for (int digit : stack){
	    		result += digit;
	    }
	    return result;
	}

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		BasicCalculatorII testObj = new BasicCalculatorII();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.calculate("33+2*2"));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		System.out.println(testObj.calculate("-3 + 22 / 2"));
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
