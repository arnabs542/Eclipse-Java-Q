package design;

public class ImplementHashMap <K, V> {
	
	class Node<K, V> {
		private final K key;
		private V value;
		private Node next; // if the generic type has been defile int class Node<K, V>
					      // Node don't need <K, V> within the scope 
		public Node (K key, V value) {
			this.key = key;
			this.value = value;
		}

	}
	
	private Node<K, V>[] buckets;
	private int size;
	private static final int DEFAULTE_CAPACITY = 100; // magic number
	
	public ImplementHashMap(int size) {
		buckets = (Node<K, V>[]) new Node[DEFAULTE_CAPACITY];
		this.size = size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
//	public V get(K key) {
//		int bufIdx = index(key);
//		Node<K, V> cur = buckets[bufIdx];
//		
//		while (cur != null) {
//			
//		}
//		// ...
//	}
	
	private int index(K key) {
		return getHashCode(key) % this.size;
	}
	
	private boolean keysAreEqual(K key1, K key2) {
		if (key1 == null && key2 == null) {
			return true;
		} else if (key1 == null || key2 == null) {
			return false;
		} else {
			return key1.equals(key2);
		}
	}
	
//	public V put(K key, V value) {
//		// without rehashing
//		int bucketIdx = index(key);
//		if (buckets[bucketIdx] == null) {
//			buckets[bucketIdx] = new Node<K, V>(key, value);
//		} else {
//			Node<K, V> cur = buckets[bucketIdx];
//			while (cur.next != null) {
//				//if (keysAreEqual())
//			}
//		}
//		
//	}
	
	private int getHashCode(K key) {
		if (key == null) {
			return 0;
		}
		int res = key.hashCode();
		return res & 0x7FFFFFFF;
	}

}

/*
 * Node<K, V>[]array
 * 
 * 
 * hash(key) to hash#
 * hash# to entry index in the array
 * 
 * 
 */