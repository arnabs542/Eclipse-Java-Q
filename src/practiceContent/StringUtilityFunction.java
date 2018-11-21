package practiceContent;

public class StringUtilityFunction {
	
	
	public static void main (String[] args) {
		
		/* ------------------< string.split() >----------------------*/
		// public String[] split(String regex, int limit)
		String log1 = "g1 adt car dog";		
		String[] split1 = log1.split(" ", 2);		
		for (String s : split1) {
			System.out.println(s);
		}
		
		/* ------------------< Character.isDigit() >--------------------*/
		boolean isDigit1 = Character.isDigit(split1[0].charAt(0));
		System.out.println(isDigit1);
		
		/* ------------------< string.compareTo() >--------------------
		 * int compareTo(Object o)
		 * 
		 * int compareTo(String anotherString)
		 * - will compare two string in Lexicographical order
		 * 
		 */
		String log2 = "ab1 add key dog";
        int cmpRes = log1.substring(log1.indexOf(' ') + 1).compareTo(log2.substring(log2.indexOf(' ') + 1));
        //System.out.println(cmpRes);
	}
	
}
