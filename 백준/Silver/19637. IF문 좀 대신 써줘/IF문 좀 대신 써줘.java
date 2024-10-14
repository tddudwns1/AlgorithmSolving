import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, String> nickNameMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String nickName = st.nextToken();
            int limit = Integer.parseInt(st.nextToken());

            if (nickNameMap.containsKey(limit))
                continue;
            nickNameMap.put(limit, nickName);
        }

        List<Integer> keys = new ArrayList<>(nickNameMap.keySet());
        int size = keys.size() - 1;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int power = Integer.parseInt(br.readLine());

            int index = binarySearch(keys, power, size);

            sb.append(nickNameMap.get(index)).append("\n");
        }

        System.out.println(sb);
    }

    private static int binarySearch(List<Integer> keys, int power, int size) {
        int left = 0;
        int right = size;

        while (left < right) {
            int mid = (left + right) >> 1;

            if (keys.get(mid) < power)
                left = mid + 1;
            else
                right = mid;
        }

        return keys.get(left);
    }
}