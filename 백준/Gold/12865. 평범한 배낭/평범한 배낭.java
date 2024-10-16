import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Product implements Comparable<Product> {
        int w;
        int v;

        public Product(int w, int v) {
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(Product o) {
            return w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Product> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            pq.add(new Product(w, v));
        }

        int[] cost = new int[k + 1];
        while (!pq.isEmpty()) {
            Product now = pq.poll();

            for (int i = k; i >= now.w; i--) {
                cost[i] = Math.max(cost[i], cost[i - now.w] + now.v);
            }
        }

        System.out.println(cost[k]);
    }
}