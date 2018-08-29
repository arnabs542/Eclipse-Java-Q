/*
 * Created Date: May 13, 2018
 * 
 * Application: Practice three basic sorting algorithms
 * 
 */

package sortingRelated;

public class BasicSorting {
	
	public static void bubbleSort(int[] arr) {	
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}	
	}
	
	public static void selectionSort(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {		
			int min = i;		
			for (int k = i + 1; k < arr.length; k++) {
				if (arr[k] < arr[min]) {
					min = k;
				}			
			}			
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}		
	}
	
	public static void insertionSort(int[] arr) {		
		for (int i = 0; i < arr.length; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}	
	}
	
  /*	 old syntax:
   * 
   * public static void insertionSort(int[] arr) {	
   * 	for (int i = 0; i < arr.length; i++) {			
   * 		int temp = arr[i];			
   * 		for (int k = 0; k < i; k++) { // find the position to insert arr[i]			
   * 			if (temp <= arr[k]) {					
   * 				for (int j = i; j > k; j--) { // move the sorted elements backwards to leave space for insertion				
   * 						arr[j] = arr[j - 1];
   * 				}				
   * 				arr[k] = temp;	
   * 				break;
   * 			}				
   * 		}
   * 	}
   * }
   * 
   * */	
	
	// Time Complexity: O(n^2);
	// Space Complexity: O(1);
	
	/* ----------------------< test stub >-------------------------*/
	static void print(int[] arr) {
		for(int i: arr) {
			System.out.print(i + " ");
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args) {
		
		int[] arr = new int[]{2,0,1,8,0,5,13,18,27};
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
	    
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
	    print(arr);
	    BasicSorting.bubbleSort(arr);
	    print(arr);
	    
	    arr = new int[]{2,0,1,8,0,5,13,19,12};
	    print(arr);
	    BasicSorting.selectionSort(arr);
	    print(arr);

	    arr = new int[]{2,0,1,8,0,5,13,19,50};
	    print(arr);
	    BasicSorting.insertionSort(arr);
	    print(arr);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}



