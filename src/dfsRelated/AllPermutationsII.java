/*
 * Created Date: June 30, 2018
 * 
 * Question - All Permutations II (hard):
 *   Given a string with possible duplicate characters, return a list with all permutations of the characters.
 *   
 *   Example: 
 *   Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
 *   Set = "aba", all permutations are ["aab", "aba", "baa"]
 *   Set = "", all permutations are [""]
 *   Set = null, all permutations are []
 * 
 * Updated:
 *   July 1, 2018 : Review
 *   June 30, 2018: Review, Mid term exam problem 3
 * 
 */

package dfsRelated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllPermutationsII {
	
	public List<String> permutations(String set) {
		List<String> result = new ArrayList<>();
		if (set == null) { // corner case
			return result;
		}
		char[] input = set.toCharArray();
		helper(input, 0, result);
		return result;
	}
		  
    private void helper(char[] input, int index, List<String> result) {
      if (index == input.length) {
        result.add(new String(input)); // don't use: input.toString()
        return;
      }
      Set<Character> set = new HashSet<>();
      // all the possible characters could be placed at index are in (index, input.length - 1)
      for (int i = index; i < input.length; i++) {
    	      if (set.add(input[i])) {
    	          swap(input, i, index);
    	          helper(input, index + 1, result); // go for the next level
    	          swap(input, i, index); // swap back when backtracking to the previous level
    	      }
      }
    }
		  
    private void swap(char[] input, int a, int b) {
      char temp = input[a];
      input[a] = input[b];
      input[b] = temp;
    }
    
	// Time Complexity: O(n!);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		AllPermutationsII testObj = new AllPermutationsII();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		List<String> result00  = testObj.permutations(null);
		for (String s : result00) {
			System.out.println(s + " ");
		}
		
		List<String> result0  = testObj.permutations("");
		 
		for (String s : result0) {
			System.out.println(s + " ");
		} // expected: [“”]
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		List<String> result1  = testObj.permutations("abc");
		 
		for (String s : result1) {
			System.out.println(s + " ");
		} // expected: [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
				
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		List<String> result2  = testObj.permutations("aba");
		 
		for (String s : result2) {
			System.out.println(s + " ");
		} // expected: ["aab", "aba", "baa"]
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");	
		
		List<String> result3  = testObj.permutations("aabbccd");
		 
		for (String s : result3) {
			System.out.println(s + " ");
		}		
	}
}
