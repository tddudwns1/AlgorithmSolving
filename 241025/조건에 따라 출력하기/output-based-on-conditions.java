import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0)
                break;

            if (num % 3 == 0)
                sb.append(num / 3);
            else
                sb.append(num + 2);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}