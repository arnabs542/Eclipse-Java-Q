/*
 * Created Date: August 30, 2018
 * 
 * Question - Pow(x, n):
 *   Implement pow(x, n), which calculates x raised to the power n (x ^ n).
 *   
 *   Example: 
 *   Input: 2.00000, 10 - Output: 1024.00000
 *   Input: 2.10000, 3 - Output: 9.26100
 *   Input: 2.00000, -2 - Output: 0.25000 (Explanation: 2^(-2) = 1/(2^2) = 1/4 = 0.25)
 * 
 */

package myMain;

public class Power {
	
	/* ----------------------< Bad Algorithm, too much recursions >-------------------------*/
	
	// Time Complexity: O(b), 
	// Space Complexity: O(1)
	public double powerBad1(double a, double b) {
		if (a == 0) { // 0 ^ xxxxx = 0
			return 0;
		} 
        if (a == 1 || b == 0) { // 1 ^ xxx = 1, // xxx ^ 0 = 1
            return 1;
        } 
		double result = 1;	
		for (int i = 0; i < Math.abs(b); i++) {
			result *= a;
		}
		if (b < 0) {
			result = (double) 1 / result;
		}
		return result;	
	}	
	
	// Time Complexity: O(b)
	// Space Complexity: O(b)
	public long powerBad2(int a, int b) {
		if (a == 0) return 0;
		if (b == 0) return 1;
		
		return a * powerBad2(a, b - 1);			    
	}

    /* ----------------------< Better Solution & Consider corner cases>-------------------------*/
	// Time Complexity: O(log(b))
	// Space Complexity: O(log(b))
	
	public Double myPow(double x, int n) {
		if (x == 0) {
			if (n <= 0) { // 0 ^ (-1) = 1 / 0, error
				return null;
			}
			return (double) 0;
		}
		
		double result;	
		if (n < 0) {
			result = 1 / power(x, -n);
		} else {
			result = power(x, n);
		}
		return result;
	}
	
	private double power(double x, int n) {
//		System.out.println("x = " + x + ", n = " + n);
		if (n == 0) { // base case;
			return 1;
		}
		double half = power(x, n / 2);
		if (n % 2 == 0) {
			return half * half;
		} else {
			return half * half * x;
		}
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		Power testObj = new Power();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		System.out.println(testObj.myPow(2, -2)); // expected: 0.25
		System.out.println(testObj.myPow(1, -2)); // expected: 1
		System.out.println(testObj.myPow(2, 3)); // expected: 8
		System.out.println(testObj.myPow(2.1, -3)); // expected: 0.10798
		
		System.out.println(testObj.myPow(0, -1)); // expected: null
		
		System.out.println(testObj.myPow(78, 0)); // expected: 1
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
	}
}

/* Systematic way of thinking 
 * 
 *  Common mistakes:
 *  1. 0 ^ (-1)
 *  
 *  
 */