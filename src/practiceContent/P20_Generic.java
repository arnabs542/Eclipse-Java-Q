package practiceContent;

import java.util.ArrayList;
import java.util.List;

class MyPair<K, V> {
	private K key;
	private V value;
	
	public MyPair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "MyPair [key=" + key + ", value=" + value + "]";
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public void setVal(V value) {
		this.value = value;
	}
	
	public K getKey() {
		return this.key;
	}
	
	public V getVal() {
		return this.value;
	}
}

class Util {
	public static <K, V> boolean myEqual (MyPair<K, V> p1, MyPair<K, V> p2) {
		return p1.getKey().equals(p2.getKey()) && p1.getVal().equals(p2.getVal());
	}
}
// ------------------------------------------------------------------------------

class Employee {}

class Engineer extends Employee {}

class Manager extends Employee {}

class Test2 {
	// ? super (Lower Bounded Wildcard) - used to set and write
	
	/* Try to add one manager into a list of emplyee
	 *                              List<Object>
	 * List<? super Manager>   =>   List<Emplyee> 
	 *                              List<Manager>
	 */
	public void addManager(List<? super Manager> list, Manager m) {
		
	}
	
	// ? extends (Upper Bounded Wildcard) - used to read

	Employee getFirstEmployee(List<? extends Employee> employees ) {
		return employees.get(0);
	}
}

public class P20_Generic {
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* ------ Verify how Java implements Generic (Type Erasure) ------------ */
		System.out.println("---< Test Case 0 >---");
		
		ArrayList<Integer> arrInt = new ArrayList<>();
		ArrayList<String> arrStr = new ArrayList<>();
		Class c1 = arrInt.getClass();
		Class c2 = arrStr.getClass();
		
		System.out.println(c1 == c2); // expected: true, generic type erasure
		System.out.println(c1); // expected: java.util.ArrayList
		
		try {
			arrInt.getClass().getMethod("add", Object.class).invoke(arrInt, "A string");
			System.out.println(arrInt.get(0)); // expected: "A string", we insert a string into a Integer List
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		
		MyPair<String, Integer> p1 = new MyPair<>("a", 1);
		MyPair<String, Integer> p2 = new MyPair<>("b", 2);
		
		System.out.println(Util.myEqual(p1, p2));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
	
}
