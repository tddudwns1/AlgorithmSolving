import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int a = 0;
		int b = 0;
		int min = Integer.MAX_VALUE;
		int[] solution = new int[n];
		String[] str = br.readLine().split(" ");
		for (int i = 0; i < n; i++)
			solution[i] = Integer.parseInt(str[i]);
		Arrays.sort(solution);
		int left = 0;
		int right = n - 1;
		while (left < right) {
			int target = solution[left] + solution[right];
			int targetAbs = Math.abs(target);
			if(targetAbs < min) {
				a = left;
				b = right;
				min = targetAbs;
			}
			if(target < 0)
				left++;
			else if(target > 0)
				right--;
			else
				break;
		}
		System.out.println(solution[a] + " " + solution[b]);
	}
}
