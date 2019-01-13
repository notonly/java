import java.lang.reflect.*;

public class ReflectionNewInstance {

	// Adding field and method for CustomClassLoader to load this class and initiate value
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String v) {
		value = v;
	}

	public static void main(String[] argvs) throws Exception  {

		Class<String> cz = (Class<String>) Class.forName("java.lang.String");

    String s = cz.newInstance();

		Field fv = s.getClass().getDeclaredField("value");
		fv.setAccessible(true);
		Field mf = Field.class.getDeclaredField("modifiers");
		mf.setAccessible(true);
		mf.setInt(fv, fv.getModifiers() & ~Modifier.FINAL);

		fv.set(s, "created from Class newInstance() and with final field value updates".getBytes()); 

		mf.setInt(fv, fv.getModifiers() | Modifier.FINAL);
		mf.setAccessible(false);

		fv.setAccessible(false);

    System.out.println("^^^^^^^^^^^ newInstance() created : " + s);

	}

}
