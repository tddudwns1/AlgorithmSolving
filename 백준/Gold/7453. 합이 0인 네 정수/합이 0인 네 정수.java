import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arrays = new int[4][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                arrays[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] halfArrays = new int[2][n * n];
        for (int i = 0; i < n; i++) {
            int num = n * i;
            for (int j = 0; j < n; j++) {
                halfArrays[0][num + j] = arrays[0][i] + arrays[1][j];
                halfArrays[1][num + j] = arrays[2][i] + arrays[3][j];
            }
        }

        Arrays.sort(halfArrays[0]);
        Arrays.sort(halfArrays[1]);

        System.out.println(process(n * n, halfArrays));
    }

    private static long process(int n, int[][] arrays) {
        long answer = 0;

        int right = n - 1;
        for (int i = 0; i < n; i++) {
            int now = -arrays[0][i];
            right = binarySearchRight(right, now, arrays[1]);

            if (now == arrays[1][right]) {
                int left = binarySearchLeft(right, now, arrays[1]);
                answer += right - left + 1;
            }
        }

        return answer;
    }

    private static int binarySearchRight(int right, long criteria, int[] array) {
        int left = 0;
        int match = right;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (array[mid] > criteria)
                right = mid - 1;
            else {
                left = mid + 1;
                if (array[mid] == criteria)
                    match = mid;
            }
        }

        return match;
    }

    private static int binarySearchLeft(int right, long criteria, int[] array) {
        int left = 0;

        while (left < right) {
            int mid = (left + right) >> 1;

            if (array[mid] < criteria)
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }
}