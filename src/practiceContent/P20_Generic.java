package practiceContent;

import java.util.ArrayList;

public class P20_Generic {

	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		ArrayList<Integer> arrInt = new ArrayList<>();
		ArrayList<String> arrStr = new ArrayList<>();
		Class c1 = arrInt.getClass();
		Class c2 = arrStr.getClass();
		
		System.out.print(c1 == c2); // generic type erasure
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
	
}
