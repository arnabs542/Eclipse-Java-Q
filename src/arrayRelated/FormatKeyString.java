package arrayRelated;

public class FormatKeyString {
	public String formatKeyString(String S, int K) {
		StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0; i < S.length(); i++) {
	        if (S.charAt(i) != '-') {
	        		sb.append(S.charAt(i));
	        }
	    }
	    System.out.println(sb.toString());
	    
	    int pos = 0;
	    int offset = sb.length() % K;
	    if (offset != 0) {
	    		sb.insert(offset, '-');
	    		System.out.println(sb.toString());
	    		pos += offset + 1;
	    }
	    pos += K;
		while (pos < sb.length()) {
			sb.insert(pos++, '-');
			pos += K;
			System.out.println(sb.toString());
		}

	    System.out.println(sb.toString().toUpperCase());
	    return sb.toString().toUpperCase();
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		FormatKeyString testObj = new FormatKeyString();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		testObj.formatKeyString("A-Bcdef-gh", 3);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
