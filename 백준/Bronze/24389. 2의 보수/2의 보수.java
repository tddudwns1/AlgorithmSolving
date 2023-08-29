import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int a = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		int b = a;
		a = ~(a - 1);
		int c = a ^ b;
		int cnt = 0;
		for (int i = 0; i < 32; i++) {
			if ((c & 1) == 1)
				cnt++;
			c = c >> 1;
		}
		System.out.println(cnt);
	}
}
