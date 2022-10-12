import circularBuffer.Buffer;
import circularBuffer.Consumer;
import circularBuffer.Producer;
import readerWriter.Monitor;
import readerWriter.Monitor1;
import readerWriter.Monitor2;
import readerWriter.Poem;
import readerWriter.Reader;
import readerWriter.Writer;

public class Program {
	
	public static void main(String[] args) {

		// Circular buffer demo
		//circularBufferDemo();
		
		// Reader/writer demo
		readerWriterDemo();
		
	} 
	
	private static void circularBufferDemo() {
		
		String[] shapes = { "Circle", "Triangle", "Rectangle", "Square", "Rhombus", "Trapezoid", "Pentagon", "Pentagram", "Hexagon", "Hexagram" };
		Buffer<String> buffer = new Buffer<String>(4);

		Thread producer = new Thread(new Producer<String>(buffer, shapes));
		Thread consumer = new Thread(new Consumer<String>(buffer, shapes.length));
		
		producer.start();
		consumer.start();
	}
	
	private static void readerWriterDemo() {
		
		String[] lines1 = {
				"Will Smith, bitch, I am feeling legendary", 
				"Take a pull and kick some knowledge", 
				"Roll me up that Mr. Marley", 
				"I'm feeling very Christopher Wallace", 
				"And they feel me down in Brooklyn", 
				"But Kanye gave me college", 
				"And that's before 23, damn straight, I'm so Michael", 
				"Chats with label presidents about my goals", 
				"The teachers said I'm nothing"};
		
		String[] lines2 = {
				"Abbey Road and Blueprint playing on vinyl", 
				"With the hippy flower princess, born in Ohio", 
				"Ask my father, getting money is hereditary", 
				"Take a pull and kick some knowledge", 
				"Cute English reporter tells me I'm steaming", 
				"Got my mother crying for the right reasons", 
				"Brews with Chris Zarou, we at the Four Seasons", 
				"He's kicking down the doors", 
				"This young boy is a problem, you should call it in"};
		
		Monitor rwMonitor = new Monitor2();
		Poem poem = new Poem();
		int lineCount =  lines1.length + lines2.length;

		Thread writer1 = new Thread(new Writer(rwMonitor, poem, lines1), "Writer1");
		Thread writer2 = new Thread(new Writer(rwMonitor, poem, lines2), "Writer2");
		Thread reader1 = new Thread(new Reader(rwMonitor, poem, lineCount), "Reader1");
		Thread reader2 = new Thread(new Reader(rwMonitor, poem, lineCount), "Reader2");
		
		writer1.start();
		writer2.start();		
		reader1.start();
		reader2.start();
	}
}
