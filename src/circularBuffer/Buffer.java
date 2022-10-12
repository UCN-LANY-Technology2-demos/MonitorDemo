package circularBuffer;

/*
 * Ring Buffer (or Circular Buffer) is a bounded circular data structure that is used for buffering 
 * data between two or more threads. As we keep writing to a ring buffer, it wraps around as it 
 * reaches the end.
 * 
 * It is implemented using a fixed-size array that wraps around at the boundaries.
 * Apart from the array, it keeps track of three things:
 * - the next available slot in the buffer to insert an element,
 * - the next unread element in the buffer,
 * - and the end of the array – the point at which the buffer wraps around to the start of the array
 */

public class Buffer<E> {

	private int capacity;
	private E[] data;
	private int readIndex;
	private int writeIndex;
	
	@SuppressWarnings("unchecked")
	public Buffer(int capacity) {
		super();
		
		this.capacity = capacity;
		this.data = (E[])new Object[this.capacity];
		this.readIndex = 0;
		this.writeIndex = -1;
	}
	
	// if there is room inside the buffer, the element is added, otherwise the method returns false
    public synchronized boolean offer(E element) {

        if (isNotFull()) {
            int nextWriteSeq = writeIndex + 1;
        	int index = nextWriteSeq % capacity;
        	
            data[index] = element;
            writeIndex++;
            
            System.out.println(element +" offered to position "+ index);
            
            return true;
        }

        return false;
    }

    // if there is a value available it is returned, otherwise the method return null;
    public synchronized E poll() {

        if (isNotEmpty()) {

        	int index = readIndex % capacity;
            E nextValue = data[index];
            readIndex++;
            
            System.out.println(nextValue +" polled from position "+ index);
            
            return nextValue;
        }
        return null;
    }

    public int getSize() {

        return (writeIndex - readIndex) + 1;
    }

    public boolean isEmpty() {

        return writeIndex < readIndex;
    }

    public boolean isFull() {

        return getSize() >= capacity;
    }
    
    private boolean isNotEmpty() {

        return !isEmpty();
    }

    private boolean isNotFull() {

        return !isFull();
    }
}
