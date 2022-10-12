import circularBuffer.Buffer;
import circularBuffer.Consumer;
import circularBuffer.Producer;

public class Program {
	
	private static final String[] shapes = { "Circle", "Triangle", "Rectangle", "Square", "Rhombus", "Trapezoid", "Pentagon", "Pentagram", "Hexagon", "Hexagram" };

	public static void main(String[] args) {

		// Circular buffer demo
		Buffer<String> buffer = new Buffer<String>(4);

		Thread producer = new Thread(new Producer<String>(buffer, shapes));
		Thread consumer = new Thread(new Consumer<String>(buffer, shapes.length));
		
		producer.start();
		consumer.start();
		
		// Reader/writer demo
		
	} 

}
