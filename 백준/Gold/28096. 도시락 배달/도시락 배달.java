import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    static final int INF = (int) 1e9;
    static int W, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();  // Skip the first value
            W = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[] S = readPoint(br);
            int[][] A = new int[N][];
            for (int i = 0; i < N; i++) {
                A[i] = readPoint(br);
            }

            int[][] DP = new int[N][1 << N];
            for (int[] row : DP) Arrays.fill(row, INF);

            for (int bit = 1; bit < (1 << N); bit++) {
                if (Integer.bitCount(bit) == 1) {
                    for (int i = 0; i < N; i++) {
                        if ((bit & (1 << i)) != 0) {
                            DP[i][bit] = dist(S, A[i]);
                        }
                    }
                    continue;
                }

                for (int i = 0; i < N; i++) {
                    if ((bit & (1 << i)) == 0) continue;
                    int pbit = bit ^ (1 << i);

                    for (int j = 0; j < N; j++) {
                        if ((pbit & (1 << j)) == 0) continue;
                        DP[i][bit] = Math.min(DP[i][bit], DP[j][pbit] + dist(A[j], A[i]));
                    }
                }
            }

            int ans = INF;
            for (int i = 0; i < N; i++) {
                ans = Math.min(ans, DP[i][(1 << N) - 1]);
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }

    private static int[] readPoint(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] point = new int[3];
        for (int i = 0; i < 3; i++) {
            point[i] = Integer.parseInt(st.nextToken());
        }
        return point;
    }

    private static int dist(int[] S, int[] T) {
        if (S[0] == T[0]) {
            return Math.abs(S[1] - T[1]) + Math.abs(S[2] - T[2]);
        }
        int ret = (S[0] > T[0]) ? S[0] - T[0] : 2 * (T[0] - S[0]);
        ret += Math.min(S[1] + T[1] - 2, 2 * W - S[1] - T[1]);
        ret += Math.min(S[2] + T[2] - 2, 2 * L - S[2] - T[2]);
        return ret;
    }
}