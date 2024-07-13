import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 0에도 위치할 수 있어 만든 위치 클래스
class Memory {
    int before;

    public Memory(int before) {
        this.before = before;
    }
}

/**
 * 임수빈이 출발한다.
 * 한 칸 뒤, 한 칸 앞, 2배로 텔레포트.
 * 그 위치에 바로 전 위치를 기록한다.
 * 만약 도착한 곳이 정답 위치면.
 * 출발 위치가 나올 때까지 거슬러 올라간 후.
 * 정답을 출력한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        // 동생이 임수빈보다 낮은 위치에 숨어있다면 한 칸 씩 뒤로 걷는게 답이다
        if (n >= k) {
            sb.append(n - k).append('\n');

            for (int i = n; i >= k; i--)
                sb.append(i).append(' ');

            System.out.print(sb);
            return;
        }

        // 넘어가는 경우보다 항상 더 빠른 해가 존재하기에 넘어가는 경우는 고려하지 않은 크기로 설정
        Memory[] field = new Memory[100_001];
        // 시작 위치 채우기(값은 의미 없지만 만약의 경우를 위해 에러가 날 수로 설정)
        field[n] = new Memory(-1);

        // 가장 빠른 경우를 탐색하는 문제이기에 bfs 채택
        bfs(field, n, k);

        // 정답을 거슬러 올라가 출력해야 하기에 recursion 채택
        reversePath(field, n, k, sb);

        sb.append(k);
        System.out.println(sb);
    }

    /**
     * 임수빈이 가장 빨리 숨은 동생을 발견할 방법을 찾는 메서드
     *
     * @param field 발자취를 남길 필드
     * @param n     임수빈 위치
     * @param k     동생 위치
     */
    private static void bfs(Memory[] field, int n, int k) {
        Queue<Integer> position = new ArrayDeque<>();

        position.add(n);

        while (true) {
            int now = position.poll();

            // 임수빈이 동생을 찾으면 바로 종료
            if (now == k)
                return;

            // 한 칸 뒤가 찾은 적 없으면 해당 위치에 현재 위치를 기록함
            int back = now - 1;
            if (back >= 0 && field[back] == null) {
                field[back] = new Memory(now);
                position.add(back);
            }

            // 한 칸 앞이 찾은 적 없으면 해당 위치에 현재 위치를 기록함
            int front = now + 1;
            if (front <= 100_000 && field[front] == null) {
                field[front] = new Memory(now);
                position.add(front);
            }

            // 텔레포트 한 곳이 찾은 적 없으면 해당 위치에 현재 위치를 기록함
            int teleport = now * 2;
            if (teleport <= 100_000 && field[teleport] == null) {
                field[teleport] = new Memory(now);
                position.add(teleport);
            }
        }
    }

    /**
     * 임수빈의 발자취를 거슬러 찾는 메서드
     *
     * @param field 발자취를 남긴 필드
     * @param n     출발한 위치
     * @param k     현재 돌아가며 방문하는 위치
     * @param sb    정답으로 출력할 메서드
     */
    private static void reversePath(Memory[] field, int n, int k, StringBuilder sb) {
        Deque<Integer> dq = new ArrayDeque<>();

        while (k != n)
            dq.addFirst(k = field[k].before);

        sb.append(dq.size()).append("\n");
        for (int now : dq)
            sb.append(now).append(" ");
    }
}