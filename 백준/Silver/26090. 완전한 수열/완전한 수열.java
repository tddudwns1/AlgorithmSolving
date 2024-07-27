import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sequence = new int[n + 1];
        for (int i = 1; i <= n; i++)
            sequence[i] = Integer.parseInt(st.nextToken()) + sequence[i - 1];

        int limit = Math.max(sequence[n], n) + 1;
        boolean[] isNotDecimal = new boolean[limit];
        setNotDecimalArray(isNotDecimal, limit);

        int count = 0;
        int last1 = n - 1;
        for (int i = 0; i < last1; i++) {
            int last2 = n - i;
            for (int j = 2; j <= last2; j++) {
                if (isNotDecimal[j])
                    continue;

                int sum = sequence[i + j] - sequence[i];
                if (isNotDecimal[sum])
                    continue;

                count++;
            }
        }

        System.out.println(count);
    }

    private static void setNotDecimalArray(boolean[] isNotDecimal, int limit) {
        int sqrt = (int) Math.sqrt(limit);

        isNotDecimal[0] = true;
        isNotDecimal[1] = true;
        for (int i = 2; i <= sqrt; i++)
            if (!isNotDecimal[i])
                for (int j = i * 2; j < limit; j += i)
                    isNotDecimal[j] = true;
    }
}