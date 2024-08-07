import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        int bestMid = 0;

        portal:
        while (left <= right) {
            int mid = (left + right) >> 1;
            int before = -mid;
            int count = 0;

            for (int i = 0; i < k; i++) {
                int position = referees[i];

                if (position - before < mid)
                    continue;

                before = position;

                if (++count != m)
                    continue;

                left = mid + 1;
                bestMid = mid;
                continue portal;
            }

            right = mid - 1;
        }

        long answer = 0;
        int before = -bestMid;
        int count = 0;

        for (int i = 0; i < k; i++) {
            int position = referees[i];

            answer = answer << 1;

            if (position - before < bestMid)
                continue;

            answer++;
            before = position;

            if (++count != m)
                continue;

            while (++i < k)
                answer = answer << 1;

            break;
        }

        System.out.println(Long.toBinaryString(answer));
    }
}