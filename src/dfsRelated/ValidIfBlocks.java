/*
 * Created Date: August 19, 2018
 * 
 * Question - Valid If Blocks:
 *   Given an integer n, print/output all possible if blocks for it. 
 *   
 *   Say n=2 output should be:
 *   if {
 *   }
 *   if {
 *   }
 *   <newline>
 *   if {
 *     if {// here should exist two spaces before each inner block
 *     }
 *   }
 * 
 */

package dfsRelated;

public class ValidIfBlocks {
	
	public void validIfBlocks(int n) {
		StringBuilder sb = new StringBuilder();
		helper(n, 0, 0, sb);
	}
	
	private void helper(int n, int left, int right, StringBuilder sb) {
		if (left == n && right == n) {
			System.out.println(sb.toString());
			//TODO: printing logic
		}
		if (left < n) {
			sb.append("{");
			helper(n, left + 1, right, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
		if (right < left) {
			sb.append("}");
			helper(n, left, right + 1, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	
	// Time Complexity: O(2^(2n));
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ValidIfBlocks testObj = new ValidIfBlocks();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		testObj.validIfBlocks(2);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		testObj.validIfBlocks(3);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
 
}
