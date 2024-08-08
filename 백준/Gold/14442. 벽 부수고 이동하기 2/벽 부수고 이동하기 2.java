import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Info {
        int y;
        int x;
        int breakCount;

        public Info(int y, int x, int breakCount) {
            this.y = y;
            this.x = x;
            this.breakCount = breakCount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++)
            map[i] = br.readLine().toCharArray();

        System.out.println(bfs(map, n, m, k));
    }

    private static int bfs(char[][] map, int n, int m, int k) {
        int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean[][][] visited = new boolean[n][m][k + 1];
        Queue<Info> q = new ArrayDeque<>();
        int answer = -1;

        q.add(new Info(0, 0, 0));

        int destinationY = n - 1;
        int destinationX = m - 1;
        int moveCount = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            moveCount++;

            while (size-- > 0) {
                Info now = q.poll();

                if (now.y == destinationY && now.x == destinationX)
                    return moveCount;
                for (int i = 0; i < 4; i++) {
                    int dy = now.y + move[i][0];
                    if (dy < 0 || dy > destinationY)
                        continue;

                    int dx = now.x + move[i][1];
                    if (dx < 0 || dx > destinationX)
                        continue;

                    int newBreakCount = now.breakCount;

                    if (map[dy][dx] == '1')
                        if (++newBreakCount > k)
                            continue;

                    if (visited[dy][dx][newBreakCount])
                        continue;

                    visited[dy][dx][newBreakCount] = true;
                    q.add(new Info(dy, dx, newBreakCount));
                }
            }
        }

        return answer;
    }
}