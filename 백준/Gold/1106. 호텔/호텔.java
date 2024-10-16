import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken()) + 100;
        int n = Integer.parseInt(st.nextToken());

        int[] costs = new int[c];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[0] = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            for (int j = 0; j < c - w; j++) {
                if (costs[j] != Integer.MAX_VALUE) {
                    int newCost = j + w;
                    costs[newCost] = Math.min(costs[newCost], costs[j] + v);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int j = c - 100; j < c; j++) {
            answer = Math.min(answer, costs[j]);
        }

        System.out.println(answer);
    }
}