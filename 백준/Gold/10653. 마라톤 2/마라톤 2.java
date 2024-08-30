import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dp 문제
 */
public class Main {
    // 위치 저장 용 클래스
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

        // 코스 수
        int n = Integer.parseInt(st.nextToken());
        // 건너 뛸 코스 수
        int k = Integer.parseInt(st.nextToken());
        if (k == n - 1)
            k--;

        // 코스 좌표 정보
        CheckPoint[] checkPoint = new CheckPoint[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            checkPoint[i] = new CheckPoint(y, x);
        }

        // 코스 별 최소 거리 정보
        int[][] infos = new int[k + 1][n];
        for (int i = 1; i < n; i++) {
            infos[0][i] = infos[0][i - 1] + getManhattanDistance(checkPoint[i - 1], checkPoint[i]);
        }

        // 건너 뛰지 않았을 경우 정보
        for (int skip = 1; skip <= k; skip++)
            for (int point = 0; point < n; point++) {
                infos[skip][point] = 100_0000;
            }

        // 건너 뛸 경우 정보
        // k 번 뛰기 전 + 현재 까지 거리
        // k - 1 번 뛰기 전 + 현재 까지 거리 ...
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

        // 정답 출력
        System.out.println(infos[k][n - 1]);
    }

    private static int getManhattanDistance(CheckPoint checkPoint1, CheckPoint checkPoint2) {
        return Math.abs(checkPoint1.y - checkPoint2.y)
                + Math.abs(checkPoint1.x - checkPoint2.x);
    }
}