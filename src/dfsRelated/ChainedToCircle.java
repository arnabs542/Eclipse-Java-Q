/*
 * == Created Date ==
 *  August 26, 2018
 *  
 * == Question - Chained To Circle ==
 * Given an array of strings, find if all the strings can be chained to form a circle. 
 * Two string s1 and s2 can be chained, if the last letter of s1 is identical to the first letter of s2.   
 *    “abc” and “cd” can be chained, 
 *    “abc” and “dz” can not be chained.
 *     
 * == Example == 
 * Input: arr[] = {"aaa", "bbb", "baa", "aab"}, Output: True
 * 
 * == Mirror Question ==
 * All PermutationsI
 *     
 * == Notes == 
 * Lai Course - Final problem 4
 * 
 */

package dfsRelated;

public class ChainedToCircle {
	
	/*
	 * 
	 * {"aaa", "bbb", "baa", "aab"}
	 * 
	 *                                0     1   2   3
	 * index = 1                    {aaa | bbb baa aab}     
	 * 
	 *                                /
	 *                                
	 * index = 2           {aaa aab | bbb baa}
	 *                 
	 *                     /                   \
	 *                     
	 * index = 3 {aaa aab bbb | baa}  {aaa aab baa | bbb}
	 * 
	 * 
	 * index = 4 {aaa aab bbb baa}
	 * 
	 * */
	
	public boolean chainCircle(String[] words) {
		if (words == null || words.length == 0) {
			return false;
		}
		return dfs(words, 1); // the cycle can start anywhere
	}
	
	private boolean dfs(String[] words, int index) {
		if (index == words.length) {
			return canChain(words[0], words[index - 1]);
		}
		for (int i = index; i < words.length; i++) {
			if (canChain(words[i], words[index - 1])) {
		        swap(words, i, index);
		        dfs(words, index + 1);
		        swap(words, i, index); // swap back when backtracking to the previous level
			}
		}
		return false;
	}
	
	private void swap(String[] words, int a, int b) {
		String temp = words[a];
		words[a] = words[b];
		words[b] = temp;
	}
	
	private boolean canChain(String a, String b) {
		if (a.charAt(0) == b.charAt(b.length() - 1)) {
			return true;
		} 
		return false;
	}
}
