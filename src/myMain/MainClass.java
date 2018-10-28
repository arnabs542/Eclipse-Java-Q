package myMain;


public class MainClass {
	
	public static void main(String[] args) {
				
		
		System.out.println("---< Ingeger to String >---");
		int i = 111;
		String si1 = String.valueOf(i);
		String si2 = Integer.toString(i);
		System.out.println(si1);
		System.out.println(si2);
		
		String str = "abb";
		StringBuilder sb = new StringBuilder(str);
		System.out.println(sb.toString());
		
		String rev = sb.reverse().toString();
		System.out.println(rev);
		
		System.out.println(str.equals(rev));
		

		
//		if (test > 'a' && test < 'z' || test > 'A' && test < 'Z' || test > '0' && test < '9') {
//			System.out.println("true");
//		} else {
//			System.out.println("false");
//		}
		

	}	    	 	
}

//int lenOfString = string.length();
//int lenOfIntArray = array.length;
//int lenOfStrArray = strArr.length;
//int lenOfCharArray = charArr.length;