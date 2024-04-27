import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[][] relate = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            relate[first][second] = true;
            relate[second][first] = true;
        }

        int[][] counts = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(counts[i], Integer.MAX_VALUE);
        }

        int count = Integer.MAX_VALUE;
        int answer = 0;

        // 친구 연결 관계를 낮은 수 부터 시작
        for (int i = 1; i <= n; i++){
            int result = dijkstra(i, relate, counts, n);
            if(count <= result)
                continue;

            count = result;
            answer = i;
        }

        //System.out.println(count);
        System.out.println(answer);
    }

    private static int dijkstra(int start, boolean[][] relate, int[][] counts, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        int count = 0;

        q.add(start);
        counts[start][start] = count;

        while (!q.isEmpty()) {
            count++;
            int size = q.size();

            while (size-- > 0) {
                int now = q.poll();

                for (int i = 1; i <= n; i++) {
                    if (!relate[now][i])
                        continue;

                    // 만약 내가 지금 보는 사람이 낮은 사람이라면
                    // 그 사람은 더 탐색 안 하고 그 사람의 친구 관계만큼 +
                    if (i < start) {
                        //int diff = count - counts[i][start];

                        //for (int j = 1; j < i; j++)
                        //    counts[start][j] = Math.min(counts[start][j], counts[i][j] + count);

                        for (int j = 1; j <= n; j++)
                            counts[start][j] = Math.min(counts[start][j], counts[i][j] + count);

                        continue;
                    }

                    if (counts[start][i] <= count)
                        continue;

                    counts[start][i] = count;
                    //counts[i][now] = count;
                    q.add(i);
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= n; i++)
            answer += counts[start][i];

        return answer;
    }
}