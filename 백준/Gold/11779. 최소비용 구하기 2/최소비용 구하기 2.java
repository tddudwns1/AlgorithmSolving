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

        // 출발 도시부터 도착 도시까지 비용 정보
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

        // 다익스트라 진행 후 결과 출력
        System.out.println(dijkstra(weights, start, end, n));
    }

    /**
     * 다익스트라를 이용해 출발 도시 부터 도착 도시 까지 최소 비용 계산 하는 메서드
     * @param weights 출발 도시부터 도착 도시까지 비용 정보
     * @param start 출발 도시
     * @param end 도착 도시
     * @param n 도시의 수
     * @return 비용, 방문 도시 수, 방문 도시 순서
     */
    private static String dijkstra(List<Info>[] weights, int start, int end, int n) {
        // 시작 도시부터 현재 도시까지 총합 비용 정보
        Queue<Info> pq = new PriorityQueue<>();
        // 현재 도시까지 가장 낮은 비용 정보
        Info[] confirmed = new Info[n + 1];

        // 출발 도시 정보 추가
        pq.add(new Info(start, 0));
        confirmed[start] = new Info(-1, 0);

        // 다익스트라 알고리즘
        while(true) {
            // 현재 도시가
            Info now = pq.poll();

            // 도착 도시라면 종료
            if (now.city == end)
                break;

            // 아니라면 현재 도시부터 갈 수 있는 다음 도시들 탐색
            for (Info next : weights[now.city]) {
                // 다음 도시에 정보가 있는데 더 효율적이라면 스킵
                int newWeight = now.weight + next.weight;
                if (confirmed[next.city] != null && confirmed[next.city].weight <= newWeight)
                    continue;

                // 아니라면 현재 정보 입력
                // (next.city에는 now.city로 부터 newWeight로 도착 가능하다)
                confirmed[next.city] = new Info(now.city, newWeight);

                // 후보에 저장
                // (next.city에는 newWeight로 도착 할 수 있다)
                pq.add(new Info(next.city, newWeight));
            }
        }

        // 정답 출력
        StringBuilder sb = new StringBuilder();
        // 도착지의 비용 정보 입력
        sb.append(confirmed[end].weight).append("\n");

        // 경로 역순회
        Stack<Integer> stack = new Stack<>();
        while (end != -1) {
            stack.add(end);

            end = confirmed[end].city;
        }

        // 도착지 까지의 경로 수 입력
        sb.append(stack.size()).append("\n");
        // 도착지 까지의 경로 입력
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(" ");

        return sb.toString();
    }
}