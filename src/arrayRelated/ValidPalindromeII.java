/*
 * == Created Date ==
 * September 30, 2018
 * 
 * == Question - Valid Palindrome II (E) ==
 * Given a non-empty string s, you may delete at most one character. 
 * Judge whether you can make it a palindrome.  
 * 
 */
package arrayRelated;

public class ValidPalindromeII {
	
    public boolean validPalindrome(String s) {
        int lo = 0;
        int hi = s.length() - 1;
        while (lo < hi) {
            if (s.charAt(lo) == s.charAt(hi)) {
                lo++;
                hi--;
            } else {
                return isPalin(s, lo + 1, hi) || isPalin(s, lo, hi - 1);
            }
        }
        return true;
    }
    
    private boolean isPalin(String s, int lo, int hi) {
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) {
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }
}
