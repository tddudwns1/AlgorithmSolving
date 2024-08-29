import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[][] canGo = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int heavy = Integer.parseInt(st.nextToken());
            int light = Integer.parseInt(st.nextToken());

            canGo[heavy][light] = true;
        }

        for (int mid = 1; mid <= n; mid++) {
            for (int y = 1; y <= n; y++) {
                for (int x = 1; x <= n; x++) {
                    if (canGo[y][mid] & canGo[mid][x])
                        canGo[y][x] = true;
                }
            }
        }

        boolean[] cantMid = new boolean[n + 1];
        int mid = n / 2;

        for (int i = 1; i <= n; i++) {
            int count = 0;

            for (int j = 1; j <= n; j++) {
                if (canGo[i][j])
                    count++;
            }

            if (count > mid)
                cantMid[i] = true;
        }

        for (int i = 1; i <= n; i++) {
            int count = 0;

            for (int j = 1; j <= n; j++) {
                if (canGo[j][i])
                    count++;
            }

            if (count > mid)
                cantMid[i] = true;
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (cantMid[i])
                count++;
        }

        System.out.println(count);
    }
}