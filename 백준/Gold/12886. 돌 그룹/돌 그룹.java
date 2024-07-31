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
 * = a + b + c
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

        // 두 수만 체크한다면 남은 수는 확정이므로 2차원 배열로 충분
        boolean[][] visited = new boolean[1501][1501];

        // 세 수를 저장할 자료구조
        Queue<int[]> q = new ArrayDeque<>();
        q.add(stone);

        while (!q.isEmpty()) {
            int[] now = q.poll();

            int a = now[0];
            int b = now[1];
            int c = now[2];

            // 만약 세 그룹의 수가 같다면 멈춤
            if (a == b && a == c)
                return 1;

            // a와 b가 다르다면
            if (a != b)
                // a가 작다면
                if (a < b)
                    addQ(a, b, c, q, visited);
                // b가 작다면
                else
                    addQ(b, a, c, q, visited);

            // 이하 동일
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

    /**
     * 큐에 넣을 수 있는지 확인하는 메서드
     * 작은 수가 350을 넘는지(너무 큰 두 수끼리의 비교를 막기 위함)
     * 확인한 적 있는 조합인지
     * 둘 다 해당하지 않으면 큐에 넣음
     * @param small 작지만 바꿀 수
     * @param big 크지만 바꿀 수
     * @param other 남은 수
     * @param q 세 수를 저장할 자료구조
     * @param visited 확인한 적 있는지 판단할 배열
     */
    private static void addQ(int small, int big, int other, Queue<int[]> q, boolean[][] visited) {
        // 작은 수가 350을 넘는지(너무 큰 두 수끼리의 비교를 막기 위함)
        if (small > 350)
            return;

        int doubleSmall = small << 1;
        int bigMinusSmall = big - small;

        // 확인한 적 있는 조합인지 x3
        if (visited[doubleSmall][bigMinusSmall])
            return;

        visited[bigMinusSmall][doubleSmall] = true;
        visited[doubleSmall][bigMinusSmall] = true;
        visited[bigMinusSmall][other] = true;
        visited[other][bigMinusSmall] = true;
        visited[doubleSmall][other] = true;
        visited[other][doubleSmall] = true;

        q.add(new int[]{doubleSmall, bigMinusSmall, other});
    }
}