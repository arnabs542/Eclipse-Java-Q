/*
 * == Created Date ==
 * June 21, 2018
 * 
 * == Question - Combinations Of Coins ==
 * Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), 
 *   get all the possible ways to pay a target number of cents.
 *   
 * Arguments:
 *   coins - an array of positive integers representing the different denominations of coins, 
 *           there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5, 2, 1}
 *   target - a non-negative integer representing the target number of cents, eg. 99
 *     
 * Return: 
 *   a list of ways of combinations of coins to sum up to be target.
 *   each way of combinations is represented by list of integer, 
 *   the number at each index means the number of coins used for the denomination at corresponding index.
 *   
 * == Example== 
 * Input: coins = {2, 1}, target = 4
 * Output:
 * 
 * [
 *   [0, 4],   (4 cents can be conducted by 0 2 cents + 4 1 cents)
 *   [1, 2],   (4 cents can be conducted by 1 2 cents + 2 1 cents)
 *   [2, 0]    (4 cents can be conducted by 2 2 cents + 0 1 cents)
 * ]
 *   
 * === Similar Questions ==
 * All Factors Of A Number
 *   
 * == Updated ==
 * August 31, Review
 * 
 */

package dfsRelated;

import java.util.ArrayList;
import java.util.List;

public class CombinationsOfCoins {
	
	public List<List<Integer>> combinations(int target, int[] coins)  {
		
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> curr = new ArrayList<>();
		combinations(target, 0, coins, curr, result);
	    return result;
	}
	
	private void combinations(int moneyLeft, int index, int[]coins, List<Integer> curr, List<List<Integer>> result) {
		if (index == coins.length) {
			if (moneyLeft == 0) {
				result.add(new ArrayList<Integer>(curr)); 
			}
			return;
		}

		for (int num = 0; num * coins[index] <= moneyLeft; num++) {
			curr.add(num);
			combinations(moneyLeft - num * coins[index], index + 1, coins, curr, result); 
			curr.remove(curr.size() - 1);
		}
	}
	
	// different syntax
	private void helper(int moneyLeft, int index, int[]coins, List<Integer> curr, List<List<Integer>> result) {
		if (index == coins.length - 1) {
			if (moneyLeft % coins[index] == 0) {
				curr.add(moneyLeft / coins[index]);
				result.add(new ArrayList<Integer>(curr)); // don't use result.add(curr), result will store a list of null reference!!!
				curr.remove(curr.size() - 1); // !!!
			}
			return;
		}
		int max = moneyLeft / coins[index];
		System.out.println("moneyLeft = " + moneyLeft);
		for (int num = 0; num <= max; num++) {
			curr.add(num);
			helper(moneyLeft - num * coins[index], index + 1, coins, curr, result); 
			curr.remove(curr.size() - 1);
		}
	}

	// Time Complexity: O(branches ^ number of coins); ?
	// Space Complexity: O(number of coins);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		CombinationsOfCoins testObj = new CombinationsOfCoins();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
	
		int[] coins = {5, 2, 1};
		List<List<Integer>> result  = testObj.combinations(11, coins);
		 
		for (List<Integer> list : result) {
			System.out.print("\n");
			for (int i : list) {
				System.out.print(i + " ");
			}
		} //expected: [[0,0,11],[0,1,9],[0,2,7],[0,3,5],[0,4,3],[0,5,1],[1,0,6],[1,1,4],[1,2,2],[1,3,0],[2,0,1]]
				
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}
