import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

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

            int total = 1;
            for (int now : map.values()) {
                total *= now + 1;
            }

            sb.append(total - 1).append("\n");
        }

        System.out.println(sb);
    }
}