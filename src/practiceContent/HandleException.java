package practiceContent;

public class HandleException {
	
	static void fun(String str) throws IllegalAccessException, NullPointerException {
		if (str == null) {
			throw new NullPointerException();
		}
		System.out.println("Inside fun() ");
//		throw new IllegalAccessException("demo");
	}
	
	public static void main(String args[]) {
		
		//fun(); // compile error, unhandle exception
		
		String str = null;
		try {
			fun(str);
		} catch (NullPointerException e) {
			e.printStackTrace();
//			throw e;
		} catch (Exception e) {
			e.printStackTrace();
//			throw e;
		} finally {
			System.out.println("\nfinally");
		}
	}
}
