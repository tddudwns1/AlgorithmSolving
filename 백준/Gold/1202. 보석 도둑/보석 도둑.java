import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Jewel implements Comparable<Jewel> {
        int m;
        int v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel o) {
            if (v == o.v)
                return m - o.m;
            return o.v - v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Jewel> jewels = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(m, v));
        }

        Collections.sort(jewels, Comparator.comparingInt(j -> j.m));

        int[] bags = new int[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);

        PriorityQueue<Jewel> pq = new PriorityQueue<>();
        long answer = 0;
        int jewelIndex = 0;

        for (int i = 0; i < k; i++) {
            while (jewelIndex < n && jewels.get(jewelIndex).m <= bags[i]) {
                pq.add(jewels.get(jewelIndex));
                jewelIndex++;
            }

            if (!pq.isEmpty()) {
                answer += pq.poll().v;
            }
        }

        System.out.println(answer);
    }
}