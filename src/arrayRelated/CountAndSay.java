/*
 * Created Date: September 1, 2018
 * 
 * Question - Count And Say (easy):
 *   Given a sequence of number: 1, 11, 21, 1211, 111221, …
 *   The rule of generating the number in the sequence is as follow:
 *   
 *   1 is "one 1" so 11.
 *   11 is "two 1s" so 21.
 *   21 is "one 2 followed by one 1" so 1211.
 *   1211 one 1, one 2, two 1s = 111221
 *   
 *   Find the nth number in this sequence.
 *   
 */

package arrayRelated;

public class CountAndSay {
	
	public String countAndSay(int n) {

		String res = "1";
		for (int i = 1; i <= n - 1; i++) {		
			res = say(res);
		}
		System.out.println("n = " + n + ", res = " + res);
		return res;
	}
	
	private String say(String input) {
		if (input == "1") {
			return "11";
		}
		char pre = input.charAt(0);
	    int count = 1;
	    StringBuilder sb = new StringBuilder();

	    for (int i = 1; i < input.length(); i++) {
	    		char cur = input.charAt(i);    		
			if (cur == pre) {
			    count++;
			} else {				
				sb.append(Integer.toString(count));
				sb.append(pre);
				count = 1;
				pre = cur;	
			}
		    if (i == input.length() - 1) {
				sb.append(Integer.toString(count));
				sb.append(cur);
		    }
	    }
	    return sb.toString();
	}
	
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		CountAndSay testObj = new CountAndSay();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		System.out.println(testObj.say("21"));
		System.out.println(testObj.say("1211"));
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		testObj.countAndSay(1); // 1
		testObj.countAndSay(2); // 11
		testObj.countAndSay(3); // 21
		testObj.countAndSay(4); // 1211
		testObj.countAndSay(5); // 111221
		testObj.countAndSay(10);// 13211311123113112211
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
