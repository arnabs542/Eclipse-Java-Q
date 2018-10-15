/*
 * == Created Date ==
 * October 14, 2018
 * 
 * == Question - Longest Common Prefix==
 *   
 *   
 * == Example == 
 *   
 * == Notes == 
 * LeetCode 28
 * 
 */


package arrayRelated;

public class ImplementstrStr {
	
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (isSame(haystack, i, needle)) {
                return i;
            }
        }
        return -1;
    }
    
    private boolean isSame(String haystack, int left, String needle) {
        for (int i = 0; i < needle.length(); i++) {
            if (haystack.charAt(left) != needle.charAt(i)) {
                return false;
            }
            left++;
        }
        return true;
    }
    
	// Time Complexity: O(H * N); H: len of haystack; N: len of needle
	// Space Complexity: O(1);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ImplementstrStr testObj = new ImplementstrStr();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.strStr("aaa", "a"));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
