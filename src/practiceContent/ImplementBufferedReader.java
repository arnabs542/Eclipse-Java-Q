package practiceContent;

import java.io.FileInputStream;
import java.io.IOException;

public class ImplementBufferedReader {
	
	private FileInputStream in;
	private StringBuffer buffer;
	
	public ImplementBufferedReader(FileInputStream in) {
		this.in = in;
		buffer = new StringBuffer();
	}
	
	public String nextLine() throws IOException {
		while (true) {
			int c = in.read();
			if (c == -1 || c == '\n') {
				break;
			}
			buffer.append((char)c);
		}
		String output = buffer.toString();
		buffer = new StringBuffer();
		return output;
	}
}
