import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class CheckPoint {
        int y;
        int x;

        public CheckPoint(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] infos = new int[k + 1][n];
        CheckPoint[] checkPoint = new CheckPoint[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            checkPoint[i] = new CheckPoint(y, x);
        }

        for (int i = 1; i < n; i++) {
            infos[0][i] = infos[0][i - 1] + getManhattanDistance(checkPoint[i - 1], checkPoint[i]);
        }

        for (int skip = 1; skip <= k; skip++)
            for (int point = 0; point < n; point++) {
                infos[skip][point] = 100_0000;
            }

        for (int point = 1; point < n; point++) {
            for (int skip = 0; skip <= k && skip < point; skip++) {
                for (int i = 0; i <= skip; i++) {
                    int beforeSkip = skip - i;
                    int beforePoint = point - i - 1;

                    infos[skip][point] = Math.min(infos[skip][point],
                            infos[beforeSkip][beforePoint]
                            + getManhattanDistance(checkPoint[beforePoint], checkPoint[point]));
                }
            }

        }

        int min = Integer.MAX_VALUE;
        for (int skip = 0; skip <= k; skip++) {
            min = Math.min(infos[skip][n - 1], min);
        }
        System.out.println(min);
    }

    private static int getManhattanDistance(CheckPoint checkPoint1, CheckPoint checkPoint2) {
        return Math.abs(checkPoint1.y - checkPoint2.y)
                + Math.abs(checkPoint1.x - checkPoint2.x);
    }
}