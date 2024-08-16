import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		while (!str.equals(".")) {
			LinkedList<Character> stack = new LinkedList<>();
			String ans = "yes";
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '(')
					stack.add(str.charAt(i));
				else if (str.charAt(i) == '[')
					stack.add(str.charAt(i));
				else if (str.charAt(i) == ']') {
					if (stack.isEmpty() || stack.removeLast() != '[') {
						ans = "no";
						break;
					}
				} else if (str.charAt(i) == ')') {
					if (stack.isEmpty() || stack.removeLast() != '(') {
						ans = "no";
						break;
					}
				}
			}
			if (!stack.isEmpty()) 
				System.out.println("no");
			else
				System.out.println(ans);
			str = br.readLine();
		}
	}
}
