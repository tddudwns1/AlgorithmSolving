import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int node;
        int weight;

        /**
         * 연결된 정점 정보를 가지고 있는 객체
         * @param node 반대편 정점
         * @param weight 가중치
         */
        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        /**
         * 낮은 비용을 우선시 하여 비교하는 함수
         * @param o the object to be compared.
         * @return 낮은 비용
         */
        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        // 연결된 정보를 담을 큐 배열
        Deque<Edge>[] edges = new Deque[v + 1];
        for (int i = 1; i <= v; i++)
            edges[i] = new ArrayDeque<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 양방향이므로 두 곳 모두 넣어준다
            edges[from].add(new Edge(to, weight));
            edges[to].add(new Edge(from, weight));
        }

        System.out.println(prim(edges, v));
    }

    private static int prim(Deque<Edge>[] edges, int v) {
        // 프림 알고리즘을 위한 우선순위 큐와, 비용이 확정된 정점을 기록할 배열
        Queue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[v + 1];

        // 1. 1번 정점부터 시작
        pq.add(new Edge(1, 0));

        // 정답을 저장할 변수와, 모든 정점이 이어졌는지 확인 할 카운트 변수
        int answer = 0;
        int count = v;
        // 6.(끝) 모든 정점이 이어질 때 까지
        while (count > 0) {
            // 2. 우선순위 큐에서 현재 가장 가중치가 낮은 연결 정보를 가진 객체를 반환
            Edge now = pq.poll();

            // 3. 이미 비용이 확정된 정점인지 확인
            if (visited[now.node])
                // 3-1. 확정됐다면 건너뛰기
                continue;
            // 3-2. 확정되지 않았다면 기록
            visited[now.node] = true;

            // 4. 정답 변수에 해당 가중치 추가, 카운트 변수 감소
            answer += now.weight;
            count--;

            // 5. 해당 정점이 가진 연결 정보를 우선순위 큐에 추가
            for (Edge next : edges[now.node])
                pq.add(next);
        }

        return answer;
    }
}