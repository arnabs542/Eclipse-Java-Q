package arrayRelated;

import java.util.ArrayList;
import java.util.List;

public class PlusOne {
	
	/*  -- < Wrong approach >--
	 * ineffient and can not pass case with many digits
	 *  
	 * */
    public int[] plusOneWrong(int[] digits) {
        long num = 0; // use int can not pass test [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
        for (int i = 0; i < digits.length; i++) {
            num += digits[i];
            if (i != digits.length - 1) {
                num *= 10;
            }
        }
        num++;
        
        List<Long> list = new ArrayList<>();
        while (num > 0) {
            list.add(num % 10);
            num /= 10;
        }
        
        int size = list.size();
        int[] result = new int[size];        
        for (int i = 0; i < size; i++) {
            result[i] = list.get(size - i - 1).intValue();
        }
        return result;       
    }
    
    /* --- < Solution >--- */
    public int[] plusOneMeth1(int[] digits) {
    		if (digits == null || digits.length == 0) {
    			return digits; 
    		}       
   		         
    		for (int i = digits.length - 1; i >= 0; i--) {  
    			if (digits[i] < 9) {
    				digits[i]++;
    				return digits;
    			}
    			digits[i] = 0;
    		}    
    		int [] res = new int[digits.length + 1];        
    		res[0] = 1;              
    		return res;
    }
    
	/* ----------------------< test stub >-------------------------*/
    private static void print(int[] res) {
		for (int ele : res) {
	        System.out.print(ele + " ");
		}
		System.out.println();
    }
    
	public static void main(String[] args) {
		
		PlusOne testObj = new PlusOne();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[] arr1 = new int[] {9, 9, 9};
		print(testObj.plusOneWrong(arr1));
		print(testObj.plusOneMeth1(arr1));
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		int[] arr2 = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		print(testObj.plusOneWrong(arr2));
		print(testObj.plusOneMeth1(arr2));	
		
		/* Test Case 3 */
		System.out.println("\n---< Test Case 3 >---");
		int[] arr3 = {7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,
					 4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,6};
		
		print(testObj.plusOneWrong(arr3)); // will fail
		print(testObj.plusOneMeth1(arr3));
	}

}
