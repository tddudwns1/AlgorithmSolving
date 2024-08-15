import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 다익스트라 문제
 */
public class Main {
    /**
     * 3 가지 용도
     *  1. 출발 도시부터 도착 도시까지 비용 정보 -> weights 에 저장
     *      도착 도시
     *      이동 비용
     *  2. 시작 도시부터 현재 도시까지 총합 비용 정보 -> pq 에 저장
     *      다음 도착 도시
     *      다음 도시까지 이동 총 합 비용
     *  3. 현재 도시까지 가장 낮은 비용 정보 -> confirmed 에 저장
     *      현재 도시에 도착하기 이전 도시
     *      현재 도시까지 이동 총 합 비용
     */
    static class Info implements Comparable<Info>{
        int city;
        int weight;

        public Info(int city, int weight) {
            this.city = city;
            this.weight = weight;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // 도시 간 이동 비용 정보 저장
        // 비용이 0인 경우도 있기에 배열 대신 list 로 진행
        List<Info>[] weights = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            weights[i] = new ArrayList();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            weights[start].add(new Info(end, weight));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(weights, start, end, n));
    }

    private static String dijkstra(List<Info>[] weights, int start, int end, int n) {
        Queue<Info> pq = new PriorityQueue<>();
        Info[] confirmed = new Info[n + 1];

        pq.add(new Info(start, 0));
        confirmed[start] = new Info(-1, 0);

        Stack<Integer> stack = new Stack<>();

        while(true) {
            Info now = pq.poll();

            if (now.city == end)
                break;

            for (Info next : weights[now.city]) {
                int newWeight = now.weight + next.weight;
                if (confirmed[next.city] != null && confirmed[next.city].weight <= newWeight)
                    continue;

                confirmed[next.city] = new Info(now.city, newWeight);

                pq.add(new Info(next.city, newWeight));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(confirmed[end].weight).append("\n");

        while (end != -1) {
            stack.add(end);

            end = confirmed[end].city;
        }

        sb.append(stack.size()).append("\n");
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(" ");

        return sb.toString();
    }
}