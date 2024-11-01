import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] sequence = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            sequence[i] = Integer.parseInt(st.nextToken());

        int answer = 0;
        int shortest = Integer.MAX_VALUE;
        int sum = 0;

        int left = 0;
        int right = 0;
        while (true) {
            if (sum < s) {
                if (right == n)
                    break;
                sum += sequence[right++];
            } else {
                answer = shortest = Integer.min(shortest, right - left);
                if (answer == 1)
                    break;
                sum -= sequence[left++];
            }
        }

        System.out.println(answer);
    }
}
