import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] input = br.readLine().toCharArray();

        Stack<Character> operators = new Stack<>();

        for (char now : input) {
            if (now == '(') {
                operators.push(now);
                continue;
            }

            if (now == '+' || now == '-') {
                while (true) {
                    if (operators.isEmpty())
                        break;

                    if (operators.peek() == '(')
                        break;

                    sb.append(operators.pop());
                }

                operators.push(now);
                continue;
            }

            if (now == '*' || now == '/') {
                // 이전 stack들 비우면서 계산식 세우고
                // 괄호가 나오면 stop
                while (true) {
                    if (operators.isEmpty())
                        break;

                    if (operators.peek() == '+' || operators.peek() == '-' || operators.peek() == '(')
                        break;

                    sb.append(operators.pop());
                }

                // 그리고 stack에 누적
                operators.push(now);
                continue;
            }

            if (now == ')') {
                // ( 가 나올 때 까지 pop 해야할 듯
                // 근데 연산자 숫자 연산자 숫자 연산자 숫자 순으로
                while (true) {
                    if (operators.isEmpty())
                        break;

                    if (operators.peek() == '('){
                        operators.pop();
                        break;
                    }

                    sb.append(operators.pop());
                }
                continue;
            }

            sb.append(now);
        }

        while (true) {
            if (operators.isEmpty())
                break;

            sb.append(operators.pop());
        }

        System.out.println(sb);
    }
}