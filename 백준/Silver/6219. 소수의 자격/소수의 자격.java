import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] primeNumber;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        setPrimeNumber(b);

        int answer = 0;

        for (int i = a; i <= b; i++)
            if (!primeNumber[i])
                if (isIncludeD(i, d))
                    answer++;

        System.out.println(answer);
    }

    private static void setPrimeNumber(int b) {
        primeNumber = new boolean[b + 1];

        primeNumber[1] = true;

        for (int i = 2; i <= b; i++)
            if (!primeNumber[i])
                for (int j = i * 2; j <= b; j += i)
                    primeNumber[j] = true;
    }

    private static boolean isIncludeD(int target, int d) {
        while (true) {
            if (target == 0)
                return false;

            if (target % 10 == d)
                return true;

            target /= 10;
        }
    }
}