import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Position {
        int z;
        int y;
        int x;

        public Position(int f, int y, int x) {
            this.z = f;
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int f = Integer.parseInt(st.nextToken());   // 층 수
            int w = Integer.parseInt(st.nextToken());   // 가로 크기
            int l = Integer.parseInt(st.nextToken());   // 세로 크기
            int n = Integer.parseInt(st.nextToken());   // 직원 수

            st = new StringTokenizer(br.readLine());
            int sz = Integer.parseInt(st.nextToken());   // 편의점 층
            int sx = Integer.parseInt(st.nextToken());   // x
            int sy = Integer.parseInt(st.nextToken());   // y
            Position start = new Position(sz, sy, sx);

            Position[] employees = new Position[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int z = Integer.parseInt(st.nextToken());   // 층
                int x = Integer.parseInt(st.nextToken());   // x
                int y = Integer.parseInt(st.nextToken());   // y

                employees[i] = new Position(z, y, x);
            }

            int[][] durations = new int[n][n];
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (i == j)
                        continue;
                    durations[i][j] = getDuration(employees[i], employees[j], l, w);
                    durations[j][i] = getDuration(employees[j], employees[i], l, w);
                }
            }

            int answer = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int allVisited = (1 << n) - 1;
                int lastEmployeeNumber = n - 1;
                int maxDuration = (2 * f + l + w) * n;

                int[][] memo = new int[n][allVisited];

                int nowDuration = dfs(i, 1 << i, memo, durations, allVisited, lastEmployeeNumber, maxDuration)
                        + getDuration(start, employees[i], l, w);
                answer = Math.min(answer, nowDuration);
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static int dfs(int now, int visited, int[][] memo, int[][] durations, int allVisited, int lastEmployeeNumber, int maxDuration) {
        if (visited == allVisited)
            return 0;

        if (memo[now][visited] != 0)
            return memo[now][visited];

        int minDuration = maxDuration;

        for (int i = lastEmployeeNumber; i >= 0; i--) {
            int nowEmployee = 1 << i;
            if ((visited & nowEmployee) != 0)
                continue;

            minDuration = Math.min(minDuration, durations[now][i] + dfs(i, visited | nowEmployee, memo, durations, allVisited, lastEmployeeNumber, maxDuration));
        }

        return memo[now][visited] = minDuration;
    }

    private static int getDuration(Position e1, Position e2, int l, int w) {
        if (e1.z == e2.z) {
            return Math.abs(e1.y - e2.y) + Math.abs(e1.x - e2.x);
        } else {
            int targetEscalatorY = getEscalator(e1.y, e2.y, l);
            int targetEscalatorX = getEscalator(e1.x, e2.x, w);
            int durationFromFloorMove = getDurationFromFloorMove(e1.z, e2.z);

            int duration1 = Math.abs(e1.y - targetEscalatorY) + Math.abs(e1.x - targetEscalatorX);
            int duration2 = Math.abs(targetEscalatorY - e2.y) + Math.abs(targetEscalatorX - e2.x);
            return duration1 + duration2 + durationFromFloorMove;
        }
    }

    private static int getDurationFromFloorMove(int z1, int z2) {
        if (z1 < z2)
            return (z2 - z1) * 2;
        else
            return z1 - z2;
    }

    private static int getEscalator(int p1, int p2, int end) {
        if (p1 + p2 - 1 < end)
            return 1;
        else
            return end;
    }
}