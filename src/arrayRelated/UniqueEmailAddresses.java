/*
 * == Created Date ==
 * Dec 21, 2018
 * 
 * == Question - Unique Email Addresses ==
 * 
 * == Notes == 
 * LeetCode 929, easy
 * 2018.12.21 Google OA
 * 
 */

package arrayRelated;
import java.util.Map;
import java.util.HashMap;

public class UniqueEmailAddresses {
    public int solution(String[] L) {
        // key: email string; value: frequency
        Map<String, Integer> freqMap = new HashMap<>();
        int res = 0;
        for (String email : L) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (email.charAt(i) != '@') {
                if (email.charAt(i) == '+') {
                    while (email.charAt(i) != '@') {
                        i++;
                    }
                    break;
                } else if (email.charAt(i) != '.') {
                    sb.append(email.charAt(i));
                }
                i++;
            }
            String s = sb.toString() + email.substring(i);
            System.out.println(s);
            int frequency = freqMap.getOrDefault(s, 0) + 1;
            res = Math.max(res, frequency);
            freqMap.put(s, frequency);
        }
        return res;
    }
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		UniqueEmailAddresses testObj = new UniqueEmailAddresses();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int res = testObj.solution(new String[] {"unique@example.com", 
				"uni.que@example.com", "unique+test@example.com", "unique@anotherexample.com"});
		
		System.out.println(res);	
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		int res2 = testObj.solution(new String[] {"unique+bob@example.com", 
				"uni.que@example.com", "uni+que+test@example.com", "uni+que@anotherexample.com"});
		
		System.out.println(res2);	
		
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
