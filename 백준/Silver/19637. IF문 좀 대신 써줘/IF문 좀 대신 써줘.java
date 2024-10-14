import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeMap<Integer, String> nickNameMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String nickName = st.nextToken();
            int limit = Integer.parseInt(st.nextToken());

            if (nickNameMap.containsKey(limit))
                continue;
            nickNameMap.put(limit, nickName);
        }


        StringBuilder sb = new StringBuilder();

        // m개의 입력을 처리
        for (int i = 0; i < m; i++) {
            int power = Integer.parseInt(br.readLine());

            // 주어진 power보다 작거나 같은 가장 큰 key를 찾는다
            Integer key = nickNameMap.ceilingKey(power);

            // 만약 key가 null인 경우는 처리할 수 없으므로, 무시
            sb.append(nickNameMap.get(key)).append("\n");
        }

        System.out.println(sb);
    }
}