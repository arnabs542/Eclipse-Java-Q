package practiceContent;

public class HandleException {
	
	static void fun() throws IllegalAccessException, NullPointerException {
		System.out.println("Inside fun() ");
		throw new IllegalAccessException("demo");
	}
	
	public static void main(String args[]) {
		
		//fun(); // compile error, unhandle exception
		
		try {
			fun();
		} catch (NullPointerException e) {
			e.printStackTrace();
//			throw e;
		} catch (Exception e) {
			e.printStackTrace();
//			throw e;
		} finally {
			
		}
	}
}
