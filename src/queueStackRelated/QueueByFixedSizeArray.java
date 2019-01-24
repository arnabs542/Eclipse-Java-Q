package queueStackRelated;

public class QueueByFixedSizeArray {
	int head;
	int tail;
	int size;
	Integer[] array;
	
	public QueueByFixedSizeArray(int capacity) {
		array = new Integer[capacity];
		head = tail = 0;
		size = 0;
	}
	
	public boolean offer(Integer element) {
		if (size == array.length) {
			// the queue is full;
			return false;
		}
		array[tail] = element;
		tail = tail + 1 == array.length ? 0 : tail + 1;
		size++;
		return true;
	}
	
	public Integer peek() { 
		if (size == 0) {
			return null;
		}
		return array[head];
	}
	
	public Integer poll() {
		if (size == 0) {
			return null;
		}
		Integer result = array[head];
		head = head + 1 == array.length ? 0 : head + 1;
		size--;
		return result;
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		QueueByFixedSizeArray testObj = new QueueByFixedSizeArray(5);
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		System.out.println(testObj.offer(1));
		System.out.println(testObj.offer(2));
		System.out.println(testObj.offer(3));
		System.out.println(testObj.offer(4));
		System.out.println(testObj.offer(5));
		System.out.println(testObj.offer(6)); // expected false
		
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		// 5 4 3 2 1 -> head
		System.out.println(testObj.poll()); // expected 1
		System.out.println(testObj.poll()); // expected 2

		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		// 5 4 3 -> head
		System.out.println(testObj.offer(6));
		System.out.println(testObj.offer(7));
		
		// 7 6 5 4 3 -> head
		System.out.println(testObj.poll()); // expected 3
		System.out.println(testObj.poll()); // expected 4
		System.out.println(testObj.poll()); // expected 5
		System.out.println(testObj.poll()); // expected 6
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
		System.out.println(testObj.poll()); // expected 7
		System.out.println(testObj.poll()); // expected null
	}
}
