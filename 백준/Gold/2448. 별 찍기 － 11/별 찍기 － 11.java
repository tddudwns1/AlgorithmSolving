import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        char[][] mark = new char[n][2 * n - 1];
        for (int i = 0; i < n; i++)
            Arrays.fill(mark[i], ' ');

        process(0, 0, n, mark);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(mark[i]);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void process(int y, int x, int len, char[][] mark) {
        if (len == 3) {
            markProcess(y, x, mark);
            return;
        }
        int halfLen = len >> 1;
        process(y, x + halfLen, halfLen, mark);
        process(y + halfLen, x, halfLen, mark);
        process(y + halfLen, x + len, halfLen, mark);
    }

    private static void markProcess(int y, int x, char[][] mark) {
        mark[y][x + 2] = '*';
        mark[++y][x + 1] = '*';
        mark[y++][x + 3] = '*';
        for (int i = 0; i < 5; i++)
            mark[y][x + i] = '*';
    }
}