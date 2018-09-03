/*
 * == Created Date ==
 * September 3, 2018
 * 
 * == Question - Fibonacci Number ==
 * Get the Kth number in the Fibonacci Sequence. 
 * (K is 0-indexed, the 0th Fibonacci number is 0 and the 1st Fibonacci number is 1).
 *   
 * == Example == 
 * 0th fibonacci number is 0
 * 1st fibonacci number is 1
 * 2nd fibonacci number is 1
 * 3rd fibonacci number is 2
 * 6th fibonacci number is 8
 *   
 * == Notes == 
 * When calculate math problem, remember:
 * - the overflow issue
 * - negative number
 * - error, like 1 / 0
 * 
 */

package dynamicProgramming;

public class FibonacciNumber {
	public long fibonacci(int K) {
		if (K <= 0) { // corner case  
            return 0;
        }
		long[] fib = new long[K + 1];
		fib[0] = 0;
		fib[1] = 1;
		for (int i = 2; i <= K; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		return fib[K];
	}
	
	// Time Complexity: O(N);
	// Space Complexity: O(N);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		FibonacciNumber testObj = new FibonacciNumber();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		System.out.println(testObj.fibonacci(-1));
		System.out.println(testObj.fibonacci(0));
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		System.out.println(testObj.fibonacci(3));
		System.out.println(testObj.fibonacci(6));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		System.out.println(testObj.fibonacci(10));
		System.out.println(testObj.fibonacci(50));
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
