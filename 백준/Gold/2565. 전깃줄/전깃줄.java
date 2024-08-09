import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Wire implements Comparable<Wire>{
        int start;
        int end;

        public Wire(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Wire o) {
            return start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Wire> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Wire(start, end));
        }
        Collections.sort(list);

        System.out.println(process(n, list));
    }

    private static int process(int n, List<Wire> pq) {
        int[] connect = new int[n + 1];
        int len = 0;

        for (Wire now : pq) {
            int destination = now.end;

            if (connect[len] < destination)
                connect[++len] = destination;
            else
                connect[binarySearch(connect, len, destination)] = destination;
        }

        return n - len;
    }

    private static int binarySearch(int[] connect, int len, int destination) {
        int left = 1;
        int right = len;

        while (left < right) {
            int mid = (left + right) >> 1;

            if (connect[mid] < destination)
                left = mid + 1;
            else
                right = mid;
        }

        return right;
    }
}