package readerWriter;

public class Writer implements Runnable {

	private Monitor monitor;
	private Poem message;
	private String[] lines;

	public Writer(Monitor monitor, Poem message, String[] lines) {

		this.monitor = monitor;
		this.message = message;
		this.lines = lines;
	}

	@Override
	public void run() {

		try {

			for (int i = 0; i < lines.length; i++) {
				Thread.sleep((long) (Math.random() * 3001));
			
				monitor.beginWrite();
				String line = lines[i];
				message.write(line);
				System.out.println(Thread.currentThread().getName() + " wrote: " + line);
				monitor.endWrite();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
