import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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
        int[] knows = new int[know];
        for (int i = 0; i < know; i++)
            knows[i] = Integer.parseInt(st.nextToken());

        int[] groups = new int[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int members = Integer.parseInt(st.nextToken());

            int standard = groups[i] = Integer.parseInt(st.nextToken());

            for (int j = 1; j < members; j++)
                union(standard, Integer.parseInt(st.nextToken()));
        }

        Set<Integer> set = new HashSet<>();
        for (int now : knows)
            set.add(find(now));

        int answer = m;
        for (int i = 0; i < m; i++)
            if (set.contains(find(groups[i])))
                answer--;

        System.out.println(answer);
    }

    private static int find(int now) {
        if (now == parents[now]) return now;
        return parents[now] = find(parents[now]);
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY)
            return;

        if (rootX < rootY)
            parents[rootY] = rootX;
        else
            parents[rootX] = rootY;
    }
}