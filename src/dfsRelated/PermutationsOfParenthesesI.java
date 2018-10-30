/*
 * == Created Date: June 21, 2018 == 
 * 
 * == Question - All Valid Permutations Of Parentheses I == 
 * Given N pairs of parentheses “()”, return a list with all the valid permutations.
 *      
 * == Example == 
 * N = 1, all valid permutations are ["()"]
 * N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"]
 * N = 0, all valid permutations are [""]
 * 
 */

package dfsRelated;

import java.util.LinkedList;
import java.util.List;

public class PermutationsOfParenthesesI {
	
	/* 1. How many levels and what does it store on each level?
	 *    - Levels: 2 * pairs of parentheses ( = how many position we need to fill) 
	 *    - Each level decide to fill this position with "(" or ")"
	 * 
	 * 2. How many different states should we try to put on this level:
	 *    - Two. Each state consider "(" or ")"
	 * 
	 * */
	
	public List<String> validParentheses(int n) {
		List<String> result = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		helper(n, 0, 0, sb, result);
		return result;
	}
	
	private void helper(int n, int leftNum, int rightNum, StringBuilder sb, List<String> result) {
		if (leftNum == n && rightNum == n) {
			result.add(sb.toString());
			return;
		}
		if (leftNum < n) {
			sb.append('(');
			helper(n, leftNum + 1, rightNum, sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}
		if (leftNum > rightNum) {
			sb.append(')');
			helper(n, leftNum, rightNum + 1, sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	
	// Time Complexity: O(2^(2n));
	// Space Complexity: O(2n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		PermutationsOfParenthesesI testObj = new PermutationsOfParenthesesI();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");		
		
		List<String> result  = testObj.validParentheses(3);
		 
		for (String s : result) {
			System.out.println(s + " ");
		}
				
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		List<String> result2  = testObj.validParentheses(2);
		 
		for (String s : result2) {
			System.out.println(s + " ");
		}
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}

}
