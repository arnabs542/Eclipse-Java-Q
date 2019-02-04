/*
 * == Created Date ==
 * Feb 3, 2019
 * 
 * == Question - Sqrt(x) ==
 * Implement int sqrt(int x).
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * 
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 * 
 * == Example 1 ==
 * Input: 4
 * Output: 2
 * 
 * == Example 2 ==
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since 
 *              the decimal part is truncated, 2 is returned.
 *   
 * == Notes == 
 * LeetCode 69(E)
 * 
 */

package binarySearch;

public class Sqrt {

    public static int mySqrt(int x) {
        if (x == 1) {
            return 1;
        }
        int lo = 0;
        int hi = x / 2;
        int result = 0;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (mid * mid == x) {
                result = (int) mid;
                return result;
            } else if (mid * mid < x) {
                result = (int) mid;
                lo = (int) mid + 1;                
            } else {
                hi = (int) mid - 1;
            }
        }
        return result;
    }
    
    
	/**
     * @Description: 计算大于 1 的正整数之平方根
     * @param n- 待求的数, deltaThreshold- 误差的阈值, maxTry- 二分查找的最大次数
     * @return double- 平方根的解
     */
	public static double getSqureRoot(int n, double deltaThreshold, int maxTry) {
		if (n <= 1) {
			return -1.0;
		}
	    double min = 1.0;
	    double max = (double)n;
	    
	    for (int i = 0; i < maxTry; i++) {
		    double middle = (min + max) / 2;
		    double square = middle * middle;
		    double delta = Math.abs((square / n) - 1);
		    System.out.println(String.format("square = %f,  delta = %f", square, delta));
		    if (delta <= deltaThreshold) {
		    		return middle;
		    	} else {
		    		if (square > n) {
		    			max = middle;
		    		} else {
		    			min = middle;
		    		}
		    	}
	    }
	    return -2.0;
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		System.out.println(mySqrt(10));
		
		int number = 10;
		double squareRoot = getSqureRoot(number, 0.000001, 10000);
		if (squareRoot == -1.0) {
			System.out.println(" 请输入大于 1 的整数 ");
		} else if (squareRoot == -2.0) {
			System.out.println(" 未能找到解 ");
		} else {
			System.out.println(String.format("%d 的平方根是 %f", number, squareRoot));
		}
	}
}
