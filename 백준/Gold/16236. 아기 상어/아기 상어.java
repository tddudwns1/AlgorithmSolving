import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * bfs, priority 문제
 */
public class Main {
    // 좌표 class
    // 위, 왼쪽 순으로 우선순위
    static class Position implements Comparable<Position> {
        int y;
        int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Position o) {
            if (y != o.y)
                return y - o.y;
            return x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 아기 상어의 위치를 저장 할 변수
        Position positionOfBaby = null;
        // 공간에 상어 위치를 저장 할 배열
        int[][] space = new int[n][n];
        for (int y = 0; y < n; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                int sizeOfShark = Integer.parseInt(st.nextToken());

                // 아기 상어면 따로 위치만 저장
                if (sizeOfShark == 9) {
                    positionOfBaby = new Position(y, x);
                } else {
                    space[y][x] = sizeOfShark;
                }
            }
        }

        // 엄마 상어에게 도움을 요청하지 않고 아기상어가 돌아다닌 시간
        System.out.println(process(positionOfBaby, space, n));
    }

    /**
     * bfs + priority 이용해서 엄마 상어에게 도움을 요청하지 않고 아기상어가 돌아다닌 시간 계산
     * 
     * 1. 이동한다
     *      공간 안 인지, 크지 않은 상어인지 확인
     *      확인 후 이동 가능한 위치들은 List 에 넣음
     *      다음 이동은 List 를 우선순위에 맞게 정렬한 후 이동 함
     * 2. 작은 상어면 먹는다
     *      이동한 위치에 작은 상어가 존재하면
     *      해당 공간은 0으로 초기화
     *      전체 탐색 초기화 후 1번부터 탐색 시작
     * 3. 먹을 상어가 없으면 멈춘다
     *      만약 1번 과정에서 작은 상어가 탐색 되지 않으면 종료
     * @param positionOfBaby
     * @param space
     * @param n
     * @return
     */
    private static int process(Position positionOfBaby, int[][] space, int n) {
        int[][] move = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        List<Position> list = new ArrayList<>();
        list.add(positionOfBaby);

        boolean[][] visited = new boolean[n][n];
        visited[positionOfBaby.y][positionOfBaby.x] = true;

        int sizeOfBaby = 2;
        int countForSizeUp = sizeOfBaby;
        int answer = 0;
        int time = 0;

        portal:
        // 3. 먹을 상어가 없으면 멈춘다
        while (!list.isEmpty()) {
            int size = list.size();
            Collections.sort(list);

            while (size-- > 0) {
                Position now = list.remove(0);

                // 2. 작은 상어면 먹는다
                if (space[now.y][now.x] != 0 && space[now.y][now.x] < sizeOfBaby) {
                    answer = time;
                    space[now.y][now.x] = 0;

                    if (--countForSizeUp == 0) {
                        countForSizeUp = ++sizeOfBaby;
                    }

                    list = new ArrayList<>();
                    list.add(new Position(now.y, now.x));

                    visited = new boolean[n][n];
                    visited[now.y][now.x] = true;
                    continue portal;
                }

                // 1. 이동한다
                for (int i = 0; i < 4; i++) {
                    int dy = now.y + move[i][0];
                    if (dy < 0 || dy >= n)
                        continue;
                    int dx = now.x + move[i][1];
                    if (dx < 0 || dx >= n)
                        continue;

                    if (visited[dy][dx])
                        continue;

                    visited[dy][dx] = true;

                    if (space[dy][dx] > sizeOfBaby)
                        continue;

                    list.add(new Position(dy, dx));
                }
            }
            time++;
        }

        return answer;
    }
}