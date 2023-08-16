import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int ans = 0;
	static class Edge implements Comparable<Edge>{
		int end;
		int weight;
		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		List<Edge>[] edges = new ArrayList[v + 1];
		for(int i = 0; i <= v; i++) edges[i] = new ArrayList<Edge>();
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges[a].add(new Edge(b, weight));
			edges[b]
					.add(
							new Edge(a, weight));
		}
		
		prim(edges, v);
		
		System.out.println(ans);
	}

	private static void prim(List<Edge>[] edges, int v) {
		boolean[] visited = new boolean[v + 1];
		Queue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0));
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			if(visited[now.end])
				continue;
			visited[now.end] = true;
			ans+=now.weight;
			for(Edge e : edges[now.end]) {
				if(visited[e.end])
					continue;
				pq.add(e);
			}
		}
	}
//	private static void prim(List<Edge>[] edges, int v) {
//		boolean[] visited = new boolean[v + 1];
//		Queue<Edge> pq = new PriorityQueue<>();
//		pq.add(new Edge(1,0));
//        
//		while(!pq.isEmpty()) {
//			Edge p = pq.poll();
//			int node = p.end;
//			int weight = p.weight;
//			
//			if(visited[node]) continue;
//			// 선택한 간선의 정점으로부터 가장 낮은 가중치 갖는 정점 선택 
//			visited[node]= true;
//			ans += weight;
//			
//			for(Edge next : edges[node]) {
//				if(!visited[next.end]) {
//					pq.add(next);
//				}
//			}
//		}
//	}
}
