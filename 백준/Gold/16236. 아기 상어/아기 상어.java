import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
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

        Queue<Integer> sharkSizeQueue = new PriorityQueue<>();

        Position positionOfBaby = null;
        int[][] space = new int[n][n];
        for (int y = 0; y < n; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                int sizeOfShark = Integer.parseInt(st.nextToken());
                if (sizeOfShark == 0)
                    continue;

                if (sizeOfShark == 9) {
                    positionOfBaby = new Position(y, x);
                } else {
                    sharkSizeQueue.add(space[y][x] = sizeOfShark);
                }
            }
        }

        System.out.println(process(positionOfBaby, space, n, sharkSizeQueue));
    }

    private static int process(Position positionOfBaby, int[][] space, int n, Queue<Integer> sharkSizeQueue) {
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
        while (!list.isEmpty()) {
            int size = list.size();
            Collections.sort(list);

            while (size-- > 0) {
                Position now = list.remove(0);

                if (space[now.y][now.x] != 0 && space[now.y][now.x] < sizeOfBaby) {
                    answer = time;

                    sharkSizeQueue.remove(space[now.y][now.x]);
                    if (sharkSizeQueue.isEmpty())
                        break;
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