import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][] blocks = new int[n + 1][h + 1];

        Queue<Integer>[] students = new ArrayDeque[n + 1];
        for (int i = 1; i <= n; i++)
            students[i] = new ArrayDeque<>();

        for (int student = 1; student <= n; student++) {
            students[student].add(0);

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int now = Integer.parseInt(st.nextToken());
                if (now > h)
                    continue;

                students[student].add(now);
                blocks[student][now] = 1;
            }
        }

        int before = 1;
        for (int nowStudent = 2; nowStudent <= n; nowStudent++) {
            for (int nowBlockHeight : students[nowStudent]) {
                for (int candidate = nowBlockHeight + 1; candidate <= h; candidate++) {
                    blocks[nowStudent][candidate] = (blocks[nowStudent][candidate] + blocks[before][candidate - nowBlockHeight]) % 1_0007;
                }
            }

            before = nowStudent;
        }

        System.out.println(blocks[n][h]);
    }
}