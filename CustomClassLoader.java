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

		return bos.toByteArray();
	}

	public static void main(String[] argvs) throws Exception {
		CustomClassLoader ccl = new CustomClassLoader();

		Class<?> c = ccl.findClass("ReflectionNewInstance");

		System.out.println("ReflectionNewInstance obj : " + c);
	}

}
