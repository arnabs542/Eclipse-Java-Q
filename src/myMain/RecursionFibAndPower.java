// Created Date: May 14, 2018
// Application: Practice recursion, calculate power and fibonacci sequence

package myMain;

public class RecursionFibAndPower {
	
	public int factorialWithRecursion(int n) {
		 if(n == 1) return 1;
		 return factorialWithRecursion(n - 1) * n; 
	}
	
	public int factorial(int n) {
	    int result = 1;
	    for(int i = 1; i <= n; i++){
	    		result *= i;
	    }
	    return result;
	}
		
	// given K, find the Kth number in the sequence
	public static long fibonacci(int K) {
		//bad algorithm, too much recursion, use DP, but no idea how to do...
		
		if(K <= 0) return 0;
		if(K == 1) return 1;
		return fibonacci(K - 1) + fibonacci(K - 2);		   
    }
	// n <= 39: time: 1503ms, space：9424k
	
	public static long fibonacci1(int K) {
		
	    long[] fib = new long[K + 1];
	    if (K <= 0) return 0;
	    if (K == 1) return 1;
	    	
	    fib[0] = 0;
	    fib[1] = 1;
	    
	    for (int i = 2; i <= K; i++) {
	    		fib[i] = fib[i - 1] + fib[i - 2];
	    }	    	
	    return fib[K]; 		
	}
	// n <= 39: time: 12ms, space：9392k
		
 	public static void main(String[] args) {	
		//System.out.print(RecursionFibAndPower.power(1,2147483647)); 
		
		System.out.print(fibonacci1(50));
	} 
}
