import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

class Point {
    int x;
    int y;

    public Point(int y, int x) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] field = new char[12][6];

        for (int i = 0; i < 12; i++)
            field[i] = br.readLine().toCharArray();

        int answer = 0;

        while (remove(field)) {
            replace(field);
            answer++;
        }

        System.out.println(answer);
    }

    private static void replace(char[][] field) {
        for (int x = 0; x < 6; x++) {
            Deque<Integer> countEmpties = new ArrayDeque<>();

            int count = 0;

            if (field[0][x] != '.')
                countEmpties.addFirst(0);

            for (int y = 0; y < 12; y++) {
                if (field[y][x] == '.') {
                    count++;
                    continue;
                }

                if (count != 0)
                    countEmpties.addFirst(count);

                count = 0;
            }

            if (count != 0)
                countEmpties.addFirst(count);

            if (!countEmpties.isEmpty())
                countEmpties.removeLast();

            if (!countEmpties.isEmpty())
                gravity(field, x, countEmpties);
        }
    }

    private static void gravity(char[][] field, int x, Deque<Integer> countEmpties) {
        int y = 11;
        while (field[y][x] != '.')
            y--;

        int now = 0;

        while (!countEmpties.isEmpty()) {
            now += countEmpties.pop();

            if (now == 0)
                break;

            while (true) {
                if (y < now)
                    break;

                if (field[y - now][x] == '.')
                    break;

                field[y][x] = field[y-- - now][x];
            }
        }

        while (y >= 0)
            field[y--][x] = '.';
    }

    private static boolean remove(char[][] field) {
        boolean[][] checked = new boolean[12][6];
        boolean isRemove = false;

        for (int i = 0; i < 12; i++)
            for (int j = 0; j < 6; j++) {
                if (field[i][j] == '.')
                    continue;

                if (checked[i][j])
                    continue;

                if (bfs(field, i, j, checked))
                    isRemove = true;
            }

        return isRemove;
    }

    private static boolean bfs(char[][] field, int y, int x, boolean[][] checked) {
        int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        Queue<Point> q = new ArrayDeque<>();
        Queue<Point> candidate = new ArrayDeque<>();
        char target = field[y][x];
        boolean isRemove = false;

        q.add(new Point(y, x));
        candidate.add(new Point(y, x));
        checked[y][x] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int dy = now.y + move[i][0];
                if (dy < 0 || dy >= 12)
                    continue;

                int dx = now.x + move[i][1];
                if (dx < 0 || dx >= 6)
                    continue;

                if (checked[dy][dx])
                    continue;

                if (field[dy][dx] != target)
                    continue;

                checked[dy][dx] = true;
                q.add(new Point(dy, dx));
                candidate.add(new Point(dy, dx));
            }
        }

        if (candidate.size() >= 4) {
            for (Point now : candidate)
                field[now.y][now.x] = '.';
            isRemove = true;
        }

        return isRemove;
    }
}