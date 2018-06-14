package heapRelated;

public class ImplementHeap {
	
	private int[] arr;
	private int size;
	
	// Min Heap
	ImplementHeap(int capacity) {
		size = 0;
		arr = new int[capacity];
	}
	
	ImplementHeap(int[] arr) {
		size = arr.length;
		this.arr = arr;
	}
	
	// Time Complexity: O(logn)
	public void offer(int val) {
		
	}

//	// Time Complexity: O(1)
//	public Integer peek() {
//		
//	}
//	
//	// Time Complexity: O(logn)
//	public Integer poll() {
//		
//	}
	
	public Integer size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void swim(int k) {
		if (k > this.size) { // corner case
			return;
		}
		int parent = (k - 1) / 2;
		while (k > 0 && less(k, parent)) {
			swap(k, parent);
			k = parent;
			parent = (k - 1) / 2;
		}
	}
	
	public void sink(int k) {
		if (k >= this.size) { // corner case
			return;
		}		
		while ((2 * k + 1) < this.size) {
			int succ = 2 * k + 1;
			if (succ + 1 < this.size && less(succ + 1, succ)) {
				succ++;
			}
			if (less(succ, k)) {
				swap(k, succ);
				k = succ;
			} else {
				break;
			}
		}
	}
	
	private boolean less(int a, int b) {
		if (arr[a] < arr[b]) {
			return true;
		} else {
			return false;
		}
	}
	private void swap(int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	/* ----------------------< test stub >-------------------------*/
	
	private static void printArr(int[] arr) {
		if(arr == null) {
			System.out.println("null");
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("\n");
	}
	
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[] arr1 = {5, 4, 3, 2, 1};
		printArr(arr1);
		
		ImplementHeap heap1 = new ImplementHeap(arr1);
		heap1.sink(0);
		printArr(heap1.arr);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		int[] arr2 = {8, 7, 6, 5, 4, 3, 2, 1};
		printArr(arr2);
		
		ImplementHeap heap2 = new ImplementHeap(arr2);
		heap2.swim(7);
		printArr(heap2.arr); // expected: 1 8 6 7 4 3 2 5
		
		heap2.swim(7);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
	
}


