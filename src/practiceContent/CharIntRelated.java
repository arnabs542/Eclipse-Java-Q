package practiceContent;

public class CharIntRelated {
	
	

	public static void main(String[] args) {
		
		
		System.out.println(-7 / 2); // expected: -3
		
		System.out.println(-7 >> 1); // expected: -4. right shift one bit. 1001 (-7) --> 1100 (-4)
		
		/* ----------------------< Pooling >-------------------------*/
		System.out.println("---< Pooling >---");
		String s1 = "bell";
		String s2 = "bell";
		System.out.println(s1 == s2); // expected: true -  Pooling for String object
		
		Integer int1 = 12;
		Integer int2 = 12;
		System.out.println(int1 == int2); // expected: true -  Pooling for -128 ~ 127
		
		Integer int3 = -129;
		Integer int4 = -129;
		System.out.println(int3 == int4); // expected: false
		System.out.println(int3.equals(int4)); // so we should use equals() to compare value of two integers

		
		String s3 = new String("bell");
		System.out.println(s1 == s3); // expected: false - String objects created by "new" will not use the pool
		
		Integer int5 = new Integer(12);
		Integer int6 = new Integer(12);
		System.out.println(int5 == int6); // expected: false, no Pooling now
		
		/* ----------------------< Character to String >-------------------------*/
		System.out.println("---< Character to String >---");
		char char1 = 'a';
		char char2 = 'z';
		char char5 = 'A';
		char char6 = 'Z';
		
		char char3 = '0';
		char char4 = '9';

		System.out.println((int)char1);
		System.out.println((int)char2);
		System.out.println((int)char5);
		System.out.println((int)char6);
		
		System.out.println((int)char3);
		System.out.println((int)char4);
		
		char test = ' ';
		System.out.println(test > 'a' && test < 'z' || test > 'A' && test < 'Z' || test > '0' && test < '9');
		
		System.out.println('a' - 'A');
		System.out.println('m' - 'a');
		System.out.println('M' - 'A');
		System.out.println(Math.abs('m' - 'a') == Math.abs('M' - 'a'));
		
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		/* == 
		 * 
		 * equals
		 * 
		 * 
		 */
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int i1 = -1000;
		char c1 = (char)i1;
		System.out.println(c1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
