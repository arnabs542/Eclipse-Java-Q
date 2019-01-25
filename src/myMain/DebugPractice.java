package myMain;

import java.util.Arrays;

public class DebugPractice {
	
	public static void reverseArray(int[] arr) {
		int temp, len = arr.length;
		for (int i = 0; i < arr.length / 2; i++) {
			temp = arr[len - 1];
			arr[len - 1] = arr[i];
			arr[i] = temp;
			len--;
		}
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public static int digitCount(int num) {
		int count = 0;
		int temp = num; // Original is without this variable to store the original num
		while (temp != 0) {
			temp /= 10;
			count++;
		}
		return count;
	}
	
	public static int checkArmstrong(int num) { //!
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
		
		/* Test Case - reverseArray */
		System.out.println("---< Test Case for reverseArray >---");
		reverseArray(new int[] {3, 2, 1});
		reverseArray(new int[] {4, 3, 2, 1});
		
		reverseArray(new int[] {4});
		
		/* Test Case - digitCount */
		System.out.println("---< Test Case for digitCount >---");
		System.out.println(digitCount(100));
		
		
		/* Test Case - distinctCount */
		System.out.println("---< Test Case for distinctCount >---");
		
		distinctCount(new int[] {4, 4, 4, 4});
		distinctCount(new int[] {2, 4, 2, 4});
		
		/* Test Case - appearsK */
		System.out.println("---< Test Case for appearsK >---");
		
		appearsK(new int[] {4, 4, 4, 4}, 4);
		
		/* Test Case - checkArmstrong */
		System.out.println("---< Test Case for checkArmstrong >---");
		checkArmstrong(371);
	}
}
