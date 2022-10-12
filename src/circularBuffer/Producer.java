package circularBuffer;

public class Producer<T> implements Runnable {

	private Buffer<T> buffer;
	private T[] items;

	public Producer(Buffer<T> buffer, T[] items) {

		this.buffer = buffer;
		this.items = items;
	}

	@Override
	public void run() {

		try {
			
			for (int i = 0; i < items.length;) {
				if (buffer.offer(items[i])) {
					i++;
					Thread.sleep((int) (Math.random() * 3001));
				}
			}

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
