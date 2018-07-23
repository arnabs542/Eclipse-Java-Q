package practiceContent;

public class PrintBlocks {
	
	public void printIfBlocks(int n) {
	    if (n == 0) {
	        return;
	    }
	    for (int i = 1; i <= n; i++) {
	        for (int j = 1; j <= i; j++) {
	            print(j);
	            System.out.print("\n");
	            print(i - j);
	        }
	    }
	}

	private void print (int n) {
	    for (int i = 1; i <= n; i++) {
	        for (int j = 1; j < i; j++) {
	            System.out.print("	");
	        }
	        System.out.println("if {");
	    }
	    
	    for (int i = n; i >= 1; i--) {
	        for (int j = i; j > 1; j--) {
	            System.out.print("	");
	        }
	        System.out.println("}");
	    }
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		PrintBlocks testObj = new PrintBlocks();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		testObj.printIfBlocks(2);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}

