package concurrencyPractice;

import java.util.Arrays;

public class ThreadSafeHashMap<K, V>  {
	/////////////////////////////////////////////////////////////////////////
	/* ----------------------< Nested Class Node >-------------------------*/
	/////////////////////////////////////////////////////////////////////////
	static class Node<K,V> {
		final K key;
		V value;
		Node<K,V> next;
		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return this.key;
		}
		
		// If the public method of MyHashMap are all implemented with synchronization, 
		// API in class Node doesn't need to use synchronization.
		// The classes who use Node should implement synchronization with principle : Data Race Free
		public V getValue() {
			return value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
	}
	
	///////////////////////////////////////////////////////////////////////
	/* ----------------------< Class MyHashMap >-------------------------*/
	///////////////////////////////////////////////////////////////////////
	
	public static final int DEFAULT_CAPACITY = 16;
	public static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private Node<K, V>[] array;
	private int size;
	private float loadFactor;
	
	/* --------------< Constructor >-------------------*/
	public ThreadSafeHashMap(int capacity, float loadFactor) {
		if (capacity <= 0) {
			
		}
		array = (Node<K, V>[])(new Node[capacity]);
		this.loadFactor = loadFactor;
		size = 0;
	}
	
	public ThreadSafeHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	/* --------------< Private Methods >-------------------*/
	
	private int hash(K key) {
		if (key == null) {
			return 0;
		}
		int code = key.hashCode();
		return code & 0x7fffffff;
	}
	
	private int getIndex(int hash) {
		return hash % array.length;
	}
	
	private boolean equalsKey(K k1, K k2) {
		return k1 == k2 || k1 != null && k1.equals(k2);
	}
	
	private boolean needRehashing() {
		float ratio = (size + 0.0f) / array.length;
		return ratio >= loadFactor;
	}
	
	private boolean rehashing() {
		//TODO: new double size array
		// for each node in the old array
		// do the put() operation to the new larger array
		return true;
	}
	
	private boolean equalsValue(V  v1, V v2) {
		return v1 == v2 || v1 != null && v1.equals(v2);
	}
	
	/* --------------< Public Methods >-------------------*/
	
	public synchronized int size() {
		return size;
	}
	
	public synchronized boolean isEmpty() {
		return size == 0;
	}
	
	public synchronized void clear() {
		Arrays.fill(array, null);
		size = 0;
	}

	public synchronized V put(K key, V value) {
		int index = getIndex(hash(key));
		Node<K, V> cur = array[index];
		while (cur != null) {
			if (equalsKey(key, cur.key)) {
				V result = cur.value;
				cur.value = value;
				return result;
			}
			cur = cur.next;
		}
		Node<K, V> newHead = new Node<>(key, value);
		newHead.next = array[index];
		array[index] = newHead;
		size++;
		if  (needRehashing()) {
			rehashing();
		}
		return null;
	}
	
	public synchronized V get(K key) {
		int index = getIndex(hash(key));
		Node<K, V> cur = array[index];
		while (cur != null) {
			if (equalsKey(key, cur.key)) {
				return cur.value;
			}
			cur = cur.next;
		}
		return null;
	}
	
	public synchronized boolean containsKey(K key) {
		int index = getIndex(hash(key));
		for (Node<K, V> node = array[index]; node != null; node = node.next) {
			if (equalsKey(key, node.key)) {
				return true;
			}
		}
		return false;
	}
	
	
	public synchronized boolean containsValue(V value) {
		if (isEmpty()) {
			return false;
		}
		for (Node<K, V> node : array) {
			while (node != null ) {
				if (equalsValue(value, node.value)) {
					return true;
				}
			}
		}
		return false;
	}
	
//	public synchronized V remove(K key) {
//		
//	}
}
