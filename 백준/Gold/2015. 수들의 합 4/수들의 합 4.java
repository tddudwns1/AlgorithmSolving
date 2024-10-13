import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long answer = 0;
        int prevTotal = 0;
        Map<Integer, Integer> countMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());

            prevTotal += a;

            if (prevTotal == k)
                answer++;
            answer += countMap.getOrDefault(prevTotal - k, 0);

            int orDefault = countMap.getOrDefault(prevTotal, 0);
            countMap.put(prevTotal, orDefault + 1);
        }

        System.out.println(answer);
    }
}