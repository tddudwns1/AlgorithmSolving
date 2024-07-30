import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 수학적 지식과 bfs를 이용한 문제
 * 기본적으로 a + b + c는 유지되는 조건을 이용하면 되는 문제
 * if b > a
 * a = a + a, b = b - a, c = c
 * a + b + c = a + a + b - a + c
 *           = a + b + c
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(process(new int[]{a, b, c}));
    }

    private static int process(int[] stone) {
        // a + b + c가 3으로 나눠지지 않으면 탐색할 필요 x
        if ((stone[0] + stone[1] + stone[2]) % 3 != 0)
            return 0;

        // 두 수만 체크한다면 
        boolean[][] visited = new boolean[1501][1501];

        Queue<int[]> q = new ArrayDeque<>();
        q.add(stone);

        while (!q.isEmpty()) {
            int[] now = q.poll();

            int a = now[0];
            int b = now[1];
            int c = now[2];

            if (a == b && a == c)
                return 1;

            if (a != b)
                if (a < b)
                    addQ(a, b, c, q, visited);
                else
                    addQ(b, a, c, q, visited);


            if (a != c)
                if (a < c)
                    addQ(a, c, b, q, visited);
                else
                    addQ(c, a, b, q, visited);


            if (b != c)
                if (b < c)
                    addQ(b, c, a, q, visited);
                else
                    addQ(c, b, a, q, visited);
        }

        return 0;
    }

    private static void addQ(int small, int big, int other, Queue<int[]> q, boolean[][] visited) {
        if (small > 350)
            return;

        int doubleSmall = small << 1;
        int bigMinusSmall = big - small;

        if (visited[doubleSmall][bigMinusSmall])
            return;

        if (visited[doubleSmall][other])
            return;

        if (visited[other][bigMinusSmall])
            return;

        visited[doubleSmall][bigMinusSmall] = true;
        visited[doubleSmall][other] = true;
        visited[other][bigMinusSmall] = true;

        q.add(new int[]{doubleSmall, bigMinusSmall, other});
    }

}