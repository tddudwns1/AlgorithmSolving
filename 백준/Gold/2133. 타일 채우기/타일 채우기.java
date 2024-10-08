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

        int before = 1;
        int now = 0;
        int sum = 0;
        for (int i = 2; i <= n; i += 2) {
            now = before * 3 + sum * 2;
            sum += before;
            before = now;
        }

        return now;
    }
}