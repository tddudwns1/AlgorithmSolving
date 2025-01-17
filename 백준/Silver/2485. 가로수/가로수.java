import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] trees = new int[n];
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(process(n - 1, trees));
    }

    private static int process(int limit, int[] trees) {
        int[] diffs = new int[limit];
        for (int i = 0; i < limit; i++) {
            diffs[i] = trees[i + 1] - trees[i];
        }

        int gcd = gcd(diffs[0], diffs[1]);
        int answer = gcd;
        for (int i = 1; i < limit; i++) {
            answer = gcd(gcd, diffs[i]);
            gcd = answer;
        }

        return ((trees[limit] - trees[0]) / answer) - limit;
    }

    private static int gcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (b != 0) {
            int div = a % b;
            a = b;
            b = div;
        }

        return a;
    }
}