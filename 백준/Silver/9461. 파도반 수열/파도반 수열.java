import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        long[] s = new long[101];
        s[1] = s[2] = s[3] = 1;
        s[4] = 2;

        int last = 4;

        for (int tc = 1; tc <= T; tc++) {
            int now = Integer.parseInt(br.readLine());

            if (last < now)
                for (int i = last + 1; i <= now; i++)
                    s[i] = s[i - 1] + s[i - 5];

            sb.append(s[now]).append("\n");
        }

        System.out.println(sb);
    }
}