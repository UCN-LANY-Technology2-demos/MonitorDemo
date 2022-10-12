package readerWriter;

public class Reader implements Runnable {

	private Monitor monitor;
	private Poem poem;
	private int expectedLineCount;

	public Reader(Monitor monitor, Poem message, int expectedLineCount) {

		this.poem = message;
		this.monitor = monitor;
		this.expectedLineCount = expectedLineCount;
	}

	@Override
	public void run() {

		try {

			int lineCount = 0;
			
			while (lineCount < expectedLineCount) {
				Thread.sleep((long) (Math.random() * 3001)); // simulates reading time

				monitor.beginRead(); // signals reading beginning
				lineCount = poem.getLineCount();
				System.out.println(
						Thread.currentThread().getName() + " read " + lineCount + " lines");
				monitor.endRead(); // signals reading ended
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

}
