/*
 * Created Date: July 8, 2018
 * 
 * Application: Practice class 17, inheritance, override & overload 
 * 
 */

package practiceContent;

class A {
	private String name = "A";
	
	public String getName() {
		return this.name;
	}
	
	public void setName(A other) {
		this.name = other.name; // though name is a private field
	}
	
	public void show(D d) {
		System.out.println("A and D");
	}
	
	public void show(A a) {
		System.out.println("A and A");
	}
}

class B extends A {
	public void show(B obj) {
		System.out.println("B and B");
	}
	
	public void show(A a) {
		System.out.println("B and A");
	}
}

class C extends B {
	
}

class D extends B {
	
}

public class P17_Override_Overload {
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		A a1 = new A();
		A a2 = new B();
		
		B b = new B();
		C c = new C();
		D d = new D();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		a1.show(b); // expected: "A and A"
		a2.show(b); // the trickiest one, think again! expected: "B and A"
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		System.out.println(a1.getName());
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}

