/*
 * == Created Date ==
 * September 13, 2018
 * 
 * == Question - Valid Palindrome ==
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * Define empty string as valid palindrome.
 *   
 * == Example == 
 *   Input: "A man, a plan, a canal: Panama", Output: true
 *   Input: "race a car", Output: false  
 *   
 * == Notes ==
 * This questiong has 300 like and 1k+ dislike on LeetCode, hhhhhhhh
 * 
 */

package arrayRelated;

public class ValidPalindrome {
	
	//  A man, a plan, a canal: Panama
	//  |                            |
	// left                         right
	//  2                             1
	
	//  race a car
	//     |  \
	//  left  right
	//   1       1
	
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
        
            int leftType = isAlpha(s.charAt(left));
            int rightType = isAlpha(s.charAt(right));
            
//            System.out.println("consider " + s.charAt(left) + " type = " +  leftType);
//            System.out.println("consider " + s.charAt(right) + " type = " +  rightType);
            
            if (leftType == -1) {
              left++;
            } else if (rightType == -1) {
              right--;
            } else {
                if (leftType == 1 && rightType == 2 || leftType == 2 && rightType == 1) {
                    int leftDiff = calDiff(s.charAt(left));
                    int rightDiff = calDiff(s.charAt(right));                   
                    if (leftDiff != rightDiff) {
                        return false;
                    }
                } else if (s.charAt(left) != s.charAt(right)){
                    return false;
                } 
                
                left++;
                right--;
            }               
        }
        return true;
    }
    
    private int calDiff(char a) {
        int res = 0;
        if (a >= 'A' && a <= 'Z') {
            res = a - 'A';
        } else {
            res = a - 'a';
        }
        return res;
    }
    
    private int isAlpha(char c) {
        if (c >= '0' && c <= '9') {
            return 0;
        }
        if (c >= 'a' && c <= 'z') {
            return 1;
        }
        if (c >= 'A' && c <= 'Z') {
            return 2;
        }
        return -1;
    } 
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ValidPalindrome testObj = new ValidPalindrome();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.isPalindrome("race a car")); // expected: false
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		System.out.println(testObj.isPalindrome("A man, a plan, a canal: Panama")); // expected: true
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
