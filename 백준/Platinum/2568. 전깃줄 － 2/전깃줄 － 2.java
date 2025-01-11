import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Connect implements Comparable<Connect> {
        int departure;
        int arrival;

        public Connect(int departure, int arrival) {
            this.departure = departure;
            this.arrival = arrival;
        }

        @Override
        public int compareTo(Connect o) {
            return departure - o.departure;
        }
    }

    static class Info {
        int order;
        Connect connect;

        public Info(int order, Connect connect) {
            this.order = order;
            this.connect = connect;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 전깃줄 개수

        StringBuilder sb = new StringBuilder();

        Queue<Connect> connects = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int departure = Integer.parseInt(st.nextToken());
            int arrival = Integer.parseInt(st.nextToken());

            connects.add(new Connect(departure, arrival));
        }

        Info[] tracking = new Info[n];
        int[] lis = new int[n + 1];
        int top = 0;

        for (int i = 0; i < n; i++) {
            Connect now = connects.poll();
            int index;

            if (lis[top] < now.arrival) {
                index = ++top;
            } else {
                index = binarySearch(top, now.arrival, lis);
            }
            lis[index] = now.arrival;
            tracking[i] = new Info(index, now);
        }

        Queue<Connect> removes = new PriorityQueue<>();
        int before = top;
        for (int i = n - 1; i >= 0 ; i--) {
            if (tracking[i].order == before) {
                before--;
            } else {
                removes.add(tracking[i].connect);
            }
        }

        sb.append(n - top).append("\n");
        while (!removes.isEmpty()) {
            sb.append(removes.poll().departure).append("\n");
        }
        System.out.println(sb);
    }

    private static int binarySearch(int right, int target, int[] arr) {
        int left = 1;

        while (left < right) {
            int mid = (left + right) >> 1;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}