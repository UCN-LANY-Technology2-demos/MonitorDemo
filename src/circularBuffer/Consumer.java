package circularBuffer;

public class Consumer<T> implements Runnable {

	private Buffer<T> buffer;
	private int expectedCount;

	public Consumer(Buffer<T> buffer, int expectedCount) {
		this.buffer = buffer;
		this.expectedCount = expectedCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {

		try {
			T[] items = (T[]) new Object[expectedCount];
			for (int i = 0; i < items.length;) {
				T item = buffer.poll();
				if (item != null) {
					items[i++] = item;
					Thread.sleep((int) (Math.random() * 3001));
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
