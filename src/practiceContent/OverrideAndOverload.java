package practiceContent;

class AAA {
	public String show(DDD obj) {
		return ("A and D");
	}
	public String show(AAA obj) {
		return ("A and A");
	}
}

class BBB extends AAA {
	public String show(BBB obj) {
		return ("B and B");
	}
	public String show(AAA obj) {
		return ("B and A");
	}
}

class CCC extends BBB {}
class DDD extends BBB {}

public class OverrideAndOverload {


	
	public static void main(String[] args) {
		AAA a1 = new AAA();
	}

}

