import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		List<List<Integer>> fibonacchi = new ArrayList<>();
		fibonacchi.add(Arrays.asList(1, 0));
		fibonacchi.add(Arrays.asList(0, 1));
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			if (fibonacchi.size() <= n) {
				for (int i = fibonacchi.size() - 1; i < n; i++) {
					int calc0 = fibonacchi.get(i).get(0) + fibonacchi.get(i - 1).get(0);
					int calc1 = fibonacchi.get(i).get(1) + fibonacchi.get(i - 1).get(1);
					fibonacchi.add(Arrays.asList(calc0, calc1));
				}
			}
			sb.append(fibonacchi.get(n).get(0)).append(" ").append(fibonacchi.get(n).get(1)).append("\n");
		}
		System.out.println(sb);
	}
}