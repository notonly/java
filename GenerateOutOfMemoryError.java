import java.util.Map;
import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * It took a little while to allocate the MAX allowed memory for JVM on my local machine
 * around 2.3 GB,  and the java.lang.OutOfMemoeryError was raised.
 */
public class GenerateOutOfMemoryError {

	public static void main(String[] args) {
		Map<Integer, Integer> mem = new HashMap<>();
		IntStream.range(1, Integer.MAX_VALUE).forEach(i -> mem.put(i, i));
	}
}

