import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    final static int MAX = 4000_0001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] costs = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                costs[i][j] = MAX;
            }
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            costs[a][b] = c;
        }

        for (int center = 1; center <= v; center++) {
            for (int start = 1; start <= v; start++) {
                for (int end = 1; end <= v; end++) {
                    costs[start][end] = Math.min(costs[start][end], costs[start][center] + costs[center][end]);
                }
            }
        }

        int answer = MAX;

        for (int i = 1; i <= v; i++) {
            answer = Integer.min(answer, costs[i][i]);
        }

        if (answer == MAX)
            System.out.println(-1);
        else
            System.out.println(answer);
    }
}