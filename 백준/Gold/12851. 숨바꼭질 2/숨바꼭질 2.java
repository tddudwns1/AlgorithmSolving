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
        int k = Integer.parseInt(st.nextToken());

        int[] answer = process(n, k);

        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    private static int[] process(int n, int k) {
        // 배열 길이 고민
        int[] field = new int[100_002];
        Arrays.fill(field, Integer.MAX_VALUE);
        Queue<Integer> q = new ArrayDeque<>();

        q.add(n);

        int time = 0;
        while (true) {
            int size = q.size();
            int count = 0;

            while (size-- > 0) {
                int now = q.poll();

                if (field[now] < time)
                    continue;

                if (now == k) {
                    count++;
                    continue;
                }

                field[now] = time;

                int back = now - 1;
                if (back >= 0)
                    q.add(back);
                int front = now + 1;
                if (front <= 100_001)
                    q.add(front);
                int mult = now * 2;
                if (mult <= 100_001)
                    q.add(mult);
            }

            if (count != 0)
                return new int[]{time, count};
            time++;
        }
    }
}