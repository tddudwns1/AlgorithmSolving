import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;

	private static boolean isprime(int num) {
		for (int i = 2; i <= Math.sqrt(num); i++)
			if (num % i == 0)
				return false;
		if (num == 1)
			return false;
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		searchTarget(0, 0);
	}

	private static void searchTarget(int dep, int num) {
		if (dep == n) {
			System.out.println(num);
			return;
		}
		for (int i = 1; i <= 9; i++) {
			if (isprime(num * 10 + i))
				searchTarget(dep + 1, num * 10 + i);
		}
	}
}
