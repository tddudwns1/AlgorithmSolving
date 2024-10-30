import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int maxLimit = 100 * n;

        int[] memories = new int[maxLimit + 1];
        memories[0] = 0;

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int app = 0; app < n; app++) {
            int memory = Integer.parseInt(st1.nextToken());
            int weight = Integer.parseInt(st2.nextToken());

            for (int nowWeight = maxLimit; nowWeight >= weight; nowWeight--) {
                if (memories[nowWeight - weight] == 10001)
                    continue;

                int newMemory = memories[nowWeight - weight] + memory;
                if (memories[nowWeight] >= newMemory)
                    continue;

                memories[nowWeight] = newMemory;
            }
        }

        int answer = 0;

        for (int i = 0; i <= maxLimit; i++) {
            if (memories[i] < m || memories[i] == 10001)
                continue;
            answer = i;
            break;
        }

        System.out.println(answer);
    }
}