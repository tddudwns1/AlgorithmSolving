import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static long a;
	static long b;
	static long c;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		a = Integer.parseInt(str[0]);
		b = Integer.parseInt(str[1]);
		c = Integer.parseInt(str[2]);
		a %= c;

		System.out.println(pow(b) % c);
	}

	private static long pow(long times) {
		if(times == 1)
			return a;
		long tmp = pow(times/2) % c;
		if(times%2 == 1)
			return tmp * tmp % c * a % c;
		return tmp * tmp % c;
	}
}
