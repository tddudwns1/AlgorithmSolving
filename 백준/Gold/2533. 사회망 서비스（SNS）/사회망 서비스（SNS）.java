import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Integer>[] relationship = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            relationship[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            relationship[u].add(v);
            relationship[v].add(u);
        }

        int[][] dp = new int[n + 1][2];
        boolean[] checked = new boolean[n + 1];

        process(1, dp, checked, relationship);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void process(int now, int[][] dp, boolean[] checked, List<Integer>[] relationship) {
        checked[now] = true;
        dp[now][0] = 1;

        for (int next : relationship[now]) {
            if (checked[next])
                continue;
            process(next, dp, checked, relationship);
            dp[now][1] += dp[next][0];
            dp[now][0] += Math.min(dp[next][1], dp[next][0]);
        }
    }
}