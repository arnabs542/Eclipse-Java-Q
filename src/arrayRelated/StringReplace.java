/*
 * Created Date: July 4, 2018
 * 
 * Question - String Replace (hard)：
 *   Given an original string input, and two strings S and T, 
 *    replace all occurrences of S in input with T.
 *   
 *   Example: 
 *     input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
 *     input = "dodododo", S = "dod", T = "a", input becomes "aoao" 
 *     
 *  Updated: 
 *    July 6, 2018: So many conditions!!!! 
 * 
 */

package arrayRelated;

import java.util.ArrayList;
import java.util.List;

public class StringReplace {
	
	public String replace(String input, String origin, String succ) {
		char[] in = input.toCharArray();
		if (origin.length() >= succ.length()) {
			return replaceShorter(in, origin, succ);
		} else {
			return replaceLonger(in, origin, succ);
		}
	}

	private String replaceShorter(char[] in, String origin, String succ) {
		int fast = 0; 
		int slow = 0;
		while (fast < in.length) {
			if (fast <= in.length - origin.length() && find (in, fast, origin)) {
				copy(in, slow, succ);
				slow += succ.length();
				fast += origin.length();
			} else {
				in[slow++] = in[fast++];
			}
		}
		return new String(in, 0, slow);
	}
		
	private String replaceLonger(char[] in, String origin, String succ) {
		List<Integer> matches = findMatches(in, origin);
		char[] result = new char[in.length + matches.size() * (succ.length() - origin.length())];
		int slow = result.length - 1;
		int fast = in.length - 1;
		int lastMatch = matches.size() - 1;
		while (fast >= 0) {
			if (lastMatch >= 0 && fast == matches.get(lastMatch)) {
				copy(result, slow - succ.length() + 1, succ); 
				slow -= succ.length();
				fast -= origin.length();
				lastMatch--;
			} else {
				result[slow--] = in[fast--];					
			}
		}
		return new String(result);
	}
	
	private List<Integer> findMatches(char[] in, String origin) {
		List<Integer> matches = new ArrayList<>();
		int index = 0;
		while (index <= in.length - origin.length()){
			if (find(in, index, origin)) {
				matches.add(index + origin.length() - 1);
				index += origin.length();
			} else {
				index++;
			}
		}
		return matches;
	}
	
	private void copy(char[] in, int fromIndex, String succ) {
		 for (int i = 0; i < succ.length(); i++) {
			 in[fromIndex + i] = succ.charAt(i);
		 }
	}
	
	private boolean find(char[] in, int fromIndex, String origin) {		
		for (int i = 0; i < origin.length(); i++) {
			if (in[fromIndex + i] != origin.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		StringReplace testObj = new StringReplace();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		String s1 = "appledogapple";
		String result1 = testObj.replace(s1, "apple", "dog");
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		String s2 = "bbbbbeeebbbbb";
		String result2 = testObj.replace(s2, "bbbbb","eee");
		System.out.println(result2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");	
		
		String s3 = "aaa";
		String result3 = testObj.replace(s3, "aa","bbb");
		System.out.println(result3);
	}
}
