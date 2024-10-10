import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 지역 개수
        int m = Integer.parseInt(st.nextToken()); // 수색범위
        int r = Integer.parseInt(st.nextToken()); // 길의 개수

        st = new StringTokenizer(br.readLine());
        int[] itemCounts = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            itemCounts[i] = Integer.parseInt(st.nextToken());
        }

        int maxDistance = Integer.MAX_VALUE - 15 * 100;
        int[][] distances = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(distances[i], maxDistance);
            distances[i][i] = 0;
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()); // 지역 a
            int b = Integer.parseInt(st.nextToken()); // 지역 b
            int l = Integer.parseInt(st.nextToken()); // 길의 길이

            distances[a][b] = l;
            distances[b][a] = l;
        }

        for (int target = 1; target <= n; target++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    if (distances[start][target] == maxDistance)
                        continue;
                    if (distances[target][end] == maxDistance)
                        continue;
                    int newWay = distances[start][target] + distances[target][end];
                    if (distances[start][end] > newWay) {
                        distances[start][end] = newWay;
                    }
                }
            }
        }

        int answer = 0;
        for (int start = 1; start <= n; start++) {
            int total = 0;
            for (int end = 1; end <= n; end++) {
                if (distances[start][end] <= m) {
                    total += itemCounts[end];
                }
            }
            answer = Math.max(answer, total);
        }

        System.out.println(answer);
    }
}