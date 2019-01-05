package practiceContent;

public class StringUtilityFunctions {
	
	
	public static void main (String[] args) {
		
		/* ------------------< string.split() >----------------------*/
		System.out.println("-------< string.split() >----------");
		
		// public String[] split(String regex, int limit)
		String log1 = "g1 aat car dog";		
		String[] split1 = log1.split(" ", 2);		
		for (String s : split1) {
			System.out.println(s);
		}
		
		/* ------------------< Character.isDigit() >--------------------*/
		System.out.println("-------< Character.isDigit() >------------");
		
		boolean isDigit1 = Character.isDigit(split1[0].charAt(0));
		System.out.println(isDigit1);
		
		/* ------------------< string.compareTo() >--------------------
		 * int compareTo(Object o)
		 * 
		 * int compareTo(String anotherString)
		 * - will compare two string in Lexicographical order
		 * 
		 */
		System.out.println("-------< string.compareTo() >------------");
		
		String log2 = "ab1 add key dog";
		System.out.println(log1.substring(log1.indexOf(' ') + 1));
		System.out.println(log2.substring(log2.indexOf(' ') + 1));
		
        int cmpRes = log1.substring(log1.indexOf(' ') + 1).compareTo(log2.substring(log2.indexOf(' ') + 1));
        System.out.println(cmpRes);
        
        String s11 = "i";
        String s12 = "love";
        System.out.println(s11.compareTo(s12));
        
        /* ------------------< string.trim() >----------------------
         * - remove leading and tailing spaces
         * */
        System.out.println("----------< string.trim() >-------------");
        
        String str1 = "   a b  ";
        System.out.println(str1.trim());
        
        /* ------------------< string.toUpperCase() >----------------------*/
        System.out.println("-------< string.toUpperCase() >------------");
        
		String Str = new String("A-Bcdef-ghI").toUpperCase();
		System.out.println(Str);
		
		/* ------------------< string.equals() >--------------------*/
		System.out.println("-------< string.equals() >------------");
		
		String string1 = "C";
		String string2 = new String("C");
		System.out.println(string1 == string2);
		System.out.println(string1.equals(string2)); // Always use string.equals() to compare strings!!!
	}
	
}
