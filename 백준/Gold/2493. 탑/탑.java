import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] tops = new int[n][2];
		String[] topstr = br.readLine().split(" ");
		Stack<int[]> stack = new Stack<>();
		for (int i = 0; i < n; i++)
			tops[i] = new int[] { Integer.parseInt(topstr[i]), 0 };
		stack.add(tops[n - 1]);
		for (int i = n - 2; i >= 0; i--)
			if (stack.isEmpty() || tops[i][0] <= stack.peek()[0])
				stack.add(tops[i]);
			else {
				while (!stack.isEmpty() && stack.peek()[0] <= tops[i][0])
					stack.pop()[1] = i + 1;
				stack.add(tops[i]);
			}

		for (int i = 0; i < n; i++)
			System.out.print(tops[i][1] + " ");
	}
}
