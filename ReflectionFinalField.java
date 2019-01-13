import java.lang.reflect.*;

import java.util.Arrays;
import java.util.List;

public class ReflectionFinalField { 

	public static void main(String[] argvs) {

		String s = new String("before changes");

		Class<?> cs = s.getClass();

		List<Field> flds = Arrays.asList(cs.getDeclaredFields());

		flds.forEach(f -> {
			try {
				// do not honor "private"
				f.setAccessible(true);

				Field mf = Field.class.getDeclaredField("modifiers");
				mf.setAccessible(true);
				// using Reflection Modifier Field to remove String object's final field value/coder/hash
				mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
				System.out.println(f + " _^:^_ " + f.get(s));

				// Now update the value, and check coder and hash
				if (f.getName().equals("value")) {
					System.out.println("####################### prior to changes; string --> " + s);
					// f.set(s, "*!after changes!*".getBytes());
					f.set(s, "*!after changes!*");
					System.out.println("####################### after changes; string --> " + s);
				}

				// re-honor "private"
				f.setAccessible(false);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// Change the string
		try {
			Field fv = cs.getDeclaredField("value");
			// not-honor "private"
			fv.setAccessible(true);
			Field mf = Field.class.getDeclaredField("modifiers");
			// also need to not-honor "private" for such "modifiers" field
			mf.setAccessible(true);

			// Remove "FINAL" modifier
			mf.setInt(fv, fv.getModifiers() & ~Modifier.FINAL);

			fv.set(s, "_After changes_".getBytes());

			// Restore "FINAL" modifier
			mf.setInt(fv, fv.getModifiers() | Modifier.FINAL);

			// restore "private" 
			fv.setAccessible(false);

			System.out.println("#### after changes,  now the string is : " + s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}


