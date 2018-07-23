package myMain;


public class MainClass {
	
	public static void main(String[] args) {
				
		System.out.println(-7 / 2); // expected: -3
		
		System.out.println(-7 >> 1); // expected: -4. right shift one bit. 1001 (-7) --> 1100 (-4)
		
		String s1 = "bell";
		String s2 = "bell";
		System.out.println(s1 == s2); // expected: true -  Pooling for String object
		
		String s3 = new String("bell");
		System.out.println(s1 == s3); // expected: false - String objects created by "new" will not use the pool
		
		// int --> String
		int i = 1;
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
	}	    	 	
}

//int lenOfString = string.length();
//int lenOfIntArray = array.length;
//int lenOfStrArray = strArr.length;
//int lenOfCharArray = charArr.length;