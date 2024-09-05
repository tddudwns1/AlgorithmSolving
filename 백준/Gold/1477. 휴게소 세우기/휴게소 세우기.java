import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] restSpot = new int[n];
        for (int i = 0; i < n; i++) {
            restSpot[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(restSpot);

        System.out.println(process(n, m, l, restSpot));
    }

    private static int process(int n, int m, int l, int[] restSpot) {
        int left = 1;
        int right = l * 2 / (m + 1) + 1;

        int answer = Integer.MAX_VALUE;

        while (left < right) {
            int distance = (left + right) >> 1;

            int count = 0;
            int spot = 0;

            int max = distance;
            for (int i = 0; i < n; i++) {
                int now = restSpot[i];

                while (true) {
                    int next = spot + distance;

                    if (next >= now)
                        break;

                    spot = next;
                    count++;
                }

                max = Math.max(max, restSpot[i] - spot);

                spot = restSpot[i];
            }

            while (true) {
                int next = spot + distance;

                if (next >= l)
                    break;

                spot = next;
                count++;
            }


            if (count > m) {
                left = distance + 1;
            } else {
                right = distance;
                answer = Math.min(answer, max);
            }
        }

        return answer;
    }
}