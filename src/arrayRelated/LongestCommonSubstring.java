/*
 * Created Date: August 18, 2018
 * 
 * Question - Longest Common Substring:
 *   Find the longest common substring of two given strings.
 *   
 *   Example: 
 *     S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”
 *   
 */

package arrayRelated;

public class LongestCommonSubstring {
	
	public String longestCommon(String source, String target) {
		StringBuilder res = new StringBuilder();

		return res.toString();
	}
	
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		LongestCommonSubstring testObj = new LongestCommonSubstring();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		String result1 = testObj.longestCommon("aaaaaa", "bbb");
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		String result2 = testObj.longestCommon("abb", "bbbbbb");
		System.out.println(result2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
