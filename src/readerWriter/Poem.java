package readerWriter;

public class Poem {
	
	private String content = "";

	public void write(String line) {

		content += " " + line +"\n";
	}

	public String read() {

		return content;
	}
	
	public int getCharacterCount() {
		return content.length();
	}
	
	public int getLineCount() {
		return content.split("\n").length;
	}
}
