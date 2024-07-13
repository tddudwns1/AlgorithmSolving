import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        // 동생이 임수빈보다 낮은 위치에 숨어있다면 한 칸 씩 뒤로 걷는게 답이다
        if (n >= k) {
            sb.append(n-k).append('\n');

            for (int i = n; i >= k; i--)
                sb.append(i).append(' ');

            System.out.print(sb);
            return;
        }

        int[] field = new int[100_001];
        Arrays.fill(field, -1);
        field[n] = -2;

        bfs(field, n, k);

        recursion(field, n, k, sb);
        sb.append(k);

        System.out.println(answer);
        System.out.println(sb);
    }

    private static void bfs(int[] field, int n, int k) {
        Queue<Integer> position = new ArrayDeque<>();

        position.add(n);

        while (true) {
            int now = position.poll();

            if (now == k)
                return;

            int back = now - 1;
            if (back >= 0 && field[back] == -1) {
                field[back] = now;
                position.add(back);
            }

            int front = now + 1;
            if (front <= 100_000 && field[front] == -1) {
                field[front] = now;
                position.add(front);
            }

            int teleport = now * 2;
            if (teleport <= 100_000 && field[teleport] == -1) {
                field[teleport] = now;
                position.add(teleport);
            }
        }
    }

    private static void recursion(int[] field, int n, int k, StringBuilder sb) {
        if (k == n)
            return;

        recursion(field, n, field[k], sb);
        sb.append(field[k]).append(" ");
        answer++;
    }
}