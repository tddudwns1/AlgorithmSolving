import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        String success = "May Pass.";
        String failure = "TLE!";

        for (int tc = 1; tc <= c; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String timeComplexity = st.nextToken();
            long n = Long.parseLong(st.nextToken());
            long t = Long.parseLong(st.nextToken());
            long l = Long.parseLong(st.nextToken());

            long benchmark = l * 1_0000_0000 / t;

            if (timeComplexity.equals("O(N)")) {
                if (n <= benchmark)
                    sb.append(success);
                else
                    sb.append(failure);
            } else if (timeComplexity.equals("O(N^2)")) {
                if (n * n <= benchmark)
                    sb.append(success);
                else
                    sb.append(failure);
            } else if (timeComplexity.equals("O(N^3)")) {
                if (n * n * n <= benchmark)
                    sb.append(success);
                else
                    sb.append(failure);
            } else if (timeComplexity.equals("O(2^N)")) {
                if (pow(n, benchmark))
                    sb.append(success);
                else
                    sb.append(failure);
            } else if (timeComplexity.equals("O(N!)")) {
                if (factorial(n, benchmark))
                    sb.append(success);
                else
                    sb.append(failure);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static boolean pow(long n, long benchmark) {
        int number = 1;

        while (n-- > 0)
            if ((number *= 2) > benchmark)
                return false;

        return true;
    }

    private static boolean factorial(long n, long benchmark) {
        long number = 1;

        while (n > 0)
            if ((number *= n--) > benchmark)
                return false;

        return true;
    }
}