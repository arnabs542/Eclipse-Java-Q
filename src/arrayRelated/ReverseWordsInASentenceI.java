/*
 * Created Date: August 2, 2018
 * 
 * Question - Reverse Words In A Sentence I (medium):
 *   Reverse the words in a sentence.
 *   
 *   Example: 
 *     “I love Google” → “Google love I”
 *   
 *   Follow up:
 *   
 *   Mirror Question:
 *   
 * 
 */

package arrayRelated;

public class ReverseWordsInASentenceI {
	
	public String reverseWords(String input) {
		char[] in = input.toCharArray();
		reverse(in, 0, in.length - 1);
		int cur = 0;
		while(cur < in.length){
			int left = cur;
			while (cur < in.length && in[cur] != ' ') {
				cur++;
			}
			reverse(in, left, cur - 1);
			cur++;
		}
	    return new String(in);
	}
	
	private void reverse(char[] in, int left, int right) {
		while (left < right) {
			char temp = in[left];
			in[left++] = in[right];
			in[right--] = temp;
		}
	}
	
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void print(char[] in) {
		for(char c : in) {
			System.out.print(c + " ");
		}
		System.out.print("\n");
	}
	public static void main(String[] args) {
		
		ReverseWordsInASentenceI testObj = new ReverseWordsInASentenceI();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		char[] c0A = {'a', 'b', 'c'};
		testObj.reverse(c0A, 0, c0A.length - 1);
		print(c0A);
		
		String s0B = "abcd";
		char[] c0B = s0B.toCharArray(); 
		testObj.reverse(c0B, 0, c0B.length - 1);
		print(c0B);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		String s1 = "I love Google";
		String result1 = testObj.reverseWords(s1);
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		String s2 = "A B C D";
		String result2 = testObj.reverseWords(s2);
		System.out.println(result2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}
