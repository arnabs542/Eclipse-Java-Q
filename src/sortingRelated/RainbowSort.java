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
 *    Rainbow Sort with four or more colors
 *   
 * Updated:
 *   June 30, 2018
 * 
 */

package sortingRelated;

public class RainbowSort {
		
	// Time Complexity: O(n)
	// Space Complexity: O(1)
	public static void rainbowSort(int[] arr) {
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
	
	public static void rainbowSortFourColor(int[] arr) {
		
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
				three--;
				four--;
			} else if (arr[three] == 3) {
				three--;
			} else if (arr[three] == 2) {
				swap (arr, three, two);
				two++;	
			} else {
				swap (arr, three, one);
				one++;
				//two++;
			}			
		}		 		
	}
	
	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public static void main(String[] args) {
		int[] arr = {-1, 0, 1, 0, -1, 1, 0, -1, 0, 1, 1};
		
		rainbowSort(arr);
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.print("\n");
		
		int[] arr1 = {2, 4, 2, 4, 1, 2, 3, 2, 1 , 2, 1, 3, 4, 3, 2, 1, 2, 3, 4};
		rainbowSortFourColor(arr1);
		for(int i : arr1) {
			System.out.print(i + " ");
		}
	}
// 3, 4, 3, 2, 1, 2, 3, 4
}

