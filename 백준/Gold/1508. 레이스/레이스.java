import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] referees = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++)
            referees[i] = Integer.parseInt(st.nextToken());

        int left = 1;
        int right = n;
        long answer = 0;

        while (left <= right) {
            int mid = (left + right) >> 1;
            int before = -mid;
            long now = 0;
            int i = 0;

            for (; i < k; i++) {
                int position = referees[i];

                now = now << 1;
                if (position - before >= mid) {
                    now += 1;
                    before = position;
                    if (Long.bitCount(now) == m)
                        break;
                }
            }

            while (++i < k)
                now <<= 1;

            if (Long.bitCount(now) == m) {
                left = mid + 1;
                answer = now;
            } else
                right = mid - 1;
        }

        System.out.println(Long.toBinaryString(answer));
    }
}