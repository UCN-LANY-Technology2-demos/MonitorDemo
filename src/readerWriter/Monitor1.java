package readerWriter;

/*
 * If we consider access to a shared resource the objectives can be: 
 * - Readers can access the shared resource only when there are no writers. 
 * - Writers can access the shared resource only when there are no readers or writers. 
 * - Only one thread can manipulate the state variables at a time.
 */

public class Monitor1 implements Monitor{

	private int readersCount = 0;
	private int writersCount = 0;

	public synchronized void beginWrite() throws InterruptedException {

		// A writer can enter if there are no other active writers
		// and no readers are waiting

		while (writersCount == 1 || readersCount > 0) {
			wait();
		}

		writersCount = 1;
	}

	public synchronized void endWrite() {

		// check out

		writersCount = 0;
		notify();
	}

	public synchronized void beginRead() throws InterruptedException {

		// A reader can enter if there are no writers active or waiting,
		// so we can have many readers active all at once

		while (writersCount == 1) {
			wait();
		}

		++readersCount;
	}

	public synchronized void endRead() {

		// reader check out.

		--readersCount;
		notify();
	}

}
