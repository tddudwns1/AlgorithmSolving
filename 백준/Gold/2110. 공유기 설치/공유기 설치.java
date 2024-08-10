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
        int c = Integer.parseInt(st.nextToken());

        int[] houses = new int[n];
        for (int i = 0; i < n; i++)
            houses[i] = Integer.parseInt(br.readLine());

        Arrays.sort(houses);

        System.out.println(process(houses, n, c));
    }

    private static int process(int[] houses, int n, int c) {
        int answer = 0;
        int left = 0;
        int right = houses[n - 1] - houses[0] + 1;

        while (left < right) {
            int mid = (left + right) >> 1;
            int count = 0;
            int prev = -mid;

            // 여기에 이진탐색 좀있다가 넣어보기
            for (int now : houses) {
                if (now - prev < mid)
                    continue;

                count++;
                prev = now;
            }

            if (count >= c) {
                answer = mid;
                left = mid + 1;
            } else
                right = mid;
        }

        return answer;
    }
}