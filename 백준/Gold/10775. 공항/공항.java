import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine()); // gate 수
        int p = Integer.parseInt(br.readLine()); // plane 수

        int[] usuallyGate = new int[g + 1];
        for (int i = 1; i <= g; i++) {
            usuallyGate[i] = i;
        }

        int i;

        for (i = 0; i < p; ++i) {
            int n = Integer.parseInt(br.readLine()); // 사용 가능한 gate 번호
            if (find(n, usuallyGate) == -1) {
                break;
            }
        }

        System.out.println(i);
    }

    private static int find(int n, int[] usuallyGate) {
        if (n == usuallyGate[n]) {
            return usuallyGate[n] = n - 1;
        }

        return usuallyGate[n] = find(usuallyGate[n], usuallyGate);
    }
}