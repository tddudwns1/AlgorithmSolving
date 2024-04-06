import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            String commands = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String[] arr = br.readLine().split("[\\[\\],]");
            sb.append(process(arr, n, commands)).append("\n");
        }

        System.out.println(sb);
    }

    private static String process(String[] arr, int n, String commands) {
        StringBuilder sb = new StringBuilder();
        boolean isForward = true;
        int left = 1;
        int right = n + 1;

        for (int i = 0; i < commands.length(); i++) {
            char command = commands.charAt(i);

            if (command == 'R') {
                isForward = !isForward;
                continue;
            }

            if (isForward)
                left++;
            else
                right--;
            if (left > right) {
                return "error";
            }
        }

        sb.append("[");

        if (left != right)
            if (isForward) {
                for (int i = left; i < right - 1; i++)
                    sb.append(arr[i]).append(",");
                sb.append(arr[right - 1]);
            } else {
                for (int i = right - 1; i > left; i--)
                    sb.append(arr[i]).append(",");
                sb.append(arr[left]);
            }

        sb.append("]");

        return sb.toString();
    }
}