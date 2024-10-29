import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Room {
        int n;
        int countOfCells;

        public Room(int n) {
            this.n = n;
        }
    }

    static class Cell {
        int walls;
        Room room;

        public Cell(int walls) {
            this.walls = walls;
        }
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class CandidateBreakWall {
        Cell cell1;
        Cell cell2;

        public CandidateBreakWall(Cell cell1, Cell cell2) {
            this.cell1 = cell1;
            this.cell2 = cell2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Cell[][] castle = new Cell[m + 2][n + 2];
        for (int y = 1; y <= m; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= n; x++) {
                castle[y][x] = new Cell(Integer.parseInt(st.nextToken()));
            }
        }

        System.out.println(process(castle, m, n));
    }

    private static String process(Cell[][] castle, int m, int n) {
        int[][] move = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        Queue<CandidateBreakWall> candidate = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        makeRoom(castle, m, n, move, candidate, sb);
        selectBreakWall(candidate, sb);

        return sb.toString();
    }

    private static void selectBreakWall(Queue<CandidateBreakWall> candidate, StringBuilder sb) {
        int max = 0;

        while (!candidate.isEmpty()) {
            CandidateBreakWall now = candidate.poll();

            if (now.cell1.room.n == now.cell2.room.n)
                continue;
            max = Math.max(max, now.cell1.room.countOfCells + now.cell2.room.countOfCells);
        }

        sb.append(max);
    }

    private static void makeRoom(Cell[][] castle, int m, int n, int[][] move, Queue<CandidateBreakWall> candidate, StringBuilder sb) {
        int roomNumber = 0;
        int countOfCells = 0;

        for (int y = 1; y <= m; y++) {
            for (int x = 1; x <= n; x++) {
                if (castle[y][x].room != null)
                    continue;

                checkCell(castle, y, x, ++roomNumber, candidate, move);

                countOfCells = Math.max(countOfCells, castle[y][x].room.countOfCells);
            }
        }

        sb.append(roomNumber).append("\n").append(countOfCells).append("\n");
    }

    private static void checkCell(Cell[][] castle, int y, int x, int roomNumber, Queue<CandidateBreakWall> candidate, int[][] move) {
        Queue<Point> confirm = new ArrayDeque<>();
        Queue<Point> memorize = new ArrayDeque<>();

        int countOfCells = 0;

        confirm.add(new Point(y, x));
        castle[y][x].room = new Room(roomNumber);

        while (!confirm.isEmpty()) {
            Point now = confirm.poll();
            memorize.add(now);
            countOfCells++;

            for (int i = 0; i < 4; i++) {
                int dy = now.y + move[i][0];
                int dx = now.x + move[i][1];

                if ((castle[now.y][now.x].walls & 1 << i) > 0) {
                    if (castle[dy][dx] != null)
                        candidate.add(new CandidateBreakWall(castle[now.y][now.x], castle[dy][dx]));
                    continue;
                }

                if (castle[dy][dx].room != null)
                    continue;

                confirm.add(new Point(dy, dx));
                castle[dy][dx].room = new Room(roomNumber);
            }
        }

        while (!memorize.isEmpty()) {
            Point now = memorize.poll();
            castle[now.y][now.x].room.countOfCells = countOfCells;
        }
    }
}