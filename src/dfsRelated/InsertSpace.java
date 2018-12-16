/*
 * == Created Date == 
 * August 19, 2018
 * 
 * == Question - Insert Space ==
 * Given a string, we can insert at most one empty space between each pair of adjacent letters. 
 * Print all permutations of strings after insertions of empty spaces. 
 *    
 * == Example ==
 * Input: str = "ABC" 
 * Output: ABC, AB_C, A_BC, A_B_C
 *     
 * == Notes ==
 * Lai Course - Final problem 4, Very basic DFS problem, think through why you doing wrong!!!
 * 
 * Review: Dec 15 2018
 * 
 * == Mirror Question ==
 * AllSubsetsI
 *   
 */

package dfsRelated;

import java.util.ArrayList;
import java.util.List;

public class InsertSpace {
	
	/* 1. How many levels and what does it store on each level?
	 *    - Levels: The number of the positions that can be added space = the number of characters - 1. 
	 * 
	 * 2. How many different states should we try to put on this level:
	 *    - Two. Each state consider either add or not add space to the current position.
	 * 
	 * 3. Base Case 
	 *    - When all the positions have been traversed
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
	
	/* ------------ < Same method, just to review > ------------ */
	
	public void InsertSpaceReview(String input) {
		System.out.println();
		dfsReview(input, 0, new ArrayList<>());
	}
	
	private void dfsReview(String input, int index, List<Integer> posList) {
		if (index >= input.length() - 1) {
			pringString(input, posList);
			return;
		}
		// add " "
		posList.add(index);
		dfsReview(input, index + 1, posList);	
		posList.remove(posList.size() - 1);
		
		// not add " "
		dfsReview(input, index + 1, posList);	
	}
	
	private void pringString(String input, List<Integer> posList) {
		if (posList.size() == 0) {
			System.out.println(input);
			return;
		}
		int pos = 0;
		for (int i = 0; i < input.length(); i++) {
			System.out.print(input.charAt(i));
			if (pos < posList.size() && i == posList.get(pos)) {
				System.out.print(" ");
				pos++;
			}
		}
		System.out.println();
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		InsertSpace tesjObj = new InsertSpace();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		tesjObj.insertSpace("ABC");
		tesjObj.InsertSpaceReview("ABC");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		tesjObj.insertSpace("ABCD");
		tesjObj.InsertSpaceReview("ABCD");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
