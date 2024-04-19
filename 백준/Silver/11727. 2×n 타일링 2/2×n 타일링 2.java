import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] memo = new long[n + 1];

        memo[0] = 1;
        memo[1] = 3;

        for (int i = 2; i < n; i++)
            memo[i] = (memo[i - 1] + memo[i - 2] * 2) % 1_0007;

        System.out.println(memo[n - 1]);
    }
}