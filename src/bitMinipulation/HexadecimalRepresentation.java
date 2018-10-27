/*
 * == Created Date == 
 * July 21 2018
 * 
 * == Question - Hexadecimal Representation (easy) == 
 * Given an integer, write an algorithm to convert it to hexadecimal. 
 * For negative integer, two’s complement method is used.
 *  
 * == Examples == 
 * 0's hex representation is "0x0"
 * 255's hex representation is "0xFF" 
 * 
 * == Note == 
 * LeetCode 405
 * 
 */

package bitMinipulation;

public class HexadecimalRepresentation {
	
	/* --- Solution1 --- 
	 * doesn't know how to process the negative value 
	 * 
	 */
	public String hex(int number) {
		String prefix = "0x";	
	    if (number == 0) { // corner case
	        return prefix + 0;	    		
	    }
	    if (number < 0) {
	    		number = Integer.MAX_VALUE;
	    }
	    
		char[] set = {'A', 'B', 'C', 'D', 'E', 'F'};
		StringBuilder sb = new StringBuilder();
	    while (number > 0) {
	        int rem = number % 16;
	        number /= 16;
	        if (rem < 10) {
	          sb.append(rem);
	        } else {
	          sb.append(set[rem - 10]); // or: sb.append((char)(rem - 10 + 'A'));
	        }
	    }
	    return prefix + sb.reverse().toString(); // remember to reverse!!!    
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(n);
	
    
	/* ---------------- Solution2 ---------------------
	 * 
	 *  -1: 11111...11..1111
	 *  15: 00000...00..1111
	 *  				    1111 = 15, map[15] = f
	 *  
	 *  -2: 11111...11..1110
	 *  15: 00000...00..1111
	 *                  1110 = 14, map[14] = 2
	 *  
	 *  
	 */
	
    char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    
    public String toHex(int num) {
        if (num == 0) {
        		return "0";
        }
        String result = "";
        while (num != 0){
            result = map[(num & 15)] + result; 
            num >>>= 4;
        }
        return result;
    }

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		HexadecimalRepresentation testObj = new HexadecimalRepresentation();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		System.out.println(0 + 'a'); // 97
		System.out.println('a' - 'A'); // 32
		System.out.println((char)('B' + 'a' - 'A'));
		System.out.println(0 + 'A'); // 65
		System.out.println(1 + 'A'); // 66
		System.out.println((char)(1 + 'A')); // B
		
		
		String result0 = testObj.hex(0);
		System.out.println(result0);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		System.out.println(testObj.hex(255));
		System.out.println(testObj.toHex(255));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		System.out.println(testObj.hex(-1));
		System.out.println(testObj.toHex(-1));
		System.out.println(testObj.toHex(-2));
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
