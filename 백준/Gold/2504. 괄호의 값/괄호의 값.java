import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String brackets = br.readLine();
        Deque<Character> dq = new ArrayDeque<>();

        int result = 0;
        int value = 1;

        for (int i = 0; i < brackets.length(); i++) {
            if (brackets.charAt(i) == '(') {
                dq.addLast(brackets.charAt(i));
                value *= 2;
            } else if (brackets.charAt(i) == '[') {
                dq.addLast(brackets.charAt(i));
                value *= 3;
            } else if (brackets.charAt(i) == ')') {
                if (dq.isEmpty() || dq.peekLast() != '(') {
                    result = 0;
                    break;
                } else if (i > 0 && brackets.charAt(i - 1) == '(')
                    result += value;
                dq.removeLast();
                value /= 2;
            } else if (brackets.charAt(i) == ']') {
                if (dq.isEmpty() || dq.peekLast() != '[') {
                    result = 0;
                    break;
                } else if (i > 0 && brackets.charAt(i - 1) == '[')
                    result += value;
                dq.removeLast();
                value /= 3;
            }
        }

        if (dq.isEmpty())
            System.out.println(result);
        else
            System.out.println(0);
    }
}
