import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(process(n, a));
    }

    private static int process(int n, int[] a) {
        int[] forward = getLIS(0, n, 1, a, n);
        int[] reverse = getLIS(n - 1, -1, -1, a, n);

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, forward[i] + reverse[i]);
        }

        return max - 1;
    }

    private static int[] getLIS(int start, int end, int value, int[] a, int n) {
        int[] countOfIncrease = new int[n];
        int[] lis = new int[n + 1];

        int top = 0;
        for (int i = start; i != end; i += value) {
            if (a[i] > lis[top]) {
                lis[++top] = a[i];
                countOfIncrease[i] = top;
            } else {
                int index = binarySearch(top, lis, a[i]);
                lis[index] = a[i];
                countOfIncrease[i] = index;
            }
        }

        return countOfIncrease;
    }

    private static int binarySearch(int right, int[] lis, int target) {
        int left = 0;

        while (true) {
            if (left >= right) {
                return left;
            }

            int mid = (left + right) >> 1;
            if (lis[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
    }
}