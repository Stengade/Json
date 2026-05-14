package json.util;

import java.io.IOException;
import java.io.StringReader;

public class JsonReader {
	private StringReader reader;
	private int buffer = -2;
	
	public JsonReader(String str) {
		this.reader = new StringReader(str);
	}

	public void gc() {
		try {
			while(true) {
				int c = this.reader.read();
				if(c == -1) {
					buffer = c;
					return;
				}
			
				if(c == '\n' || c == '\r' || c == ' ' || c == '\t')continue;
				buffer = c;
				return;
			}
		}catch(IOException e) {
			this.buffer = -1;
		}
	}

	public int read() {
		if(this.buffer != -2) {
			int buf = this.buffer;
			this.buffer = -2;
			return buf;
		}
		
		try {
			return this.reader.read();
		} catch (IOException e) {
			return -1;
		}
	}
}
