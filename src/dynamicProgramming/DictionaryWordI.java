/*
 * Created Date: July 8, 2018
 * 
 * Question - Dictionary Word I (medium+):
 *   Given a word and a dictionary, 
 *    determine if it can be composed by concatenating words from the given dictionary.
 *   
 *   Assumptions:
 *     The given word is not null and is not empty
 *     The given dictionary is not null and is not empty and all the words in the dictionary are not null or empty
 *     
 *   Examples:
 *     Dictionary: {“bob”, “cat”, “rob”}
 *     Word: “robob” return false
 *     Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"
 * 
 */

package dynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class DictionaryWordI {
	
	public boolean canBreak(String input, String[] dict) {
		
		Set<String> dictionary = new HashSet<>();
		for (String s : dict) {
			dictionary.add(s);
		}
		
		int size = input.length();
		boolean[] M = new boolean[size + 1];
		M[0] = true;
		M[1] = false;
		for (int i = 2; i <= size; i++) {
			boolean curRst = false;
			for (int k = 0; k < i; k++) {
				if (M[k] && dictionary.contains(input.substring(k, i))) {
					curRst = true;
					break;
				}
			}
			M[i] = curRst;
			System.out.println("M[" + i + "] = " + M[i]);
		}
		return M[size];
	}
	
	// Time Complexity: O(n^3)  // two nested loop * substring() -- O(n)
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		DictionaryWordI testObj = new DictionaryWordI();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		String testString = "0123456";
		System.out.println(testString.substring(0, 1)); // not "01", but "0"!!!, 
		  //the second parameter is the size of substring, not end index
		System.out.println(testString.substring(0, 3));
		System.out.println(testString.substring(1));
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		String[] dict1 = {"rob", "bob", "cat"};
		boolean result1 = testObj.canBreak("robob", dict1);
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		boolean result2 = testObj.canBreak("robcatbob", dict1); 
		System.out.println(result2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
