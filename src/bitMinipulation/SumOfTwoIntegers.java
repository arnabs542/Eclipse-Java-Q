/*
 * == Created Date ==
 * October 6, 2018
 * 
 * == Question - Sum Of Two Integers (E) ==
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.  
 * 
 */

package bitMinipulation;

public class SumOfTwoIntegers {
	
    public int getSum(int a, int b) {
        int c = a | b;
        int res = a | b;
//        int power = 0;
//        while (c != 0) {
//            if (((c >>> 1) & 1) == 1) {
//                res += Math.pow(2, power);
//                power++;
//            }
//        }
        return res;
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		SumOfTwoIntegers testObj = new SumOfTwoIntegers();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.getSum(1, 2));
		
		System.out.println(testObj.getSum(10, 20));
		System.out.println(testObj.getSum(12, 2));
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
