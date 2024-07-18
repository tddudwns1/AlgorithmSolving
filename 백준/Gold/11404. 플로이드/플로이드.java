import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        final int MAX = 1000_0000;

        int[][] costs = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(costs[i], MAX);
            costs[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (costs[s][e] <= c)
                continue;

            costs[s][e] = c;
        }

        for (int center = 1; center <= n; center++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    int newCost = costs[start][center] + costs[center][end];

                    if (costs[start][end] <= newCost)
                        continue;

                    costs[start][end] = newCost;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                if (costs[i][j] == MAX)
                    sb.append("0 ");
                else
                    sb.append(costs[i][j]).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);
    }
}