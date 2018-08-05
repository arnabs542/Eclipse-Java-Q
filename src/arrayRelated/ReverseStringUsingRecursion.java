/*
 * Created Date: August 4, 2018
 * 
 * Question: Reverse String Using Recursion (easy+)
 * 
 */

package arrayRelated;

public class ReverseStringUsingRecursion {

	public String reverse(String input) {
		char[] in = input.toCharArray();
		reverse(in, 0, in.length - 1);
	    return new String(in);
	}
	
	private void reverse(char[]in, int left, int right) {
		if (left >= right) { // base case
			return;
		}
		char temp = in[left];
		in[left++] = in[right];
		in[right--] = temp;
		reverse(in, left, right);
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ReverseStringUsingRecursion testObj = new ReverseStringUsingRecursion();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		String res0 = testObj.reverse("a");
		System.out.println(res0);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		String res1 = testObj.reverse("abcde");
		System.out.println(res1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}
