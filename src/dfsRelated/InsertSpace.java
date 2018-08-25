/*
 * Created Date: August 19, 2018
 * 
 * Question - Insert Space:
 *   Given a string, we can insert at most one empty space between each pair of adjacent letters. 
 *   Print all permutations of strings after insertions of empty spaces. 
 *    
 *   Example: 
 *     Input: str = "ABC" 
 *     Output: ABC, AB_C, A_BC, A_B_C
 *     
 *   Notes:
 *     Final, Problem 1
 *     Very basic DFS problem, think through why you doing wrong!!!
 *     
 *   Mirror Question:
 *     AllSubsetsI
 *   
 */

package dfsRelated;

public class InsertSpace {
	
	/* 1. How many levels and what does it store on each level?
	 *    - Levels: The number of the positions that can be added space = the number of characters - 1. 
	 * 
	 * 2. How many different states should we try to put on this level:
	 *    - Two. Each state consider either add or not add space to the current position.
	 * 
	 *               ABC
	 *               
	 *          /            \
	 *          
	 *        A_BC           ABC
	 *        
	 *       /   \          /    \
	 *       
	 *   A_B_C    A_BC    AB_C   ABC
	 *   
	 * */
	
	public void insertSpace(String input) {
		if (input == null) {
		    return;
		}
		StringBuilder sb = new StringBuilder();
		char[] in = input.toCharArray();
		insertSpace(in, sb, 0);
	}

	private void insertSpace(char[] in, StringBuilder sb, int index) {
		if (index == in.length - 1) { // base case
			sb.append(in[in.length - 1]); // append the last character of input
		    System.out.println(sb.toString());
		    sb.deleteCharAt(sb.length() - 1);
		    return;
		}
	    sb.append(in[index]);
	    
	    // add " "
	    sb.append(' ');
	    insertSpace(in, sb, index + 1);
	    sb.deleteCharAt(sb.length() - 1);
	    
	    // do not add " "
	    insertSpace(in, sb, index + 1);
	    sb.deleteCharAt(sb.length() - 1);
	}
		
	// Time Complexity: O(2^n);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		InsertSpace tesjObj = new InsertSpace();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		tesjObj.insertSpace("ABC");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		tesjObj.insertSpace("ABCD");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
