import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<Integer> cnt = new ArrayList<>();
		cnt.add(0);
		cnt.add(1);
		cnt.add(2);
		for(int i = 3; i <= n; i++) {
			cnt.add((cnt.get(i - 2) + cnt.get(i - 1))%10007);
		}
		System.out.println(cnt.get(n));
	}
}