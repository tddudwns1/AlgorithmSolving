import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        sb.append("YES");

        int[] array = new int[n];
        int[] order = new int[n];

        int index = n / 2;
        boolean flag = true;
        for (int i = 1; i <= n; i++) {
            array[index] = i;
            order[i - 1] = index + 1;

            int num = i;
            if (flag)
                num *= -1;
            flag = !flag;

            index += num;
        }

        sb.append("\n");
        for (int now : array)
            sb.append(now).append(" ");

        sb.append("\n");
        for (int now : order)
            sb.append(now).append(" ");

        System.out.println(sb);
    }
}