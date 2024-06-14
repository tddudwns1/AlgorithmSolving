import java.io.*;
import java.util.*;

class Main {
    static class Point {
        int x;
        int y;
        int z;

        public Point(int y, int x, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int numberOfNonRipe = 0;
        Queue<Point> ripes = new ArrayDeque<>();
        String[][][] box = new String[n + 2][m + 2][h + 2];
        for (int i = 1; i <= h ; i++) {
            for (int j = 1; j <= n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= m; k++) {
                    box[j][k][i] = st.nextToken();

                    if (box[j][k][i].equals("0"))
                        numberOfNonRipe++;
                    else if (box[j][k][i].equals("1"))
                        ripes.add(new Point(j, k, i));
                }
            }
        }

        System.out.println(process(m, n, h, numberOfNonRipe, ripes, box));
    }

    private static int process(int m, int n, int h, int numberOfNonRipe, Queue<Point> ripes, String[][][] box) {
        if (numberOfNonRipe == 0)
            return 0;

        int[][] move = {{0, 1, 0},{1, 0, 0},{0, -1, 0},{-1, 0, 0},{0, 0, 1},{0, 0, -1}};
        int days = -1;
        int numberOfRipe = 0;

        while (!ripes.isEmpty()) {
            int todayRipe = ripes.size();
            while (todayRipe-- > 0) {
                Point now = ripes.poll();

                for (int i = 0; i < 6; i++) {
                    int dy = now.y + move[i][0];
                    int dx = now.x + move[i][1];
                    int dz = now.z + move[i][2];

                    if (box[dy][dx][dz] == null || !box[dy][dx][dz].equals("0"))
                        continue;

                    box[dy][dx][dz] = "1";
                    numberOfRipe++;
                    ripes.add(new Point(dy, dx, dz));
                }
            }
            days++;
        }

        if (numberOfNonRipe != numberOfRipe)
            return -1;

        return days;
    }
}