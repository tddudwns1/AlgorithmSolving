import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());

		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		Stack<Integer> stack3 = new Stack<>();
		Stack<Integer> stack4 = new Stack<>();
		Stack<Integer> stack5 = new Stack<>();
		Stack<Integer> stack6 = new Stack<>();

		int count = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int line = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			switch(line) {
			case 1:
				if(!stack1.isEmpty()) {
					while (!stack1.isEmpty()) {
						if (stack1.peek() <= num)
							break;
						stack1.pop();
						count++;
					}
					if(!stack1.isEmpty() && stack1.peek() == num) break;
					stack1.add(num);
					count++;
				}
				else {
					stack1.add(num);
					count++;
				}
				break;
			case 2:
				if(!stack2.isEmpty()) {
					while (!stack2.isEmpty()) {
						if (stack2.peek() <= num)
							break;
						stack2.pop();
						count++;
					}
					if(!stack2.isEmpty() && stack2.peek() == num) break;
					stack2.add(num);
					count++;
				}
				else {
					stack2.add(num);
					count++;
				}
				break;
			case 3:
				if(!stack3.isEmpty()) {
					while (!stack3.isEmpty()) {
						if (stack3.peek() <= num)
							break;
						stack3.pop();
						count++;
					}
					if(!stack3.isEmpty() && stack3.peek() == num) break;
					stack3.add(num);
					count++;
				}
				else {
					stack3.add(num);
					count++;
				}
				break;
			case 4:
				if(!stack4.isEmpty()) {
					while (!stack4.isEmpty()) {
						if (stack4.peek() <= num)
							break;
						stack4.pop();
						count++;
					}
					if(!stack4.isEmpty() && stack4.peek() == num) break;
					stack4.add(num);
					count++;
				}
				else {
					stack4.add(num);
					count++;
				}
				break;
			case 5:
				if(!stack5.isEmpty()) {
					while (!stack5.isEmpty()) {
						if (stack5.peek() <= num)
							break;
						stack5.pop();
						count++;
					}
					if(!stack5.isEmpty() && stack5.peek() == num) break;
					stack5.add(num);
					count++;
				}
				else {
					stack5.add(num);
					count++;
				}
				break;
			case 6:
				if(!stack6.isEmpty()) {
					while (!stack6.isEmpty()) {
						if (stack6.peek() <= num)
							break;
						stack6.pop();
						count++;
					}
					if(!stack6.isEmpty() && stack6.peek() == num) break;
					stack6.add(num);
					count++;
				}
				else {
					stack6.add(num);
					count++;
				}
				break;
			}
			
		}
		System.out.println(count);
	}
}
