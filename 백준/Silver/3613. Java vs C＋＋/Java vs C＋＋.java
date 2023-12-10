import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println(convertor(new BufferedReader(new InputStreamReader(System.in)).readLine()));
	}

	private static String convertor(String input) {
		StringBuilder sb = new StringBuilder();
		String type = "";
		int index = 0;
		char now = input.charAt(index);

		if (now < 97)
			return "Error!";
		sb.append(now);

		while (input.length() > ++index) {
			now = input.charAt(index);
			if (now >= 97) {
				sb.append(now);
				continue;
			}
			if (now == '_')
				type = "C++";
			else
				type = "Java";
			break;
		}
		
		index--;
		
		if (type.equals("C++")) {
			while (input.length() > ++index) {
				now = input.charAt(index);
				if (now >= 97) {
					sb.append(now);
					continue;
				}
				if (now != '_')
					return "Error!";

				if(input.length() <= ++index)
					return "Error!";
				
				if(input.charAt(index) < 97)
					return "Error!";

				sb.append((char) (input.charAt(index) - 32));
			}
		} else if (type.equals("Java")) {
			while (input.length() > ++index) {
				now = input.charAt(index);
				if (now >= 97) {
					sb.append(now);
					continue;
				}
				
				if (now == '_')
					return "Error!";

				sb.append('_');
				sb.append((char) (input.charAt(index) + 32));
			}
		}

		return sb.toString();
	}
}