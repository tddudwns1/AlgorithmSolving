import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            long n = Long.parseLong(br.readLine());

            sb.append(process(n)).append("\n");
        }

        System.out.println(sb);
    }

    private static long process(long n) {
        if (n < 3) {
            return 2;
        }

        int sqrt = (int) Math.sqrt(n) + 1;
        long limit = (long) Math.pow(sqrt + 1, 2);
        long answer = n;
        for (; answer <= limit; answer++) {
            if(isPrime(sqrt, answer)) {
                break;
            }
        }
        return answer;
    }

    private static boolean isPrime(int sqrt, long i) {
        for (int j = 2; j <= sqrt; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
}