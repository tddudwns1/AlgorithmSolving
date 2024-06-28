import java.io.*;
import java.util.*;

class Main {
    static int n, answer = 0;
    static boolean[] 세로;
    static boolean[] 왼밑;
    static boolean[] 오밑;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        세로 = new boolean[n];
        왼밑 = new boolean[2 * n - 1];
        오밑 = new boolean[2 * n - 1];

        backtracking(0);

        System.out.println(answer);
    }

    private static void backtracking(int y) {
        if (y == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (세로[i] || 왼밑[i + y] || 오밑[i - y + n - 1]) continue;

            세로[i] = true;
            왼밑[i + y] = true;
            오밑[i - y + n - 1] = true;

            backtracking(y + 1);

            세로[i] = false;
            왼밑[i + y] = false;
            오밑[i - y + n - 1] = false;
        }
    }
}