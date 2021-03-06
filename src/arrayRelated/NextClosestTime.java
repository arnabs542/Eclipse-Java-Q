/*
 * == Created Date ==
 * Jan 12, 2019
 * 
 * == Question - Next Closest Time ==
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. 
 * There is no limit on how many times a digit can be reused.
 * 
 * You may assume the given input string is always valid. 
 * For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 * 
 * == Example 1 == 
 * Input: "19:34"
 * Output: "19:39"
 * 
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  
 *              It is not 19:33, because this occurs 23 hours and 59 minutes later.
 * 
 * == Example 2 == 
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. 
 *              It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
 * 
 * == Notes == 
 * LeetCode 681*(M)
 * 
 */

package arrayRelated;

import java.util.HashSet;
import java.util.Set;

public class NextClosestTime {
	
	/* ----- < Method 1 - Simulation > -----
	 * Time Complexity: O(1) // We try up to 24 * 6024∗60 possible times until we find the correct time.
	 * Space Complexity: O(1);
	 * 
	 * */
    public String nextClosestTime(String time) {
        int cur = 60 * Integer.parseInt(time.substring(0, 2));
        cur += Integer.parseInt(time.substring(3));
        
        Set<Integer> allowed = new HashSet<>();
        for (char c: time.toCharArray()) {
        		if (c != ':') {
        			allowed.add(c - '0');
        		}
        	}

        while (true) {
            cur = (cur + 1) % (24 * 60); // % (24 * 60) to process the next minite after 23:59 
            
            int[] digits = new int[]{cur / 60 / 10, cur / 60 % 10, cur % 60 / 10, cur % 60 % 10};
            search : {
                for (int d: digits) {
                		if (!allowed.contains(d)) {
                			break search;
                		}
                }
                return String.format("%02d:%02d", cur / 60, cur % 60);
            }
        }
    }
    
	/* ----- < Method 2 - Build From Allowed Digits > -----
	 * Time Complexity: O(1) // Try total 4 ^ 4 possible times and take the best one.
	 * Space Complexity: O(1);
	 * 
	 * */
    public String nextClosestTimeII(String time) {
        int start = 60 * Integer.parseInt(time.substring(0, 2));
        
        System.out.print("\nstart = " + start);
        
        start += Integer.parseInt(time.substring(3));
        System.out.print("\nstart = " + start);
        
        int ans = start;
        int elapsed = 24 * 60;
        
        Set<Integer> allowed = new HashSet<>();
        for (char c: time.toCharArray()) if (c != ':') {
            allowed.add(c - '0');
        }

        for (int h1: allowed) {
        		for (int h2: allowed) {
        			if (h1 * 10 + h2 < 24) {
                    for (int m1: allowed) {
                    		for (int m2: allowed) {
                    			if (m1 * 10 + m2 < 60) {
                                    int cur = 60 * (h1 * 10 + h2) + (m1 * 10 + m2);
                                    int candElapsed = Math.floorMod(cur - start, 24 * 60);
                                    if (0 < candElapsed && candElapsed < elapsed) {
                                        ans = cur;
                                        elapsed = candElapsed;
                                    }
                              }
                    		}
                    }
                 }
        		}
        }

        return String.format("%02d:%02d", ans / 60, ans % 60);
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		NextClosestTime testObj = new NextClosestTime();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		System.out.println("\nresult = " + testObj.nextClosestTime("10:10"));
		
		System.out.println("\nresult = " + testObj.nextClosestTimeII("10:10"));
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
