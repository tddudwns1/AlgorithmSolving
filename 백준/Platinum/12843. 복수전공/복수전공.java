import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int connect = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer> computers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            String department = st.nextToken();

            if (department.equals("c"))
                computers.add(number);
        }

        List<Integer>[] connectBySoftware = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            connectBySoftware[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int computer = Integer.parseInt(st.nextToken());
            int software = Integer.parseInt(st.nextToken());

            connectBySoftware[computer].add(software);
        }

        int[] connectByComputer = new int[n + 1];

        for (int now : computers)
            if (dfs(now, connectBySoftware, connectByComputer, new boolean[n + 1]))
                connect++;

        System.out.println(n - connect);
    }

    private static boolean dfs(int now, List<Integer>[] connectBySoftware, int[] connectByComputer, boolean[] visited) {
        for (int software : connectBySoftware[now]) {
            if (visited[software])
                continue;
            visited[software] = true;

            if (connectByComputer[software] == 0 || dfs(connectByComputer[software], connectBySoftware, connectByComputer, visited)) {
                connectByComputer[software] = now;
                return true;
            }
        }

        return false;
    }
}