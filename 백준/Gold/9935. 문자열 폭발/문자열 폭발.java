import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        int strLength = str.length;

        char[] cutting = br.readLine().toCharArray();
        int cuttingLength = cutting.length;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < strLength; i++) {
            stack.add(str[i]);
            int stackSize = stack.size();

            if (stackSize >= cuttingLength) {
                process(cuttingLength, stack, stackSize, cutting);
            }
        }

        System.out.println(getAnswer(stack));
    }

    private static String getAnswer(Stack<Character> stack) {
        if (stack.isEmpty())
            return "FRULA";

        StringBuilder sb = new StringBuilder();
        for (char now : stack)
            sb.append(now);
        return sb.toString();
    }

    private static void process(int cuttingLength, Stack<Character> stack, int stackSize, char[] cutting) {
        int standard = stackSize - cuttingLength;
        for (int i = 0; i < cuttingLength; i++)
            if (stack.get(standard + i) != cutting[i])
                return;

        for (int i = 0; i < cuttingLength; i++)
            stack.pop();
    }
}