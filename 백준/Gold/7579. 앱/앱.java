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
        int m = Integer.parseInt(st.nextToken());
        
        int maxLimit = m + 1000_0000;

        int[] weights = new int[maxLimit + 1];
        Arrays.fill(weights, 10_000_001);
        weights[0] = 0;

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int app = 0; app < n; app++) {
            int memory = Integer.parseInt(st1.nextToken());
            int weight = Integer.parseInt(st2.nextToken());

            for (int nowMemory = maxLimit; nowMemory >= memory; nowMemory--) {
                int newWeight = weights[nowMemory - memory] + weight;
                if (weights[nowMemory] <= newWeight)
                    continue;

                weights[nowMemory] = newWeight;
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int i = m; i <= maxLimit; i++) {
            answer = Math.min(answer, weights[i]);
        }

        System.out.println(answer);
    }
}