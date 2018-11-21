package myMain;


public class MainClass {
	
	public static void main(String[] args) {
				
		
		String s1 = "g1 act car";
		String s2 = "a8 act zoo";
				
		System.out.println(s1.substring(s1.indexOf(' ') + 1)); 
		int result = s1.substring(s1.indexOf(' ') + 1).compareTo(s2.substring(s2.indexOf(' ') + 1));
		System.out.println(result);

	}	    	 	
}

//int lenOfString = string.length();
//int lenOfIntArray = array.length;
//int lenOfStrArray = strArr.length;
//int lenOfCharArray = charArr.length;