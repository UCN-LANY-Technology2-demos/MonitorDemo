package readerWriter;

public interface Monitor {

	void beginWrite() throws InterruptedException;
	void endWrite();
	void beginRead() throws InterruptedException;
	void endRead();
}
