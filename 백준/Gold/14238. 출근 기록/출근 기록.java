import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static String answer = "-1";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<String> stack = new Stack<>();

        String s = br.readLine();

        int aCount = 0;
        int bCount = 0;
        int cCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                aCount++;
            } else if (s.charAt(i) == 'B') {
                bCount++;
            } else {
                cCount++;
            }
        }

        boolean[][][][][] checked = new boolean[aCount + 1][bCount + 1][cCount + 1][2][3];

        process(stack, aCount, bCount, cCount, 0, 0, checked);

        System.out.println(answer);
    }

    private static void process(Stack<String> stack, int aCount, int bCount, int cCount, int bLeft, int cLeft, boolean[][][][][] checked) {
        if (!answer.equals("-1") || checked[aCount][bCount][cCount][bLeft][cLeft]) {
            return;
        }
        checked[aCount][bCount][cCount][bLeft][cLeft] = true;

        if (aCount == 0 && bCount == 0 && cCount == 0) {
            StringBuilder sb = new StringBuilder();
            for (String now : stack) {
                sb.append(now);
            }
            answer = sb.toString();
            return;
        }

        if (cCount > 0 && cLeft == 0) {
            stack.add("C");
            process(stack, aCount, bCount, cCount - 1, 0, 2, checked);
            stack.pop();
        }
        if (cLeft > 0) {
            cLeft--;
        }

        if (bCount > 0 && bLeft == 0) {
            stack.add("B");
            process(stack, aCount, bCount - 1, cCount, 1, cLeft, checked);
            stack.pop();
        }

        if (aCount > 0) {
            stack.add("A");
            process(stack, aCount - 1, bCount, cCount, 0, cLeft, checked);
            stack.pop();
        }
    }
}