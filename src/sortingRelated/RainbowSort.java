/*
 * Created Date: May 28,2018
 * 
 * Question - Rainbow Sort:
 *   Given an array of balls, where the color of the balls can only be Red, Green or Blue, 
 *    sort the balls such that all the Red balls are grouped on the left side, 
 *    all the Green balls are grouped in the middle and all the Blue balls are grouped on the right side. 
 *    (Red is denoted by -1, Green is denoted by 0, and Blue is denoted by 1).
 *      
 *   Example: 
 *     {0} is sorted to {0}
 *     {1, 0} is sorted to {0, 1}
 *     {1, 0, 1, -1, 0} is sorted to {-1, 0, 0, 1, 1}
 *       
 *   Follow up:
 *    Rainbow Sort II: Rainbow Sort with four or more colors
 *   
 * Updated:
 *   June 30, 2018
 *   July 4, finally solve Rainbow Sort II !!!
 * 
 */

package sortingRelated;

public class RainbowSort {
		
	// Time Complexity: O(n)
	// Space Complexity: O(1)
	public void rainbowSort(int[] arr) {
		if(arr == null || arr.length == 0) return;
		
		int i = 0; 
		int j = 0;
		int k = arr.length - 1;
		
		// ( , i) : -1
		// [i, j) : 0
		// [j, k] : unknown area
		// (k, ) : 1
		
		while (j <= k) {
			if(arr[j] == -1) {
				swap(arr, i, j);
				i++;
				j++;
			} else if(arr[j] == 0) {
				j++;
			} else {
				swap(arr, j, k);
				k--;
			}
		}
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(1);
	public void rainbowSortFourColor(int[] arr) {
		
		// ( , one) : 1
		// [one, two) : 2
		// [two, three) : unknown area
		// [three, four) : 3
		// [four, ) : 4
		
		int one = 0; 
		int two = 0; 
		int three = arr.length - 1; 
		int four = arr.length - 1; 
		
		while (two <= three) {
			if (arr[three] == 4) {
				swap(arr, three, four);
				if (three >= four) {
					three--;
				}
				four--;
			} else if (arr[three] == 3) {
				three--;
			} else if (arr[three] == 2) {
				swap (arr, three, two);
				two++;	
			} else {
				swap (arr, three, one);
				one++;
				if (one > two) {
					two++;
				}
			}			
		}		 		
	}
	
	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	/* ----------------------< test stub >-------------------------*/
	
	private static void print (int[] arr) {
		for(int i : arr) {
			System.out.print(i + " ");
		}
	}
	
	public static void main(String[] args) {
		
		RainbowSort testObj = new RainbowSort();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		
		int[] arr1 =  {-1, 0, 1, 0, -1, 1, 0, -1, 0, 1, 1};
		testObj.rainbowSort(arr1);
		print(arr1);
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		int[] arr2 = {2, 4, 2, 4, 1, 2, 3, 2, 1 , 2, 1, 3, 4, 3, 2, 1, 2, 3, 4};
		testObj.rainbowSortFourColor(arr2);
		print(arr2);
		
		/* Test Case 3 */
		System.out.println("\n---< Test Case 3 >---");
		
		int[] arr3 = {3, 4, 3, 2, 1, 2, 3, 4};
		testObj.rainbowSortFourColor(arr3);
		print(arr3);	
		
		/* Test Case 4 */
		System.out.println("\n---< Test Case 4 >---");
		
		int[] arr4 = {3, 4, 3, 2, 1, 1, 1, 1};
		testObj.rainbowSortFourColor(arr4);
		print(arr4);	
		
		/* Test Case 5 */
		System.out.println("\n---< Test Case 5 >---");
		
		int[] arr5 = {1, 3, 4, 2, 1, 2, 1, 2, 1};
		testObj.rainbowSortFourColor(arr5);
		print(arr5);	
	}
}

