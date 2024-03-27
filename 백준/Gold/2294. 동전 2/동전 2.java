import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] answer = new int[k + 1];
        Arrays.fill(answer, 100_001);
        answer[0] = 0;

        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(br.readLine());

            if (now > k)
                continue;

//            if (answer[now] == 1)
//                continue;

            for (int j = now; j <= k; j++) {
                answer[j] = Math.min(answer[j], answer[j - now] + 1);
            }
        }

        if (answer[k] > 100_000)
            answer[k] = -1;

        System.out.println(answer[k]);
    }
}