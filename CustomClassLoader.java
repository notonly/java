import java.io.*;

public class CustomClassLoader extends ClassLoader {

	@Override 
	public Class findClass(String name) throws ClassNotFoundException {
		byte[] bts = loadClassFromFile(name);

		return defineClass(name, bts, 0, bts.length);

	}


	/**
	 * using JVM default classloader to open resouce-stream
	 * writes byte-array via ByteArrayStream
	 * @param fnm File name
	 * @return byte array
	 */
	private byte[] loadClassFromFile(String fnm) {
		InputStream is = getClass().getClassLoader()
												.getResourceAsStream(fnm.replace('.', File.separatorChar) + ".class");

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		int v = 0;

		try {
			while ((v = is.read()) != -1) {
				bos.write(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (is != null) {
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bos.toByteArray();
	}

	public static void main(String[] argvs) throws Exception {
		CustomClassLoader ccl = new CustomClassLoader();

		Class<ReflectionNewInstance> clazz = (Class<ReflectionNewInstance>) ccl.findClass("ReflectionNewInstance");

		System.out.println("\nReflectionNewInstance obj : " + clazz);

		System.out.println("\nclazz.newInstance() = " + clazz.newInstance());

		Object rni = clazz.newInstance();


		// Notice invoking on both setValue and getValue methods;  and setValue has one parameter
		// https://analyzejava.wordpress.com/2014/09/25/java-classloader-write-your-own-classloader/
		// and 
		// http://tutorials.jenkov.com/java-reflection/methods.html
		System.out.println("\nTo invoke CustomClassLoader's getMethod and then invoke on the method\n");
		clazz.getMethod("setValue", String.class)
			   .invoke(rni, "^^^ ****   invoking  from Dynamically created instance");

		System.out.println("\nafter customClassLoader, and newInstance called on that class "
				+ "we got instance and value : " 
				+ clazz.getMethod("getValue").invoke(rni) + "\n\n");
	}

}
