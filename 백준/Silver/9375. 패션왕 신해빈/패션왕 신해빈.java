import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for (int T = 1; T <= tc; T++) {
            int n = Integer.parseInt(br.readLine());

            Map<String, Integer> map = new HashMap();
            for (int i = 0; i < n; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();
                String key = st.nextToken();

                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            AtomicInteger total = new AtomicInteger(1);
            map.forEach((o, v) -> total.updateAndGet(v1 -> v1 * (v + 1)));

            sb.append(total.get() - 1).append("\n"); // 마지막에 1을 빼줌
        }

        System.out.println(sb);
    }
}