import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 플로이드 워셜 풀이
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 비교 가능 여부 저장
        boolean[][] canCompare = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int heavy = Integer.parseInt(st.nextToken());
            int light = Integer.parseInt(st.nextToken());

            canCompare[heavy][light] = true;
        }

        // 연관된 관계를 통해 비교 가능 여부 업데이트
        for (int mid = 1; mid <= n; mid++) {
            for (int y = 1; y <= n; y++) {
                for (int x = 1; x <= n; x++) {
                    if (canCompare[y][mid] & canCompare[mid][x])
                        canCompare[y][x] = true;
                }
            }
        }
        
        // 가운데가 불가능한지 확인
        boolean[] cantMid = new boolean[n + 1];
        int mid = n / 2;
        
        /**
         * 전체적인 로직은
         *  더 무거운, 더 가벼운 수를 센 후, 절반보다 많다면 불가능
         */
        for (int i = 1; i <= n; i++) {
            processByHeavy(n, canCompare, i, cantMid, mid);
        }
        for (int i = 1; i <= n; i++) {
            processByLight(cantMid, i, n, canCompare, mid);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (cantMid[i]) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static void processByHeavy(int n, boolean[][] canCompare, int i, boolean[] cantMid, int mid) {
        int count = 0;

        for (int j = 1; j <= n; j++) {
            if (canCompare[i][j]) {
                // 이미 가운데가 아니라면, 가운데보다 가볍다면
                // 해당 무게보다 가벼운 구슬이기 때문에 탐색 중지
                // 해당 무게는 가운데가 될 수 없음
                if (cantMid[j]) {
                    cantMid[i] = true;
                    return;
                }
                count++;
            }
        }

        // 만약 더 무거운 구슬이 절반보다 많다면
        // 해당 무게는 가운데가 될 수 없음
        if (count > mid)
            cantMid[i] = true;
    }

    private static void processByLight(boolean[] cantMid, int i, int n, boolean[][] canCompare, int mid) {
        // 이미 가운데가 될 수 없다고 판별됐다면 탐색 중지
        if (cantMid[i])
            return;

        int count = 0;

        for (int j = 1; j <= n; j++) {
            if (canCompare[j][i]) {
                count++;
            }
        }

        // 만약 더 가벼운 구슬이 절반보다 많다면
        // 해당 무게는 가운데가 될 수 없음
        if (count > mid)
            cantMid[i] = true;
    }
}