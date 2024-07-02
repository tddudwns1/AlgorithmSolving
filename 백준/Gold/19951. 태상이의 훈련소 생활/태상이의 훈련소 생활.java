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

        int[] yeonByeonJang = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            yeonByeonJang[i] = Integer.parseInt(st.nextToken());

        int[] jiSi = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            jiSi[a] += k;
            if (b < n)
                jiSi[b + 1] -= k;
        }

        StringBuilder sb = new StringBuilder();
        int temp = 0;
        for (int i = 1; i <= n; i++) {
            temp += jiSi[i];
            sb.append(yeonByeonJang[i] + temp).append(" ");
        }

        System.out.println(sb);
    }
}