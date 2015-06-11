package corenet.netbase;

import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;

public class BufferHelper {
	
	public static void flipToRead(ByteBuffer buffer) {
		if (buffer.position() > 0) {
			try {
				int position = buffer.position();
				buffer.reset();
				buffer.limit(position);
			} catch(InvalidMarkException e) {
				buffer.flip();
			} 
		}
	}
	
	public static void crunch(ByteBuffer buffer) {
		buffer.compact();
		int position = buffer.position();
		buffer.position(0);
		buffer.mark();
		buffer.position(position);
	}

	public static ByteBuffer realloc(ByteBuffer buffer, int size) {
		ByteBuffer newBuffer = buffer;
		if (size < buffer.capacity()) {
			if (buffer.limit() + (size - buffer.remaining()) <= buffer.capacity()) {
				buffer.mark();
				buffer.position(buffer.limit());
				buffer.limit(buffer.capacity());
			} else {
				crunch(buffer);
			}
		} else {
			newBuffer = ByteBuffer.allocate(size + buffer.remaining());
			if (buffer.hasRemaining()) {
				byte[] src = buffer.array();
				byte[] dst = newBuffer.array();
				System.arraycopy(src, buffer.position(), dst, 0, buffer.remaining());
			}
			newBuffer.position(buffer.remaining());
		}
		
		return newBuffer;
	}
}
