/*
 * Created Date: May 13, 2018
 * Application: Practice three basic sorting algorithms
 * 
 */

package sortingRelated;

public class BasicSorting {
	
	static void bubbleSort(int[] arr) {
		
		for(int i = arr.length - 1; i > 0; i--) {
			
			for(int k = 0; k < i; k++) {
				
				if(arr[k] > arr[k+1]) {
					int temp = arr[k];
					arr[k] = arr[k+1];
					arr[k+1] = temp;
				}			
			}					
		}	
	}
	
	static void selectionSort(int[] arr) {
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
	
	static void insertionSort(int[] arr) {

		for(int i = 0; i < arr.length; i++) {
			
			int temp = arr[i];
			
			for(int k = 0; k < i; k++) {
				
				if(temp <= arr[k]) {
					
					for(int j = i; j > k; j--) {
						
						arr[j] = arr[j-1];
					}				
					arr[k] = temp;	
					break;
				}				
			}
		}		
	}
	
	static void print(int[] arr) {
		for(int i: arr) {
			System.out.print(i + " ");
		}
		System.out.print("\n");
	}
	
	
	public static void main(String[] args) {		
	    int[] arr = new int[]{2,0,1,8,0,5,13,18,27};

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
	}
}



