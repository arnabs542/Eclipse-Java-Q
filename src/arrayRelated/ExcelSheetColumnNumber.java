/*
 * == Created Date ==
 * Nov 13, 2018
 * 
 * == Question - ==
 * Excel Sheet Column Number
 *   
 * == Notes == 
 * LeetCode 171
 * 
 */

package arrayRelated;

public class ExcelSheetColumnNumber {
	public int titleToNumber(String s) {
	    int result = 0;
	    for (int i = 0 ; i < s.length(); i++) {
	      result = result * 26 + (s.charAt(i) - 'A' + 1);
	    }
	    return result;
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ExcelSheetColumnNumber testObj = new ExcelSheetColumnNumber();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.titleToNumber("AB"));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
