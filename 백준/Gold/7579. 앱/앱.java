import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        int maxLimit = 0;

        int[] weights = new int[n];
        for (int i = 0; i < n; i++)
            maxLimit += weights[i] = Integer.parseInt(st2.nextToken());

        int[] memories = new int[maxLimit + 1];
        memories[0] = 0;

        for (int app = 0; app < n; app++) {
            int memory = Integer.parseInt(st1.nextToken());
            int weight = weights[app];

            for (int nowWeight = maxLimit; nowWeight >= weight; nowWeight--) {
                int newMemory = memories[nowWeight - weight] + memory;
                if (memories[nowWeight] >= newMemory)
                    continue;

                memories[nowWeight] = newMemory;
            }
        }

        int answer = 0;

        for (int i = 0; i <= maxLimit; i++) {
            if (memories[i] < m)
                continue;
            answer = i;
            break;
        }

        System.out.println(answer);
    }
}