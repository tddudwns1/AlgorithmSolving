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

        int[] answer;

        if (n < k)
            answer = process(n, k);
        else
            answer = new int[]{n - k, 1};

        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    private static int[] process(int n, int k) {
        int[] field = new int[k + 2];
        Arrays.fill(field, Integer.MAX_VALUE);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(n);

        int max = k + 1;
        int time = 0;
        int count = 0;
        while (true) {
            int size = q.size();

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
                if (back >= 0 && field[back] >= time)
                    q.add(back);

                int front = now + 1;
                if (front <= max) {
                    if (field[front] >= time)
                        q.add(front);

                    int mult = now * 2;
                    if (mult <= max && field[mult] >= time)
                        q.add(mult);
                }
            }

            if (count != 0)
                return new int[]{time, count};

            time++;
        }
    }
}