import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 학생 수
        int m = Integer.parseInt(st.nextToken()); // 비교 횟수

        List<Integer>[] adjList = new ArrayList[n + 1]; // 인접 리스트
        int[] inDegree = new int[n + 1]; // 각 학생의 진입 차수

        // 인접 리스트 초기화
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 키 비교 입력 처리
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b); // a -> b (a가 b보다 앞에 있어야 한다)
            inDegree[b]++; // b의 진입 차수 증가
        }

        // 위상 정렬을 위한 큐 생성
        Queue<Integer> queue = new LinkedList<>();

        // 진입 차수가 0인 학생들을 큐에 넣음
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 결과를 저장할 StringBuilder
        StringBuilder sb = new StringBuilder();

        // 위상 정렬 시작
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current).append(" ");

            // 현재 학생이 가리키는 모든 학생의 진입 차수를 줄임
            for (int next : adjList[current]) {
                inDegree[next]--;

                // 진입 차수가 0이 된 학생을 큐에 넣음
                if (inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        // 결과 출력
        System.out.println(sb.toString().trim());
    }
}