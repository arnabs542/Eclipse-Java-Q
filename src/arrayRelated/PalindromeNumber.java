/*
 * == Created Date ==
 * Dec 16, 2018
 * 
 * == Question - Palindrome Number ==
 *   
 * == Notes == 
 * LeetCode 9, easy
 * 
 */

package arrayRelated;

import java.util.*;

public class PalindromeNumber {
	
    public boolean isPalindromeMeth1(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
        		return true;
        }
        
        List<Integer> list = new ArrayList<>();    
        while (x > 0) { // O(log_10(n))
        		list.add(x % 10);
        		x /= 10;
        }     
        
        int left = 0;
        int right = list.size() - 1;
        while (left < right) { // O(n)
        		if (list.get(left) != list.get(right)) {
        			return false;
        		}
        		left++;
        		right--;
        }
        return true;
    }
    
    /* ----------------------< Better Solution >-------------------------
     * Revert half of the int number
     * 
     * Time Complexity: O(log_10(n))
     * Space Complexity: O(1)
     * 
     * */
    public boolean isPalindromeMeth2(int x) {
        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
        		// for "1221"
        		// 1st round: 1221 % 10 = 1, revertedNumber = 1
        		// 2nd round: 122 % 10 = 2, then 1 * 10 + 2 = 12, we get the reverse half 
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return x == revertedNumber || x == revertedNumber/10;
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		PalindromeNumber testObj = new PalindromeNumber();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.isPalindromeMeth1(11211));
		System.out.println(testObj.isPalindromeMeth2(11211));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
