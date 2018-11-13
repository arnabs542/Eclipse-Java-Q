package practiceContent;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileReadWrite {
	
	/* ----------------------< test stub >-------------------------*/
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
	
		
		/* Test Case 0 */
		System.out.println("---< Character streams >---");
		
		FileInputStream in = null;
		FileOutputStream out = null;
		
		try {
			in = new FileInputStream("././README.md");
			out = new FileOutputStream("././output.txt");
			int c;
			while ((c = in.read()) != -1) {
				System.out.print((char)c);
				out.write(c);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
//		BufferedReader br = new BufferedReader();
		
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
