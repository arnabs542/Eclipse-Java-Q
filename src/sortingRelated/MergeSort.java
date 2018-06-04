/*
 * Created Date: May 16, 2018
 * Application: Practice Merge Sort using recursion method
 * 
 */

package sortingRelated;

public class MergeSort {
	
	public static int[] mergeSort(int[] array) {
		if(array == null) return null;
		if(array.length == 0) return array;	
		
		int[] aux = new int[array.length];
		mergeSort(array, aux, 0, array.length - 1);
		return array;		
	}
		
	private static int[] mergeSort(int[] array, int[] aux, int lo, int hi) {
						
		if(lo == hi) return array;
		System.out.print("merge sort: [" + array[lo] + "," + array[hi] +"]\n");
		
		int mid = lo + (hi - lo)/2;
		
		mergeSort(array, aux, lo, mid);
		mergeSort(array, aux, mid + 1, hi);
		merge(array, aux, lo, mid, hi);
		
		return array;
	}
	
	private static void merge(int[] array, int[] aux, int lo, int mid, int hi) {
		
		System.out.print("merge: [" + array[lo] + "," + array[mid] +"]");
		System.out.print(" and [" + array[mid + 1] + "," + array[hi] +"]\n");
	
		for(int k = lo; k <= hi; k++) {
			aux[k] = array[k];
		}

		int i = lo;
		int j = mid + 1;
		
		for(int k = lo; k <= hi; k++) {
			if(i > mid) {			
				array[k] = aux[j++];
			} 
			else if(j > hi) {		
				array[k] = aux[i++];						
			} 
			else if(aux[i] <= aux[j]) {			
				array[k] = aux[i++];	
			} 
			else {			
				array[k] = aux[j++];	
			}
		}
		
		for(int index: array) {
			System.out.print(index + " ");
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args) {
		int[] array = {20, 18, 5, 17, 8, 33};

		System.out.print("Original Array: ");
		for(int i: array) {
			System.out.print(i + " ");
		}
		System.out.print("\n\n");
		
		mergeSort(array);
		
		System.out.print("\nFinal Array: ");
		for(int i: array) {
			System.out.print(i + " ");
		}	
	}
}

//System.out.print("array[k]: " + array[k] + " = aux[i]: " + aux[i] +"\n");
