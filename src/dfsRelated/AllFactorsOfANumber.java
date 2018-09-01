/*
 * Created Date: August 31, 2018
 * 
 * Question - All Factors Of A Number:
 *   Get all possible combinations of factors that can multiply to a target number.
 *   The given number is guaranteed to be >= 2.
 *     
 *   Example: 
 *     12 -->  [ [2, 2, 3], [2, 6], [3, 4], [12] ]
 *     5 --> [ [5] ]
 *     
 *  Mirror Question:
 *    Combinations Of Coins
 * 
 */
package dfsRelated;

import java.util.ArrayList;
import java.util.List;

public class AllFactorsOfANumber {
	public List<List<Integer>> factors(int n) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> facts = findFactors(n);
		List<Integer> curr = new ArrayList<>();
		dfs(n, 0, facts, curr, result);
		return result;
	}
	
	/*
	 * 12: [2, 3, 4, 6, 12]
	 * 
	 *   2 index = 0                   []                    [2]                   [2 2]         [2 2 2]   
	 *                          /         |     \           /   \                 /    \            /
	 *   3 index = 1          []         [3]    [3 3]     [2] [2 3]           [2 2] [2 2 3]    [2 2 2]  
	 *                     /      \      / \                                   / 
	 *  	 4 index = 2     []      [4]   [3] [3 4]                            [2 2] 
	 *                  / \      /     /                                   /    \
	 *   6 index = 3   []  [6]  [4] [3]                                [2 2]  [2 2 6] 
	 *                / \       /   /                                   /       /  
	 *  12 index = 4 [] [12]  [4]  [3]                              [2 2]   [2 2 6]
	 *                
	 * */
	
	private void dfs(int targetRemain, int index, List<Integer> facts, List<Integer> curr, List<List<Integer>> result) {
		if (index == facts.size()) {			
			if (targetRemain == 1) {
				result.add(new ArrayList<Integer>(curr));
			}
			return;
		}
		
		int curFac = facts.get(index);
		
		dfs(targetRemain, index + 1, facts, curr, result);
		
		int num = 1;
		while (targetRemain % Math.pow(curFac, num) == 0) {
			for (int i = 1; i <= num; i++) {
				curr.add(curFac);
			}
			
			dfs(targetRemain / (int) Math.pow(curFac, num), index + 1, facts, curr, result);	
			
			for (int i = 1; i <= num; i++) {
				curr.remove(curr.size() - 1);	
			}
			
			num++;
		}
	}
	
	private List<Integer> findFactors(int n) {
	    List<Integer> res = new ArrayList<>();
	    for (int i = 2; i <= n; i++) {
	    		if (n % i == 0) {
	    			res.add(i);
	    		}
	    }
	    return res;
	}

	// Time Complexity: O( (log_2(n)) ^ k ), k = the number of factors
	//					eg: 12, 4 ^ 3
	// Space Complexity: O(k);
	
	/* ----------------------< test stub >-------------------------*/
	
	private static void print(List<List<Integer>> result) {
		for (List<Integer> res : result) {
			for (int item : res) {
				System.out.print(item + " ");
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args) {
		
		AllFactorsOfANumber testObj = new AllFactorsOfANumber();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
			
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		List<List<Integer>> result1 = testObj.factors(12);
		print(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		List<List<Integer>> result2 = testObj.factors(100);
		print(result2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		List<List<Integer>> result3 = testObj.factors(2);
		print(result3);		
	}
}
