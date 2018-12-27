/*
 * == Created Date ==
 * Dec 24, 2018
 * 
 * == Question - Palindromic Substrings ==
 *   
 * == Notes == 
 * LeetCode 647, medium
 * Pure Storage OA
 * 
 */

package dynamicProgramming;

public class PalindromicSubstrings {
    
	// Method: Expand Around Center
	// Time Complexity: O(n^2);
	// Space Complexity: O(1);
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result++;
            result += expandFromCenter(s, i - 1, i + 1);
            result += expandFromCenter(s, i, i + 1);
        }
        return result;
    }
    
    private int expandFromCenter(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		PalindromicSubstrings test = new PalindromicSubstrings();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(test.countSubstrings("hellolle")); // 13
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		System.out.println(test.countSubstrings("aaa")); // 6
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
