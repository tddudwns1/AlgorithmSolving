import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long r = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());

            long n = Math.min(r, c);
            long diff = Math.max(r, c) - n;

            long nPlus1Multn = n * (n + 1);
            long dark = nPlus1Multn * (2 * n + 1) / 3
                    - nPlus1Multn
                    + nPlus1Multn * diff
                    - n * diff;
            long white = dark
                    + n;

            sb.append(white).append(" ").append(dark).append("\n");
        }

        System.out.println(sb);
    }
}