package myMain;

import java.util.Arrays;

public class DebugPractice {
	
	public static int checkArmstrong(int num) {
		int digitCount = 0, res = 0;
		int temp = num;
		while (temp != 0) {
			temp = temp / 10;
			digitCount++;
		}
		temp = num;
		while (temp != 0) {
			int remainder = temp % 10;
			res += Math.pow(remainder, digitCount);
			temp /= 10;
		}
		if (res == num) {
			System.out.println(true);
			return 1;
		}
		System.out.println(false);
		return 0;
	}
	
	public static int appearsK(int inputArr[], int k) {
		Arrays.sort(inputArr);
		int i = 1, count = 1;
		int e = inputArr[0];
		int res = -1;
		while (i < inputArr.length) {
			if (e == inputArr[i]) {
				count++;
				if (i == inputArr.length - 1 && count == k) { // Original is without this if block
					res = e;
				}
			} else {
				if (count == k) {
					res = e;
				}
				e = inputArr[i];
				count = 1;
			}
			i++;
		}
		System.out.println(res);
		return res;
	}
	
	public static int distinctCount(int[] elements) {
		int[] counted = new int[elements.length];
		int count, flag;
		counted[0] = elements[0];
		count = 1;
		for (int i = 0; i < elements.length; i++) {
			flag = 0;
			for (int j = 0; j < count; j++) {
				if (elements[i] == counted[j]) {
					flag = 1;
				}
			}
			if (flag == 0) { // Original is flag == 1
				count++;
				counted[count - 1] = elements[i];
			}
		}
		System.out.println(count);
		return count;
	}
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		// Class testObj = new Class();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		distinctCount(new int[] {4, 4, 4, 4});
		distinctCount(new int[] {2, 4, 2, 4});
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		appearsK(new int[] {4, 4, 4, 4}, 4);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		checkArmstrong(371);
	}
}
