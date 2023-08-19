import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] solution = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			solution[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(solution);

		int c = 0;
		int a = 0;
		int b = 0;
		int min = Integer.MAX_VALUE;
		int standard = -1;

		if (solution[0] >= 0) {
			c = 0;
			a = 1;
			b = 2;
		} else if (solution[n - 1] <= 0) {
			c = n - 3;
			a = n - 2;
			b = n - 1;
		} else
			portal : while (standard++ < n - 2) {
				int left = standard + 1;
				int right = n - 1;
				while (left < right) {
					int target = solution[standard] + solution[left] + solution[right];
					int targetAbs = Math.abs(target);
					if (targetAbs < min) {
						c = standard;
						a = left;
						b = right;
						min = targetAbs;
					}
					if (target < 0)
						left++;
					else if (target > 0)
						right--;
					else
						break portal;
				}
			}
		System.out.println(solution[c] + " " + solution[a] + " " + solution[b]);
	}
}
