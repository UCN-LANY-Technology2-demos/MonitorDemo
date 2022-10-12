package readerWriter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor2 implements Monitor {
	private int readersCount = 0;
	private int writersCount = 0;

	private int waitingReaders = 0;
	private int waitingWriters = 0;

	private Lock lock = new ReentrantLock();
	private Condition canRead = lock.newCondition();
	private Condition canWrite = lock.newCondition();

	// wait until there are no active readers or writers
	public void beginWrite() throws InterruptedException {
		lock.lock();
		// A writer can enter if there are no other active writers and no readers are
		// waiting
		if (writersCount == 1 || readersCount > 0) {
			++waitingWriters;
			canWrite.await();
			--waitingWriters;
		}

		writersCount = 1;
		lock.unlock();
	}

	// check out and wake a waiting writer or readers
	public synchronized void endWrite() {
		lock.lock();
		writersCount = 0;

		// Checks to see if any readers are waiting
		if (waitingReaders > 0) {
			canRead.signal();
		} else {
			canWrite.signal();
		}
		lock.unlock();
	}

	// wait until there are no writers
	public synchronized void beginRead() throws InterruptedException {
		lock.lock();
		// A reader can enter if there are no writers active or waiting,
		// so we can have many readers active all at once
		if (writersCount == 1 || waitingWriters > 1) {
			++waitingReaders;
			canRead.await();
			--waitingReaders;
		}

		++readersCount;
		canRead.signal();
		lock.unlock();
	}

	// check out and wake a waiting writer
	public synchronized void endRead() {
		lock.lock();
		// When a reader finishes, if it was the last reader, it lets a writer in (if
		// any is there).
		if (--readersCount == 0) {
			canWrite.signal();
		}
		lock.unlock();
	}
}
