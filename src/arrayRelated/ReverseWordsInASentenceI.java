/*
 * == Created Date ==
 * August 2, 2018
 * 
 * == Question - Reverse Words In A Sentence I (medium) == 
 * Reverse the words in a sentence.
 * There are no heading or tailing white spaces
 *   
 * == Example ==
 * “I love Google” → “Google love I”
 *   
 * == Note == 
 * September 23, 2018: fix the code for a bunch of painful corner cases 
 * 
 */

package arrayRelated;

public class ReverseWordsInASentenceI {
	
	/* == Assumption == 
	 * 1. The words are separated by one space character
	 * 2. There are no leading and trailing space
	 * 3. Input is not null
	 * */
	
	public String reverseWordsWithoutSomeCornerCase(String input) {
		char[] in = input.toCharArray();
		reverse(in, 0, in.length - 1);
		int cur = 0;
		while (cur < in.length) {
			int left = cur;
			while (cur < in.length && in[cur] != ' ') {
				cur++;
			}
			reverse(in, left, cur - 1);
			cur++;
		}
	    return new String(in);
	}
	
	// -- < Without the above assumptions > --
	
    public String reverseWords(String s) {
    		s = preProcess(s.toCharArray());
    		return reverseWordsWithoutSomeCornerCase(s);
    }
    
    private String preProcess(char[] in) { 	
    		// remove the leading and trailing spaces. 
    		// Or we can use string.trim() method
        int start = 0;
        while (start < in.length && in[start] == ' ') {
        		start++;
        }     
    
        int end = in.length - 1;
        while (end >= 0 && in[end] == ' ') {
        		end--;     
        }
        
        // adjust the spaces between words to only one
    		StringBuilder sb = new StringBuilder();
    		int index = start;
    		while (index <= end) {
    			sb.append(in[index]);
    			if (in[index] == ' ') {
    				while (index <= end && in[index] == ' ') {
    					index++;
    				}
    			} else {
    				index++;
    			}
    		}
    		return sb.toString();
    }
    
    private void reverse(char[] in, int start, int end) {       
        while (start < end) {
            char temp = in[start];
            in[start++] = in[end];
            in[end--] = temp;
        }
    }
	
	// Time Complexity: O(n);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void print(char[] in) {	
		for(char c : in) {
			System.out.print(c + " ");
		}
		System.out.print("\n");
	}
	
	public static void print(String in) {	
		System.out.print("\"");
		System.out.print(in);
		System.out.println("\"");
	}
	
	public static void main(String[] args) {
		
		ReverseWordsInASentenceI testObj = new ReverseWordsInASentenceI();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		char[] c0A = {'a', 'b', 'c'};
		testObj.reverse(c0A, 0, c0A.length - 1);
		print(c0A);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		print(testObj.reverseWords("I love Google"));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		print(testObj.reverseWords(" 1"));
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");	
		print(testObj.reverseWords("  "));
		
		/* Test Case 4 */
		System.out.println("---< Test Case 4 >---");
		print(testObj.reverseWords("a   "));
		
		/* Test Case 5 */
		System.out.println("---< Test Case 5 >---");
		print(testObj.reverseWords("   a   "));
		
		/* Test Case 6 */
		System.out.println("---< Test Case 6 >---");
		print(testObj.reverseWords("   a   b "));
		
		/* Test Case 7 */
		System.out.println("---< Test Case 7 >---");
		print(testObj.reverseWords("   a   b  c d   e  "));		
	}
}
