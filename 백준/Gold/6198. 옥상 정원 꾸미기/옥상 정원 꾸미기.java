import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] stack = new int[n];
        long count = 0;
        int top = 0;

        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(br.readLine());
            
            while (top > 0 && stack[top - 1] <= now)
                top--;
            
            count += top;
            stack[top++] = now;
        }

        System.out.println(count);
		br.close();
    }
}