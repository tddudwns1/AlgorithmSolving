import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		getArr(n, a, br);

		int m = Integer.parseInt(br.readLine());
		int[] b = new int[m];
		getArr(m, b, br);

		List<Integer> sumA = new ArrayList<>(), sumB = new ArrayList<>();
		prifixSum(sumA, a, n);
		prifixSum(sumB, b, m);

		Collections.sort(sumA);
		Collections.sort(sumB, Comparator.reverseOrder());

		int right = sumA.size() - 1;
		int left = sumB.size() - 1;
		long ans = 0;
		while (true) {
			int sum = sumA.get(right) + sumB.get(left);

			if (sum > T)
				right--;
			else if (sum < T)
				left--;
			else {
				int nowA = sumA.get(right);
				long ansA = 0;
				do
					ansA++;
				while (--right >= 0 && sumA.get(right) == nowA);

				int nowB = sumB.get(left);
				long ansB = 0;
				do
					ansB++;
				while (--left >= 0 && sumB.get(left) == nowB);
				ans += ansA * ansB;
			}
			if (right < 0 || left < 0)
				break;
		}

		System.out.println(ans);
	}

	private static void getArr(int n, int[] arr, BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
	}

	private static void prifixSum(List<Integer> sumList, int[] arr, int n) {
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++)
				sumList.add(sum += arr[j]);
		}
	}
}