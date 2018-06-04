/*
 * Created Date: May 28,2018
 * Application: Practice Rainbow Sort
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
		
		while(j <= k) {
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
		
		// ( , i) : -1
		// [i, j) : 0
		// [j, k) : unknown area
		// [k, h) : 1
		// [h, ) : 2
		
		int i = 0;
		int j = 0;
		int h = arr.length - 1;
		int k = arr.length - 1;
		
		while(j <= k ) {
			if(arr[k] == 2) {
				swap(arr, k, h);
				k--;
				h--;
			} else if(arr[k] == 1) {
				k--;
			} else if(arr[k] == 0) {
				swap(arr, k, j);
				j++;	
			} else {
				swap(arr, k, i);
				i++;
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
		
		int[] arr1 = {-1, 0, 2, 0, 1, 2, 0, -1, 1, 2, 0, -1, 0, 2, 1, 1};
		rainbowSortFourColor(arr1);
		for(int i : arr1) {
			System.out.print(i + " ");
		}
	}

}

