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
        int left = 1;
        int right = houses[n - 1] - houses[0];
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canPlaceRouters(houses, n, c, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static boolean canPlaceRouters(int[] houses, int n, int c, int distance) {
        int count = 1;
        int prev = houses[0];

        for (int i = 1; i < n; i++) {
            if (houses[i] - prev >= distance) {
                count++;
                prev = houses[i];
                if (count == c) {
                    return true;
                }
            }
        }

        return false;
    }
}