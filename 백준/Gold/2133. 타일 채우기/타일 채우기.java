import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(process(n));
    }

    private static int process(int n) {
        if (n % 2 == 1)
            return 0;

        int[] memo = new int[n + 1];
        memo[0] = 1;
        int sum = 0;
        for (int i = 2; i <= n; i += 2) {
            memo[i] = memo[i - 2] * 3 + sum * 2;
            sum += memo[i - 2];
        }

        return memo[n];
    }
}