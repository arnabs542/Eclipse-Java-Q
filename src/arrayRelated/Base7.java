/*
 * == Created Date ==
 * Dec 30, 2018
 * 
 * == Question - Base 7 ==
 *   
 * == Notes == 
 * LeetCode 504(E)
 *   
 */

package arrayRelated;

public class Base7 {

	/* ----- < Method 1 - Use StringBuilder > ----- */
    public String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        boolean isNegtive = false;
        if (num < 0) {
            num = -num;
            isNegtive = true;
        }
        while (num > 0) {
            int digit = num % 7;
            sb.append(Integer.toString(digit));
            num /= 7;
        }
        if (isNegtive) {
        		sb.append("-");
        }
        sb.reverse();
        
        System.out.println(sb.toString());
        return sb.toString();
    }
    
    /* ----- < Method 2 > ----- */
    public String convertToBase7II(int num) {
        if (num == 0) {
            return "0";
        }
        boolean isNegtive = false;
        int sum = 0;
        int carry = 1;
        if (num < 0) {
            num = -num;
            isNegtive = true;
        }
        while (num > 0) {
            sum = num % 7 * carry + sum;
            carry *= 10;
            num /= 7;
        }
        if (isNegtive) {
            sum = -sum;
        }
        
        System.out.println(Integer.toString(sum));
        return Integer.toString(sum);
    }
    
    /* ----- < Method 3 > ----- */
    public String convertToBase7III(int num) {
    		System.out.println(Integer.toString(num, 7));
    		return Integer.toString(num, 7);
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		Base7 testObj = new Base7();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		testObj.convertToBase7(-7);
		testObj.convertToBase7(100);
		
		testObj.convertToBase7II(-7);
		testObj.convertToBase7II(100);
		
		testObj.convertToBase7III(-7);
		testObj.convertToBase7III(100);
		testObj.convertToBase7III(0);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
