
public class GenerateStackOverflowError {
	public int onePlusSum(int a, int b) {
		return onePlusSum(a, b) + 1;
	}

	public static void main(String[] args) {
		System.out.println(new GenerateStackOverflowError().onePlusSum(1, 2));
	}
}

