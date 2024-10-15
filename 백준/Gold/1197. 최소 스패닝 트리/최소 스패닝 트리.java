import java.util.*;
import java.io.*;

public class Main {
	static int ans = 0;
	static class Edge implements Comparable<Edge>{
		int next;	// 다음 정점
		int weight;	// 간선의 가중치
		
		public Edge(int next, int weight) {
			this.next = next;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) { // 가중치를 기준으로 오름차순 정렬합니다.
			return this.weight - e.weight;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		List<Edge>[] edges = new ArrayList[v + 1];						// 정점들의 간선 정보들을 저장할 Array입니다
		for(int i = 1; i <= v; i++) edges[i] = new ArrayList<Edge>();	// 정점의 간선 정보들을 저장할 List입니다
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges[a].add(new Edge(b, weight));	//	양방향 간선 정보를 각 정점마다 저장합니다.
			edges[b].add(new Edge(a, weight));
		}
		
		prim(edges, v);				// prim 알고리즘을 이용하여 MST를 구합니다.
		System.out.println(ans);	// MST의 가중치를 출력합니다.
	}
	private static void prim(List<Edge>[] edges, int v) {
		boolean[] visited = new boolean[v + 1];	// 방문을 확인 할 배열입니다.(사이클 생성 방지 용도)
		Queue<Edge> pq = new PriorityQueue<>();	// 가중치가 낮은 간선을 우선으로 선택하기 위한 Queue입니다.
		pq.add(new Edge(1, 0));					// 시작 정점을 정합니다
		
		while(!pq.isEmpty()) {					// 방문하지 않은 정점이 없을 때 까지 반복합니다.
			Edge now = pq.poll();				// 기준이 되는 간선(가중치가 낮은 간선)을 pq에서 제거합니다.
			
			if(visited[now.next]) continue;		// 만약 해당 간선의 정점이 방문을 한 상태라면 skip합니다.
			
			visited[now.next] = true;			// 방문을 하지 않았다면 방문 표시를 하고, 가중치를 더합니다.
			ans += now.weight;
			
			for(Edge e : edges[now.next]) {		// 해당 간선의 정점과 연결된 간선들 중에서
				if(visited[e.next]) continue;	// 그 간선의 정점이 방문을 하지 않았다면
				pq.add(e);						// pq에 추가합니다.
			}	
		}
	}
}
