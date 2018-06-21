/*
 * Created Date: June 20, 2018
 * 
 * Question - All Subsets I
 *   Given a set of characters represented by a String, return a list containing all subsets of the characters.
 *   
 *  Assumptions:
 *    There are no duplicate characters in the original set.
 *  
 *  Examples:
 *    Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
 *    Set = "", all the subsets are [""]
 *    Set = null, all the subsets are []  
 *   
 *   Follow up:
 *   
 *   
 * Updated: 
 *   Need to Review on June 21, 2018
 * 
 */


package dfsRelated;

import java.util.LinkedList;
import java.util.List;

public class AllSubsetsI {
	
	public List<String> subSets(String set) {
		List<String> result = new LinkedList<>();
		if (set == null) {
			return result;
		}
		StringBuilder sb = new StringBuilder();
		char[] input = set.toCharArray();
		findSub(input, 0, sb, result);
	 	return result;
	}
	
	private void findSub(char[] input, int index, StringBuilder sb, List<String> result) {
		if (index == input.length) {
			result.add(sb.toString());
			return; // base case, remember to return!!!
		}
		
		sb.append(input[index]);
		findSub(input, index + 1, sb, result);
		sb.deleteCharAt(sb.length() - 1);
		
		findSub(input, index + 1, sb, result);
	}

		  
    /* ----------------------< the same as above, different input type >-------------------------*/
	
	public void findSubset(char[] input, int index, StringBuilder output) {
		if (index == input.length) { 
			System.out.println(output);
			return;
		}
		output.append(input[index]);
		findSubset(input, index + 1, output);
		output.deleteCharAt(output.length() - 1);
		
		findSubset(input, index + 1, output);
	}
	
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		AllSubsetsI testObj = new AllSubsetsI();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		char[] input = {'a', 'b', 'c'};
		StringBuilder output = new StringBuilder();
		testObj.findSubset(input, 0, output);
		
		List<String> result  = testObj.subSets("abc");
		 
		for (String s : result) {
			System.out.println(s + " ");
		}
		
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
