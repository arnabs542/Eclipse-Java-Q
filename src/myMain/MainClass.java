package myMain;


public class MainClass {
	
	public static void main(String[] args) {
		
		int[] array = new int[10];
		int lenOfArr = array.length;
		
		String string = "string";
		int lenOfStr = string.length();
		
		String[] strArr = new String[10];
		int lenOfStrArr = strArr.length;
		
		char[] charArr = new char[10];
		int lenOfCharArr = charArr.length;
		
		String s = "aabbbc";
		char[] charA = s.toCharArray();
		System.out.print(charA[0] == charA[1]);
	}	    	 
}