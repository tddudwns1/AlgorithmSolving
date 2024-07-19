import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++)
            parents[i] = i;

        st = new StringTokenizer(br.readLine());
        int know = Integer.parseInt(st.nextToken());
        for (int i = 0; i < know; i++)
            parents[Integer.parseInt(st.nextToken())] = 0;

        int[] groups = new int[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int members = Integer.parseInt(st.nextToken());

            int standard = Integer.parseInt(st.nextToken());
            groups[i] = standard;

            for (int j = 1; j < members; j++) {
                int now = Integer.parseInt(st.nextToken());
                union(standard, now);
            }
        }

        for (int i = 1; i <= n; i++)
            find(i);

        int answer = m;
        for (int i = 0; i < m; i++)
            if (parents[groups[i]] == 0) 
                answer--;

        System.out.println(answer);
    }

    private static int find(int now) {
        if (now == parents[now]) return now;
        return parents[now] = find(parents[now]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y)
            return;

        if (x < y)
            parents[y] = x;
        else
            parents[x] = y;
    }
}