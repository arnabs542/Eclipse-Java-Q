/*
 * == Created Date ==
 * October 2, 2018
 * 
 * == Question - Number Of 1 Bits ==
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has 
 * (also known as the Hamming weight).  
 *  
 */

package bitMinipulation;

public class NumberOf1Bits {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
