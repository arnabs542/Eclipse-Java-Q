/*
 * == Created Date ==
 * September 2, 2018
 * 
 * == Question - Roman To Integer (easy) ==
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 
 * Given a roman numeral, convert it to an integer. 
 * Input is guaranteed to be within the range from 1 to 3999.
 *   
 * == Example == 
 * Input: "III", Output: 3
 * Input: "IV", Output: 4
 * Input: "IX", Output: 9
 * Input: "MCMXCIV", Output: 1994 (Explanation: M = 1000, CM = 900, XC = 90 and IV = 4)
 *   
 * == Follow up == 
 * Integer To Roman
 * 
 */

package arrayRelated;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
	public int romanToInt(String s) {
		Map<Character, Integer> romToInt = getRomToIntMap();		
		int res = 0;		
		int cur = 0;
		while (cur < s.length()) {
			int sVal = romToInt.get(s.charAt(cur));
			if (cur + 1 < s.length()) {
				int nextVal = romToInt.get(s.charAt(cur + 1));			
				if (nextVal > sVal) {
					res = res + nextVal - sVal;
					cur += 2;
				} else {
					res += sVal;
					cur++;
				}
			} else {
				res += sVal;
				cur++;
			}
		}
		return res;
    }
	
	private Map<Character, Integer> getRomToIntMap() {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		return map;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(1); ?
	
	/* -----< Integer to Roman >---------*/	
	public String intToRoman(int num) {
		int[] intVal = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
		String[] romans = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < intVal.length; i++) {
			while (num - intVal[i] >= 0) {
				num -= intVal[i];
		        sb.append(romans[i]);
		    }
		}
		return sb.toString();
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		RomanToInteger testObj = new RomanToInteger();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		System.out.println(testObj.romanToInt("M"));
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.romanToInt("IX"));
		System.out.println(testObj.romanToInt("III"));
		System.out.println(testObj.romanToInt("MCMXCIV"));
		System.out.println(testObj.romanToInt("MM"));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
