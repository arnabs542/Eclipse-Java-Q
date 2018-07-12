/*
 * Created Date: June 13, 2018
 * 
 * Application: Implement a Min Heap
 * 
 */

package heapRelated;

public class ImplementHeap { 
	
	private Integer[] arr;
	private int size;
	
	ImplementHeap(int capacity) {
		size = 0;
		arr = new Integer[capacity];
	}
	
	ImplementHeap(Integer[] arr) {
		size = arr.length;
		this.arr = arr;
		heapify();
	}
	
	public void heapify() {
		for (int k = size / 2 - 1; k >= 0; k--) {
			sink(k);
		}
	}
	
	// Time Complexity: O(logn)
	public void offer(int val) {
		if (size >= arr.length) {
			Integer[] newArr = new Integer[size * 2];
			for (int i = 0; i < arr.length; i++) {
				newArr[i] = arr[i];
			}
			this.arr = newArr;
		} 
		arr[size] = val;
		swim(size);
		size++;	
	}

	// Time Complexity: O(1)
	public Integer peek() {
		if (arr == null || arr.length == 0) {
			return null;
		}
		return this.arr[0];
	}
	
	// Time Complexity: O(logn)
	public Integer poll() {
		int result = arr[0]; // Retrieve min key from top.
		swap(0, size - 1); // Exchange with last item.
		arr[size - 1] = null; 
		size--;
		sink(0);  // Restore heap property.		
		return result;
	}
	
	public Integer size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	// Bottom-up reheapify (swim)  
	private void swim(int k) {
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
	
	// Top-down reheapify (sink) 
	private void sink(int k) {
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
		return arr[a] < arr[b];
	}
	
	private void swap(int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	private void printHeap() {
		if(arr == null) {
			System.out.println("null");
		}
		for (int i = 0; i < size; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.print("\n");
	}
	
	/* ----------------------< test stub >-------------------------*/
	
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		ImplementHeap heap0 = new ImplementHeap(3);
		System.out.println("size: " + heap0.size());
		System.out.println("isEmpty(): " + heap0.isEmpty());
		
		/* Test Case 1 */
		System.out.println("\n ---< Test Case 1: Test offer(), size(), isEmpty() >---");
		Integer[] arr1 = {8, 7, 6, 5, 4, 3, 2, 1};
		ImplementHeap heap1 = new ImplementHeap(arr1);
	    heap1.printHeap(); // expected: 1 4 2 5 8 3 6 7
		
		System.out.println("isEmpty(): " + heap1.isEmpty());
		System.out.println("size: " + heap1.size());
		
		System.out.print("offer(1): ");
		heap1.offer(1);
		heap1.printHeap(); // expected: 1 1 2 4 8 3 6 7 5
		
		System.out.println("size: " + heap1.size());
		
		/* Test Case 2 */
		System.out.println("\n ---< Test Case 2: Test poll() >---");
		System.out.println("poll(): " + heap1.poll());
		heap1.printHeap(); // expected: 1 4 2 5 8 3 6 7 
		
		System.out.println("size: " + heap1.size());
		
		System.out.println("poll(): " + heap1.poll());
		System.out.println("poll(): " + heap1.poll());
		System.out.println("poll(): " + heap1.poll());
		System.out.println("poll(): " + heap1.poll());
		
		heap1.printHeap(); 
		System.out.println("size: " + heap1.size());
		
		/* Test Case 3 */
		System.out.println("\n ---< Test Case 3 >---");		
	}
	
}


