import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static class Info {
        int index;
        int value;

        public Info(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] lis = new int[n + 1];
        Info[] tracking = new Info[n];

        lis[0] = -1_000_000_001;
        int top = 0;

        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(st.nextToken());
            int index;

            if (lis[top] < now) {
                index = ++top;
            } else {
                index = binarySearch(top, now, lis);
            }
            lis[index] = now;
            tracking[i] = new Info(index, now);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(top).append("\n");

        Deque<Integer> dq = new ArrayDeque<>();
        int before = top;
        for (int i = n - 1; i >= 0 ; i--) {
            if (tracking[i].index == before) {
                dq.addFirst(tracking[i].value);
                before--;
            }
        }

        for (int now : dq) {
            sb.append(now).append(" ");
        }

        System.out.println(sb);
    }

    private static int binarySearch(int right, int target, int[] arr) {
        int left = 1;

        while (left < right) {
            int mid = (left + right) >>> 1;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}