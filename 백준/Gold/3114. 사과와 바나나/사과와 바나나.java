import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dp와 prefix sum 문제
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // 각 과일 현황 저장 배열
        // 추후 dp를 위해 앞 뒤 여유를 둠
        int[][] apples = new int[r + 2][c + 2];
        int[][] bananas = new int[r + 2][c + 2];

        for (int y = 1; y <= r; y++) {
            st = new StringTokenizer(br.readLine());

            for (int x = 1; x <= c; x++) {
                String now = st.nextToken();
                char fruit = now.charAt(0);
                int count = Integer.parseInt(now.substring(1));

                if (fruit == 'A')
                    apples[y][x] = count;
                else
                    bananas[y][x] = count;
            }
        }

        // 각 과일 누적합을 저장
        prefixSum(r, c, apples, bananas);

        // 전체 최대 갯수 계산
        System.out.println(dp(r, c, apples, bananas));
    }

    /**
     * 각 과일의 방향별 누적합 저장 배열
     *
     * 불도저는 우측과 하단으로만 이동 가능
     * 그말은 즉, 우측으로 이동 시 좌측의 땅엔 재방문 불가
     *
     * 우측 이동 = 좌측 하단은 A나라가 됨
     *         = 사과 수만 세면 됨
     * 하단 이동 = 우측 상단은 B나라가 됨
     *         = 바나나 수만 세면 됨
     *
     * 누적합 적용 근거
     *      반복되는 과일 갯수 카운팅
     * @param r 세로
     * @param c 가로
     * @param apples 사과 현황
     * @param bananas 바나나 현황
     */
    private static void prefixSum(int r, int c, int[][] apples, int[][] bananas) {
        for (int x = 1; x <= c; x++)
            for (int y = r - 1; y > 1; y--)
                apples[y][x] += apples[y + 1][x];

        for (int y = 1; y <= r; y++)
            for (int x = c - 1; x > 1; x--)
                bananas[y][x] += bananas[y][x + 1];
    }

    /**
     * 누적 현황을 이용한 dp 메서드
     * 누적합 메서드의 설명이 사실상 이 코드의 설명
     * @param r
     * @param c
     * @param apples
     * @param bananas
     * @return
     */
    private static int dp(int r, int c, int[][] apples, int[][] bananas) {
        int[][] dp = new int[r + 1][c + 1];

        /**
         *      1. 오
         *          밑으로 다 더하기(사과)
         *      2. 밑
         *          오른쪽으로 다 더하기(바나나)
         *      3. 오밑
         *          위에 2개 다 진행
         */

        for (int y = 1; y <= r; y++) {
            for (int x = 1; x <= c; x++) {
                dp[y][x] =
                        Math.max(dp[y - 1][x] + bananas[y - 1][x + 1],
                                Math.max(dp[y][x - 1] + apples[y + 1][x - 1],
                                        dp[y - 1][x - 1] + bananas[y - 1][x] + apples[y][x - 1]
                                )
                        );
            }
        }

        return dp[r][c];
    }
}