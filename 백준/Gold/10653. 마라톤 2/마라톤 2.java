import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Info {
        CheckPoint checkPoint;
        int weight;

        public Info(CheckPoint checkPoint, int weight) {
            this.checkPoint = checkPoint;
            this.weight = weight;
        }
    }

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

        Info[][] infos = new Info[k + 1][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            infos[0][i] = new Info(new CheckPoint(y, x), 0);
        }

        for (int i = 1; i < n; i++) {
            infos[0][i].weight = infos[0][i - 1].weight + getManhattanDistance(infos[0][i - 1], infos[0][i]);
        }

        for (int skip = 1; skip <= k; skip++)
            for (int point = 0; point < n; point++) {
                infos[skip][point] = new Info(new CheckPoint(0, 0), 100_0000);
            }

        for (int point = 1; point < n; point++) {
            for (int skip = 0; skip <= k && skip < point; skip++) {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i <= skip; i++) {
                    int beforeSkip = skip - i;
                    int beforePoint = point - i - 1;

                    min = Math.min(min,
                            infos[beforeSkip][beforePoint].weight
                            + getManhattanDistance(infos[0][beforePoint], infos[0][point]));
                }
                infos[skip][point].weight = min;
            }

        }

        int min = Integer.MAX_VALUE;
        for (int skip = 0; skip <= k; skip++) {
            min = Math.min(infos[skip][n - 1].weight, min);
        }
        System.out.println(min);
    }

    private static int getManhattanDistance(Info info1, Info info2) {
        return Math.abs(info1.checkPoint.y - info2.checkPoint.y)
                + Math.abs(info1.checkPoint.x - info2.checkPoint.x);
    }
}