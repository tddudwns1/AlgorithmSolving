import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int ans = np(arr);
		if (ans == -1)
			System.out.println("-1");
		else {
			for (int i = 0; i < n; i++)
				sb.append(arr[i]).append(" ");
			System.out.println(sb);
		}
	}

	private static int np(int[] arr) {
		int i, j, k;
		i = j = k = arr.length - 1;

		while (i > 0 && arr[i - 1] >= arr[i])
			i--;
		if (i == 0)
			return -1;

		while (arr[i - 1] >= arr[j])
			j--;

		swap(arr, i - 1, j);

		while (i < k)
			swap(arr, i++, k--);
		return 0;
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
