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
 * LeetCode 14
 * 
 */

package arrayRelated;

public class LongestCommonPrefix {
	
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
        		return "";
        }
        int cur = 0;
        int maxLen = Integer.MIN_VALUE;    
        boolean notEqual = false;
        boolean larger = false;
        while (true) {
            char curChar = '#';            
            for (String str : strs) {
                if (str == "") {
                    notEqual = true;
                    break;
                }
                maxLen = Math.max(maxLen, str.length());
                if (str.length() > cur) {
                    if (curChar == '#') {
                        curChar = str.charAt(cur);
                    } else if (curChar != str.charAt(cur)) {
                        notEqual = true;
                        break;
                    }
                } else {
                		larger = true;
                		break;
                } 
            }
            if (notEqual || larger) {
                break;
            }
            cur++;
            if (cur >= maxLen) {
                break;
            }
        }
        if (cur == 0) {
        		String res = "";
            return res;
        }
        return strs[0].substring(0, cur);
    }
    
	// Time Complexity: O(n * len); n : number of strings, len: longest prefix length
	// Space Complexity: O(1);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		LongestCommonPrefix testObj = new LongestCommonPrefix();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		String[] strs0 = {""};
		System.out.println(testObj.longestCommonPrefix(strs0));
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		String[] strs1 = {"a", "ab"};
		System.out.println(testObj.longestCommonPrefix(strs1));
		
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
