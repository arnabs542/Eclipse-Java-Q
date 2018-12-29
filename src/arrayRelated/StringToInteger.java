/*
 * == Created Date ==
 * December 17, 2018
 * 
 * == Question - String to Integer (atoi) ==
 *   
 * == Notes == 
 * LeetCode 8
 * 
 */

package arrayRelated;

public class StringToInteger {
    /*
    
    corner cases:
    1. null or empty
    2. leading spaces
    3. sigh, + or -
    4. trailing spaces or other characters
    5. overflow an iteger
    6. overflow a long
    7. tailing spaces or other characters
    
    */
    
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) { // corner case 1
            return 0; // should clarify with the interviewee about the return value in corner cases
        }
        int n = str.length();
        int i = 0;
        while (i < n - 1 && str.charAt(i) == ' ') { // corner case 2
            i++;
        }
        boolean isPositive = true;
        if (i < n && str.charAt(i) == '+' || str.charAt(i) == '-') { // corner case 3
            isPositive = str.charAt(i) == '+';
            i++;
        }
        long sum = 0; 
        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') { 
            sum = sum * 10 + (str.charAt(i) - '0');
            if (sum > (long)Integer.MAX_VALUE + 1) { // corner case 6; why +1, 2147483647 -2147483648
                break;
            }
            i++;
        }
        sum = isPositive ? sum: -sum;
        if (sum > (long)Integer.MAX_VALUE) { // corner case 5
            return Integer.MAX_VALUE;
        }
        if (sum < (long)Integer.MIN_VALUE) { // corner case 5
            return Integer.MIN_VALUE;
        }
        return (int)sum;
    }	
}
