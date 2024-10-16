import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(process(n, costs));
    }

    private static int process(int n, int[][] costs) {
        int allVisited = (1 << n) - 1;
        int lastCountryNumber = n - 1;
        int maxCost = 16 * 1_000_000 + 1;

        int[][] visited = new int[n][allVisited];

        return dfs(0, 1, visited, costs, allVisited, lastCountryNumber, maxCost);
    }

    private static int dfs(int now, int nowVisited, int[][] visited, int[][] costs, int allVisited, int lastCountryNumber, int maxCost) {
        if (nowVisited == allVisited) {
            if (costs[now][0] == 0)
                return maxCost;
            return costs[now][0];
        }

        if (visited[now][nowVisited] != 0)
            return visited[now][nowVisited];

        int minCost = maxCost;

        for (int i = lastCountryNumber; i >= 0; i--) {
            int nowCountry = 1 << i;
            if ((nowVisited & nowCountry) != 0)
                continue;
            if (costs[now][i] == 0)
                continue;

            minCost = Math.min(minCost, costs[now][i] + dfs(i, nowVisited | nowCountry, visited, costs, allVisited, lastCountryNumber, maxCost));
        }
        return visited[now][nowVisited] = minCost;
    }
}