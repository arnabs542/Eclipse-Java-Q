/*
 * Created Date: July 23, 2018
 * 
 * Question - N Queens:
 *   Get all valid ways of putting N Queens on an N * N chess board 
 *     so that no two Queens threaten each other.
 *     
 *  Assumptions:
 *    N > 0
 *   
 *  Return:
 *    A list of ways of putting the N Queens
 *    Each way is represented by a list of the Queen's y index for x indices of 0 to (N - 1)
 *    
 *  Example:
 *   N = 4, there are two ways of putting 4 queens:
 *    [1, 3, 0, 2] --> the Queen on the first row is at y index 1, 
 *                     the Queen on the second row is at y index 3, 
 *                     the Queen on the third row is at y index 0 
 *                     and the Queen on the fourth row is at y index 2.
 *                     
 *    [2, 0, 3, 1] --> the Queen on the first row is at y index 2, 
 *                     the Queen on the second row is at y index 0, 
 *                     the Queen on the third row is at y index 3 
 *                     and the Queen on the fourth row is at y index 1.
 *                     
 * Updated:
 *   July 30, 2018: Review                
 * 
 */

package dfsRelated;

import java.util.ArrayList;
import java.util.List;

public class Nqueens {
	
	public void nQueens(int n) {
		List<Integer> cur = new ArrayList<>();
		helper(n, cur);
	}
	
	private void helper(int n, List<Integer> cur) {
		if (cur.size() == n) { // base case, when we have positioned n-th queen to the n-th row
			for (int item : cur) {
				System.out.print(item + " ");
			}
			System.out.print("\n");
			return;
		}
		// for the current row, check if any column can be used to place a queen 
		for (int col = 0; col < n; col++) {
			if (validate(cur, col)) {
				cur.add(col);
				helper(n, cur); // check the next row
				cur.remove(cur.size() - 1); // remember this step for dfs backtracking
			}
		}
	}
	
	private boolean validate(List<Integer> cur, int col) {
		int curRow = cur.size();
		// check if position a queen at (curRow, col) has any conflict with previous queen   
		for (int row = 0; row < curRow; row++) {
			if (cur.get(row) == col || Math.abs(cur.get(row) - col) == curRow - row) {
				return false;
			} 
		}
		return true;
	}
	
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		Nqueens testObj = new Nqueens();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		testObj.nQueens(4); // expected 1 3 0 2 , 2 0 3 1
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		testObj.nQueens(8); 
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}
