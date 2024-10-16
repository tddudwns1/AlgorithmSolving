import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static Deque<Integer>[] tallerList;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        tallerList = new ArrayDeque[n + 1];
        for (int i = 1; i <= n; i++)
            tallerList[i] = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int taller = Integer.parseInt(st.nextToken());
            int shorter = Integer.parseInt(st.nextToken());

            tallerList[shorter].add(taller);
        }

        checked = new boolean[n + 1];

        for (int i = 1; i <= n; i++)
            lineUp(i);

        System.out.println(sb);
    }

    private static void lineUp(int now) {
        if (checked[now]) return;

        while (!tallerList[now].isEmpty())
            lineUp(tallerList[now].remove());

        checked[now] = true;
        sb.append(now).append(" ");
    }
}