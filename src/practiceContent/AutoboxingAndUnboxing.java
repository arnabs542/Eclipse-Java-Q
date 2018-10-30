package practiceContent;

import java.util.ArrayList;
import java.util.List;

public class AutoboxingAndUnboxing {
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
	/* Autoboxing */
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i); // -> list.add(Integer.valueOf(i));
		}
					
	/* Unboxing */
		Integer a = 5; // autoboxing
		a++;
		// Integer is immutable, the int value of the Integer object can never change,
		// so wehen a++ means:
		// 		int temp = a.intValue();
		// 		temp++;
		// 		a = Integer.valueOf(temp)
		
		// arithmetic operations (+ - * / > < <= >= ...) only applied to primitive type	
		Integer b = 5;
		Integer c = 5;
		System.out.println(b == c); // true, unboxing, a.intValue() == b.intValue()
		System.out.println(b + 2); // 7, unboxing
		
	/*
	 * Integer class caches the Integer objects with values from -128 to 127,
	 * so every time an Integer object within this range is needed, it will always return the correspingding object
	 * 
	 */
		System.out.println("---< Pooling For Integer>---");
		a = 129;
		b = 129;
		System.out.println(a == b); // false
		
		Integer int1 = 127;
		Integer int2 = 127;
		System.out.println(int1 == int2); // expected: true -  Pooling for -128 ~ 127
				
		Integer int3 = 129;
		Integer int4 = 129;
		System.out.println(int3 == int4); // expected: false - no pooling
		System.out.println(int3.equals(int4)); // so we should use equals() to compare value of two integers

		Integer int5 = new Integer(12);
		Integer int6 = new Integer(12);
		System.out.println(int5 == int6); // expected: false, no Pooling now
		System.out.println(int5.equals(int6)); // expected: true

		
		/* ---------< Pooling for String objects >------------------
		 * 
		 * Whenever it encounters a string literal in your code, 
		 *   the compiler creates a String Object with its value
		 *  
		 * 
		 */
		System.out.println("---< Pooling for String objects >---");
		String s1 = "bell";
		String s2 = "bell";
		System.out.println(s1 == s2); // expected: true -  Pooling for String object
		
		
		String s3 = new String("bell");
		String s4 = new String("bell");
		System.out.println(s1 == s3); // expected: false - String objects created by "new" will not use the pool
		System.out.println(s3.equals(s4)); //  expected: true, we should use equals() to compare value of two integers
		
		
		/* ---------< Concatenate Strings >-----------
		 * 
		 */
		System.out.println("---< Concatenate Strings >---");
		String str1 = "bell is";
		String str2 = "the best";
				
		// concat() will create a new String object rather that change the value of s1 or s2 - Immutability
		str1.concat(str2); 
		
	}

}
