import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        int K = Integer.parseInt(br.readLine());
        
        int[] weights = new int[V + 1];
        Arrays.fill(weights, Integer.MAX_VALUE);
        
        List<int[]>[] edges = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            edges[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[start].add(new int[]{end, weight});
        }
        
        dijkstra(V, K, weights, edges);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= V; i++) {
            if (weights[i] == Integer.MAX_VALUE)
                sb.append("INF");
            else
                sb.append(weights[i]);
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
    
    private static void dijkstra(int V, int K, int[] weights, List<int[]>[] edges) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{K, 0});
        weights[K] = 0;
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int start = current[0];
            int weight = current[1];
            
            if (weight > weights[start]) continue;
            
            for (int[] edge : edges[start]) {
                int end = edge[0];
                int newWeight = weight + edge[1];
                
                if (newWeight < weights[end]) {
                    weights[end] = newWeight;
                    pq.add(new int[]{end, newWeight});
                }
            }
        }
    }
}