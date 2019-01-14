import java.io.Serializable;


/**
 * Using JDK tool serialver to check the serialVersionUID for 
 * classes implementing Serializable interface
 */
public class MySerial implements Serializable {
	public String name;
	public int age;

	static final long serialVersionUID = 1L;
}
