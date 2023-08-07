import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[][] tops = new int[n][2];
		String[] topstr = br.readLine().split(" ");
		Stack<int[]> stack = new Stack<>();
		sb.append("0 ");
		stack.add(new int[] { Integer.parseInt(topstr[0]), 1 });
		for (int i = 1; i < n; i++) {
			int h = Integer.parseInt(topstr[i]);
			// 이전 > h
			if (stack.peek()[0] > h) {
				sb.append(stack.peek()[1]).append(" ");
				stack.add(new int[] { h, i + 1 });
				continue;
			}
			if (stack.peek()[0] == h) {
				sb.append(stack.peek()[1]).append(" ");
				stack.add(new int[] { h, i + 1 });
				continue;
			}
			while (true) {
				if (stack.isEmpty()) {
					sb.append("0 ");
					stack.add(new int[] { h, i + 1 });
					break;
				}
				if (stack.peek()[0] < h) {
					stack.pop();
					continue;
				}
				if (stack.peek()[0] > h) {
					sb.append(stack.peek()[1]).append(" ");
					stack.add(new int[] { h, i + 1 });
					break;
				}
				if (stack.peek()[0] == h) {
					sb.append(stack.peek()[1]).append(" ");
					stack.add(new int[] { h, i + 1 });
					break;
				}
			}
		}
		System.out.println(sb);
	}
}
