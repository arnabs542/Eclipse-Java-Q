/*
 * == Created Date ==
 * Dec 18, 2018
 * 
 * == Question - Longest Palindromic Substring ==
 *   
 * == Notes == 
 * LeetCode 5 - Medium+
 * 
 */

package dynamicProgramming;

public class LongestPalindromicSubstring {
	
	public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
        		// The center of a palindrome can be in between two letters for substrings of even length, such as "abba"
        		// thus, calculate i and i + 1
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left;
        int R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
    
	// Time Complexity: O(n^2);
	// Space Complexity: O(1);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		LongestPalindromicSubstring testObj = new LongestPalindromicSubstring();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.longestPalindrome("dababa"));
		
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		System.out.println(testObj.longestPalindrome("abcbx"));
		
		System.out.println(testObj.longestPalindrome("xyzzyabc"));
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
