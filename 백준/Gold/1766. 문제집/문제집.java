import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 위상 정렬 문제
 */
public class Main {
    static class Problem {
        int beforeCount = 0;
        List<Integer> after = new ArrayList<>();

        public Problem(){}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Problem[] problems = new Problem[n + 1];
        for (int i = 1; i <= n; i++)
            problems[i] = new Problem();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            problems[a].after.add(b);
            problems[b].beforeCount++;
        }

        StringBuilder sb = new StringBuilder();

        process(n, sb, problems);

        System.out.println(sb);
    }

    private static void process(int n, StringBuilder sb, Problem[] problems) {
        // 기존 위상 정렬과 다른 유일한 부분
        Queue<Integer> candidate = new PriorityQueue<>();

        for (int i = 1; i <= n; i++)
            if (problems[i].beforeCount == 0)
                candidate.add(i);

        while(!candidate.isEmpty()) {
            int now = candidate.poll();

            for (int next : problems[now].after)
                if (--problems[next].beforeCount == 0)
                    candidate.add(next);

            sb.append(now).append(" ");
        }
    }
}