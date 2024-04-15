import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] score = new int[n+2][2];

        for (int i = 2; i < n + 2; i++) {
            int now = Integer.parseInt(br.readLine());

            score[i][1] = Math.max(score[i - 1][0], score[i - 2][1]) + now;
            score[i][0] = score[i - 2][1] + now;
        }

        System.out.println(Math.max(score[n + 1][0], score[n + 1][1]));
    }
}