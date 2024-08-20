import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        StringBuilder answer = new StringBuilder();
        StringBuilder stack = new StringBuilder();
        process(0, n, m, stack, answer);
        
        System.out.print(answer);
    }
    
    private static void process(int depth, int n, int m, StringBuilder stack, StringBuilder answer) {
        if (depth == m) {
            answer.append(stack).append("\n");
            return;
        }
        
        for (int i = 1; i <= n; i++) {
            stack.append(i).append(" ");
            process(depth + 1, n, m, stack, answer);
            stack.setLength(stack.length() - 2);
        }
    }
}