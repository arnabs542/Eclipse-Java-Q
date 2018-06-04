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
	    if(K <= 0) return 0;
	    if(K == 1) return 1;
	    	
	    fib[0] = 0;
	    fib[1] = 1;
	    
	    for(int i = 2; i <= K; i++) {
	    		fib[i] = fib[i - 1] + fib[i - 2];
	    }	    	
	    return fib[K]; 		
	}
	// n <= 39: time: 12ms, space：9392k
	
	// Time Complexity: O(b)
	// Space Complexity: O(1)
    public static long powerB1(int a, int b) {  	
    		// bad algorithm, too much iterations
    		// will fail in this case: [1,2147483647] 
		 
		 if(b == 0) return 1;		   
		 if(a == 0) return 0;			  
		 long result = 1;
		 
		 for(long i = 0; i < b; i++) {
			 result *= a;
		 }		 
		return result;	 
	}	

	// Time Complexity: O(b)
	// Space Complexity: O(b)
	public static long powerB2(int a, int b) {
		//bad algorithm, too much recursion
		if(a == 0) return 0;
		if(b == 0) return 1;
		
		return a * powerB2(a, b - 1);			    
	}

	// Time Complexity: O(log(b))
	// Space Complexity: O(log(b))
	public static long power(int a, int b) {
		if(a == 0) return 0;
		if(b == 0) return 1;
		long half = power(a, b/2);
		
		if(b % 2 == 0) return half * half;
		else return half * half * a;	
	}	
		
 	public static void main(String[] args) {	
		//System.out.print(RecursionFibAndPower.power(1,2147483647)); 
		
		System.out.print(fibonacci1(50));
	} 
}
