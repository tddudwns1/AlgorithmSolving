import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        System.out.println(process(n, k));
    }

    private static int process(int n, int k) {
        int left = 0;
        int right = k;

        while (left <= right) {
            int mid = (left + right) >> 1;

            int sum = 0;
            for (int i = 1; i <= n; i++) {
                int count = (Math.min(n, mid / i) - (i - 1)) * 2 - 1;
                if (count <= 0)
                    break;
                sum += count;
            }

            if (sum < k)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return left ;
    }
}