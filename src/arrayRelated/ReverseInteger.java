/*
 * == Created Date == 
 * September 2, 2018
 * 
 * == Question - Reverse Integer (easy+) ==
 * Given a 32-bit signed integer, reverse digits of an integer.
 *   
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. 
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 *   
 * == Example == 
 * Input: 123, Output: 321
 * Input: -123, Output: -321
 * Input: 120, Output: 21
 * Input: 123, Output: 321
 * Input: 1000000003, Output: 0
 * 
 * == Note ==
 * Seems like a very easy question, but actually has a lots of traps.
 * I used a stack at first, very inefficient. 
 * And I forgot the negative input and overflow issue. 
 *   
 * == Solution ==
 * Pop and Push Digits & Check before Overflow
 * 
 * pop operation: 
 *   pop = x % 10;
 *   x /= 10;
 *   
 * push operation:
 *   temp = rev * 10 + pop;
 *   rev = temp;
 */
package arrayRelated;

public class ReverseInteger {
    public int reverse(int x) {        
        long res = 0;      
        while (x != 0) {
            int dig = x % 10;
            x /= 10;
            res = res * 10 + dig;
        }
        return Math.abs(res) >= Integer.MAX_VALUE ? 0 : (int) res;
    }
}
