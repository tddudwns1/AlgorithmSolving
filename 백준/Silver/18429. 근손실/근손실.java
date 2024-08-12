import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int count = 0, n, k, kits[], needWeight[];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        kits = new int[n];
        for (int i = 0; i < n; i++)
            kits[i] = Integer.parseInt(st.nextToken());

        needWeight = new int[n + 1];
        for (int i = 0; i <= n; i++)
            needWeight[i] = k * (i + 1);

        visited = new boolean[n];

        count = factorial(n);

        backtracking(0, n);

        System.out.println(count);
    }

    private static void backtracking(int nowWeight, int day) {
        if (nowWeight >= needWeight[day])
            return;

        if (nowWeight < 0) {
            count -= factorial(day);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            backtracking(nowWeight + kits[i] - k, day - 1);
            visited[i] = false;
        }
    }

    private static int factorial(int n) {
        int value = 1;
        for (int i = 2; i <= n; i++)
            value *= i;

        return value;
    }
}