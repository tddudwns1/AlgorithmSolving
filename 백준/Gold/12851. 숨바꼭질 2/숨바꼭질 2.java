import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] answer = process(n, k);

        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    /**
     * BFS 이용해 임수빈이 동생을 찾는 시간과 가능한 방법 수 탐색하는 메서드
     * @param n 임수빈 위치
     * @param k 동생 위치
     * @return 시간과 가능한 방법 수
     */
    private static int[] process(int n, int k) {
        // 뒤에 있다면 뒷걸음질만이 답
        if (n >= k)
            return new int[]{n - k, 1};

        // 위치마다 처음 도착하는 시간을 기록하는 장부
        int[] field = new int[k + 2];
        Arrays.fill(field, Integer.MAX_VALUE);

        // 한 칸 넘어서가 최대 탐색 범위
        // 왜냐하면 *2를 하며 k + 2만큼 가고 뒤로 2번 가는 것 보다
        // -1을 하고 *2를 하는게 같은 방법이라도 1번 적은 과정이고
        // 이 보다 긴 방법은 고려할 필요 없음
        int max = k + 1;
        // 시간
        int time = 0;
        // 방법 수
        int count = 0;

        // 시간마다 도착하는 위치를 계산하기 위한 목록
        Queue<Integer> q = new ArrayDeque<>();
        q.add(n);

        // 현재 시간 기록
        field[n] = time;
        while (true) {
            // 같은 시간 내에 탐색할 방법 수
            int size = q.size();

            while (size-- > 0) {
                int now = q.poll();

                // 동생의 위치라면 방법 수 늘리고 pass
                if (now == k) {
                    count++;
                    continue;
                }

                int back = now - 1;
                if (back >= 0 && field[back] >= time) {
                    q.add(back);

                    // 현재 시간 기록
                    field[back] = time;
                }

                int front = now + 1;
                if (front <= max) {
                    if (field[front] >= time) {
                        q.add(front);

                        // 현재 시간 기록
                        field[front] = time;
                    }

                    int mult = now * 2;
                    if (mult <= max && field[mult] >= time) {
                        q.add(mult);

                        // 현재 시간 기록
                        field[mult] = time;
                    }
                }
            }

            if (count != 0)
                return new int[]{time, count};

            time++;
        }
    }
}