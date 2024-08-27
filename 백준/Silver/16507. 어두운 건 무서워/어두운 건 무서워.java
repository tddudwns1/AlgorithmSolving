import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][] brights = new int[r + 1][c + 1];

        for (int y = 1; y <= r; y++) {
            st = new StringTokenizer(br.readLine());

            for (int x = 1; x <= c; x++) {
                brights[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        prefixSum(r, c, brights);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());

            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            sb.append(getPixelAvg(r1 - 1, c1 - 1, r2, c2, brights)).append("\n");
        }

        System.out.println(sb);
    }

    private static void prefixSum(int r, int c, int[][] brights) {
        for (int y = 1; y <= r; y++) {
            for (int x = 2; x <= c; x++) {
                brights[y][x] += brights[y][x - 1];
            }
        }

        for (int y = 2; y <= r; y++) {
            for (int x = 1; x <= c; x++) {
                brights[y][x] += brights[y - 1][x];
            }
        }
    }

    private static int getPixelAvg(int r1, int c1, int r2, int c2, int[][] brights) {
        int sumOfRange = brights[r2][c2] - brights[r1][c2] - brights[r2][c1] + brights[r1][c1];
        int sizeOfRange = (r2 - r1) * (c2 - c1);
        return sumOfRange / sizeOfRange;
    }
}