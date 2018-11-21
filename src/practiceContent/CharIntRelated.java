package practiceContent;

public class CharIntRelated {
	
	public static void main(String[] args) {
		
		/* ----------------------< reverse() will not change original object >-------------------------*/
		System.out.println("---< reverse() will not change original object >---");
		String str = "abb";
		StringBuilder sb = new StringBuilder(str);
		System.out.println(sb.toString());
		
		String rev = sb.reverse().toString();
		System.out.println(rev);
		
		System.out.println(str.equals(rev));
		
		/* ----------------------< / & >> >-------------------------*/
		System.out.println("---< / & >> >---");
		System.out.println(-7 / 2); // expected: -3
		
		System.out.println(-7 >> 1); // expected: -4. right shift one bit. 1001 (-7) --> 1100 (-4)
		
		/* ----------------------< Ingeger to String >-------------------------*/
		System.out.println("---< Ingeger to String >---");
		int i = 111;
		String si1 = String.valueOf(i);
		String si2 = Integer.toString(i);
		System.out.println(si1);
		System.out.println(si2);
		
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
